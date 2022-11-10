package org.it_resource;

import java.nio.ByteBuffer;

class Utils {

    public static void reverse(byte[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    public static int bytesToUInt(byte[] bytes) {
        byte[] buffer = new byte[4];
        if (bytes.length < 4) {
            for (int i = 0; i < 4 - bytes.length; i++) {
                buffer[i] = 0x00;
            }
            for (int i = 0; i < bytes.length; i++) {
                buffer[4 - bytes.length + i] = bytes[i];
            }

            return ((buffer[0] & 0xFF) << 24) | ((buffer[1] & 0xFF) << 16) | ((buffer[2] & 0xFF) << 8) | ((buffer[3] & 0xFF));
        } else {
            return ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) | ((bytes[2] & 0xFF) << 8) | ((bytes[3] & 0xFF));
        }
    }

    public static long bytesToLong(byte[] bytes) {
        byte[] buffer = new byte[8];
        if (bytes.length < 8) {
            for (int i = 0; i < 8 - bytes.length; i++) {
                buffer[i] = 0x00;
            }
            for (int i = 0; i < bytes.length; i++) {
                buffer[8 - bytes.length + i] = bytes[i];
            }
        }
        return ByteBuffer.wrap(buffer).getLong();
    }

}
