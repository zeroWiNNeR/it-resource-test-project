package org.it_resource;

import java.io.UnsupportedEncodingException;

class StringConverterImpl implements Converter {

    private static final int MAX_LENGTH = 1000;
    private static final String TYPE = "CP866";

    @Override
    public String decode(byte[] data) {
        if (data.length > MAX_LENGTH) {
            throw new IllegalArgumentException("Длина String превышает максимальную");
        }

        try {
            return "\"" + new String(data, TYPE) + "\"";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
