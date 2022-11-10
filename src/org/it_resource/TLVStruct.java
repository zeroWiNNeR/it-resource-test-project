package org.it_resource;

import java.util.List;
import java.util.Map;

class TLVStruct {

    private String value;
    private List<Map<String, String>> items;

    public TLVStruct(String value) {
        this.value = value;
    }

    public TLVStruct(List<Map<String, String>> items) {
        this.items = items;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Map<String, String>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, String>> items) {
        this.items = items;
    }
}
