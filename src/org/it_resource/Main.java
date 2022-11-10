package org.it_resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

class Main {

    public static void main(String[] args) throws IOException {
        Map<String, TLVStruct> tlVs = TLVConverter.getTLVsFromBin(args[0]);
        System.out.println(JsonConverter.convertTlvCollectionToJson(tlVs));
        Files.writeString(Paths.get(args[1]), JsonConverter.convertTlvCollectionToJson(tlVs));
    }

}
