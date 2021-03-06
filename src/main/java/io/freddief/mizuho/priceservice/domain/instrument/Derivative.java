package io.freddief.mizuho.priceservice.domain.instrument;

import java.util.Objects;

import static io.freddief.mizuho.priceservice.domain.instrument.InstrumentType.DERIVATIVE;

public class Derivative implements Instrument {

    private static final InstrumentType INSTRUMENT_TYPE = DERIVATIVE;

    private final String id;
    private final String code;

    public Derivative(String id, String code) {
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
    public InstrumentType getType() {
        return INSTRUMENT_TYPE;
    }

    @Override
    public String toString() {
        return "Derivative{" +
            "id='" + id + '\'' +
            ", code='" + code + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Derivative that = (Derivative) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCode());
    }
}
