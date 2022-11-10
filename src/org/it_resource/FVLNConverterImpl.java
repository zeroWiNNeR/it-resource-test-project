package org.it_resource;

import java.nio.ByteBuffer;

class FVLNConverterImpl implements Converter {

    private static final int MAX_LENGTH = 8;

    @Override
    public String decode(byte[] data) {
        if (data.length > MAX_LENGTH) {
            throw new IllegalArgumentException("Длина FVLN превышает максимальную");
        }

        short point = (short) (data[0] & 0xff);

        byte[] numberBytes = new byte[8];
        for (int i = 0; i < 9 - data.length; i++) {
            numberBytes[i] = (byte) 0x00;
        }
        for (int i = 1; i < data.length; i++) {
            numberBytes[8 - data.length + i] = data[i];
        }

        String number = String.valueOf(ByteBuffer.wrap(numberBytes).getLong());
        if (point == 0) {
            return number;
        } else {
            String beforePoint = number.substring(0, number.length() - point - 1);
            String afterPoint = number.substring(number.length() - point);
            return String.format("%s.%s", beforePoint, afterPoint);
        }
    }

}
