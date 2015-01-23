package net.odtel.usercheck.domain;

public class RadiusUserValue {

    private Long id;
    private String username;
    private RadiusAttribute attribute;
    private RadiusOperation operator;
    private String value;

    public RadiusUserValue() {

    }

    public RadiusUserValue(String username, RadiusAttribute attribute, RadiusOperation operator, String value) {
        this.username = username;
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RadiusAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(RadiusAttribute attribute) {
        this.attribute = attribute;
    }

    public RadiusOperation getOperator() {
        return operator;
    }

    public void setOperator(RadiusOperation operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
