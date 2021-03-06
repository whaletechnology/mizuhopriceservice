package io.freddief.mizuho.priceservice.dto.instrument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Stock implements Instrument {

    private final String id;
    private final String code;

    @JsonCreator
    public Stock(@JsonProperty("id") String id,
                 @JsonProperty("code") String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Stock{" +
            "id='" + id + '\'' +
            ", code='" + code + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(getId(), stock.getId()) &&
            Objects.equals(getCode(), stock.getCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCode());
    }
}
