package io.freddief.mizuho.priceservice.domain.vendor;

import java.util.Objects;

public class Vendor {

    private final String id;
    private final String name;

    public Vendor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return Objects.equals(getId(), vendor.getId()) &&
            Objects.equals(getName(), vendor.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName());
    }
}
