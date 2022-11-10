package org.it_resource;

import java.util.Map;

public class JsonConverter {

    public static String convertTlvCollectionToJson(Map<String, TLVStruct> tlvs) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int counterTlvs = 0;

        for (Map.Entry<String, TLVStruct> entry : tlvs.entrySet()) {
            counterTlvs++;
            if (entry.getKey().equals(ETLVConverter.ITEMS.getFieldName())) {
                sb.append("\n\t\"").append(entry.getKey()).append("\": [");
                int itemsCounter = 0;
                for (Map<String, String> item : entry.getValue().getItems()) {
                    itemsCounter++;
                    sb.append("\n\t\t{");
                    int entryCounter = 0;
                    for (Map.Entry<String, String> itemEntry : item.entrySet()) {
                        entryCounter++;
                        sb.append("\n\t\t\"").append(itemEntry.getKey()).append("\": ").append(itemEntry.getValue());
                        if (entryCounter != item.size()) {
                            sb.append(",");
                        }
                    }
                    sb.append("\n\t\t}");
                    if (itemsCounter != entry.getValue().getItems().size()) {
                        sb.append(",");
                    }
                }
                sb.append("\n\t]");
            } else {
                sb.append("\n\t\"").append(entry.getKey()).append("\": ").append(entry.getValue().getValue());
            }
            if (counterTlvs != tlvs.size()) {
                sb.append(",");
            }
        }
        sb.append("\n}");
        return sb.toString();
    }

}
