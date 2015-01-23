package net.odtel.usercheck.domain;

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

    public String getValue() {
        return this.value;
    }

}
