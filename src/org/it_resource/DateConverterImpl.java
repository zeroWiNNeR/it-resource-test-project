package org.it_resource;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.it_resource.Utils.bytesToUInt;
import static org.it_resource.Utils.reverse;

class DateConverterImpl implements Converter {

    private static final int MAX_LENGTH = 4;

    @Override
    public String decode(byte[] data) {
        if (data.length > MAX_LENGTH) {
            throw new IllegalArgumentException("Длина Date превышает максимальную");
        }

        reverse(data);
        int utcSecond = bytesToUInt(data);
        return "\"" + LocalDateTime.ofInstant(Instant.ofEpochSecond(utcSecond), ZoneOffset.UTC) + "\"";
    }

}
