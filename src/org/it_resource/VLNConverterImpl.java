package org.it_resource;

import static org.it_resource.Utils.bytesToUInt;
import static org.it_resource.Utils.reverse;

class VLNConverterImpl implements Converter {

    private static final int MAX_LENGTH = 8;

    @Override
    public String decode(byte[] data) {
        if (data.length > MAX_LENGTH) {
            throw new IllegalArgumentException("Длина VLN превышает максимальную");
        }

        reverse(data);
        return String.valueOf(bytesToUInt(data));
    }

}
