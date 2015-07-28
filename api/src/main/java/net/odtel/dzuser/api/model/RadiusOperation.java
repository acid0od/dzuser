package net.odtel.dzuser.api.model;

public enum RadiusOperation {
    ADD_NEW_VALUE("+="),
    REPLAY("="),
    ASSIGN(":="),
    EQUALS("=="),
    EQUALS_REGEXP("=~"),
    NOT_EQUALS_REGEXP("!~");

    private String value;

    private RadiusOperation(String value) {
        this.value = value;
    }

/*
    private RadiusOperation(String name) {
        try {
            Field fieldName = getClass().getSuperclass().getDeclaredField("name");
            fieldName.setAccessible(true);
            fieldName.set(this, name);
            fieldName.setAccessible(false);
        } catch (Exception e) {}
        this.value = name;
    }
*/

    public static RadiusOperation getKey (String value) throws IllegalArgumentException{
        for (RadiusOperation radiusOperation : RadiusOperation.values()) {
            if (radiusOperation.getValue().equals(value)) {
                return radiusOperation;
            }
        }
        throw new IllegalArgumentException("Illegal RadiusOpertion name: [" + value + "]");
    }

    public String getValue () {
        return this.value;
    }


}
