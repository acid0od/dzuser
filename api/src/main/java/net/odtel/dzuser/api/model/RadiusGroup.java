package net.odtel.dzuser.api.model;

public class RadiusGroup {

    private Long id;
    private String groupname;
    private RadiusAttribute attribute;
    private RadiusOperation operator;
    private String value;

    public RadiusGroup() {
    }

    public RadiusGroup(String groupname, RadiusAttribute attribute, RadiusOperation operator, String value) {
        this.groupname = groupname;
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

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
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
