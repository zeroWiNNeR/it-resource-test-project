package org.it_resource;

enum ETLVConverter {

    DATE_TIME((short) 1, "dateTime", new DateConverterImpl()),
    ORDER_NUMBER((short) 2, "orderNumber", new VLNConverterImpl()),
    CUSTOMER_NAME((short) 3, "customerName", new StringConverterImpl()),
    ITEMS((short) 4, "items", null),

    NAME((short) 11, "name", new StringConverterImpl()),
    PRICE((short) 12, "price", new VLNConverterImpl()),
    QUANTITY((short) 13, "quantity", new FVLNConverterImpl()),
    SUM((short) 14, "sum", new VLNConverterImpl()),
    ;

    private short tag;
    private String fieldName;
    private Converter converter;

    ETLVConverter(short tag, String fieldName, Converter converter) {
        this.tag = tag;
        this.fieldName = fieldName;
        this.converter = converter;
    }

    public static String getFieldNameByTag(short tag) {
        for (var v : ETLVConverter.values()) {
            if (v.getTag() == tag) {
                return v.getFieldName();
            }
        }

        throw new IllegalArgumentException("Не удалось определить tag: " + tag);
    }

    public static Converter getByTag(short tag) {
        for (var v : values()) {
            if (v.getTag() == tag) {
                return v.getConverter();
            }
        }

        throw new IllegalArgumentException("Не удалось определить tag: " + tag);
    }

    public short getTag() {
        return tag;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Converter getConverter() {
        return converter;
    }

}
