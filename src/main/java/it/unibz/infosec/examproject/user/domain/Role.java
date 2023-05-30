package it.unibz.infosec.examproject.user.domain;

public enum Role {
    CUSTOMER("customer"), VENDOR("vendor");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role fromString(String name) {
        for (final Role r : values()) {
            if (r.name.equals(name)) {
                return r;
            }
        }
        return null;
    }
}


