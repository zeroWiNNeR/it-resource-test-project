package org.it_resource;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class TLVConverter {

    public static Map<String, TLVStruct> getTLVsFromBin(String file) throws IOException {
        try (RandomAccessFile raFile = new RandomAccessFile(file, "r"); FileChannel fc = raFile.getChannel()) {
            Map<String, TLVStruct> result = new LinkedHashMap<>();
            readNextTLV(fc, result);
            return result;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private static void readNextTLV(FileChannel fc, Map<String, TLVStruct> result) throws IOException {
        if (fc.position() == fc.size()) {
            return;
        }

        short tag = getTagOrLength(fc);
        short length = getTagOrLength(fc);

        String fieldName = ETLVConverter.getFieldNameByTag(tag);
        if (fieldName.equals(ETLVConverter.ITEMS.getFieldName())) {
            Map<String, String> nestedTLV = new LinkedHashMap<>();
            getNestedTLVString(fc, fc.position() + length, nestedTLV);
            if (result.get(fieldName) == null) {
                List<Map<String, String>> items = new ArrayList<>();
                items.add(nestedTLV);
                result.put(fieldName, new TLVStruct(items));
            } else {
                result.get(fieldName).getItems().add(nestedTLV);
            }
        } else {
            result.put(fieldName, new TLVStruct(ETLVConverter.getByTag(tag).decode(getValueBytes(fc, length))));
        }

        readNextTLV(fc, result);
    }

    private static void getNestedTLVString(FileChannel fc, long endPosition, Map<String, String> tlv) throws IOException {
        if (fc.position() == endPosition) {
            return;
        }

        short tag = getTagOrLength(fc);
        short length = getTagOrLength(fc);
        byte[] valueBytes = getValueBytes(fc, length);

        tlv.put(ETLVConverter.getFieldNameByTag(tag), ETLVConverter.getByTag(tag).decode(valueBytes));

        getNestedTLVString(fc, endPosition, tlv);
    }

    private static short getTagOrLength(FileChannel fc) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        fc.read(buffer);
        buffer.flip();
        short result = (short) (buffer.get(1) << 8 | buffer.get(0) & 0xFF);
        buffer.clear();
        return result;
    }

    private static byte[] getValueBytes(FileChannel fc, int length) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        fc.read(buffer);
        buffer.flip();
        byte[] result = buffer.array();
        buffer.clear();
        return result;
    }

}
