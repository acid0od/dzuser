package net.odtel.dzuser.api.model;

public enum RadiusAttribute {
    CLEARTEXT_PASSWORD("Cleartext-Password", 1100),
    FRAMED_PROTOCOL("Framed-Protocol", 11),
    MPD_LIMIT("mpd-limit", 1234),
    POOL_NAME("Pool-Name", 14),
    FRAMED_MTU("Framed-MTU", 12),
    SERVICE_TYPE("Service-Type", 13),
    NAS_IP_ADDRESS("NAS-IP-Address", 4),
    FRAMED_IP_ADDRESS("Framed-IP-Address", 5),
    CISCO_AVPAIR("Cisco-AVPair",6),
    MIKROTIK_RATE_LIMIT("Mikrotik-Rate-Limit", 1444);

    private String value;
    private int key;

    private RadiusAttribute(String value, int key) {
        this.value = value;
        this.key = key;
    }

/*
    private RadiusAttribute(String name, int key) {
        try {
            Field fieldName = getClass().getSuperclass().getDeclaredField("name");
            fieldName.setAccessible(true);
            fieldName.set(this, name);
            fieldName.setAccessible(false);
        } catch (Exception e) {}
        this.value = name;
        this.key = key;
    }
*/

    public static RadiusAttribute getKey (String value) throws IllegalArgumentException{
        if (value == null) return null;
        for (RadiusAttribute radiusAttribute : RadiusAttribute.values()) {
            if (radiusAttribute.getValue().equals(value)) {
                return radiusAttribute;
            }
        }
        throw new IllegalArgumentException("Illegal RadiusAttribute name: " + value);
    }

    public String getValue () {
        return this.value;
    }
}
