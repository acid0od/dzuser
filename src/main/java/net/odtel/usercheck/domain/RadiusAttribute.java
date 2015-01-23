package net.odtel.usercheck.domain;

public enum RadiusAttribute {
    CLEARTEXT_PASSWORD("Cleartext-Password", 1100),
    NAS_IP_ADDRESS("NAS-IP-Address", 4),
    MIKROTIK_RATE_LIMIT("Mikrotik-Rate-Limit", 1444);

    private String value;
    private int key;

    private RadiusAttribute(String value, int key) {
        this.value = value;
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

}
