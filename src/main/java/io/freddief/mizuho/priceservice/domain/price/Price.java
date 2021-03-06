package io.freddief.mizuho.priceservice.domain.price;

import io.freddief.mizuho.priceservice.domain.instrument.Instrument;
import io.freddief.mizuho.priceservice.domain.vendor.Vendor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Price {

    private final String id;
    private final Instrument instrument;
    private final Vendor vendor;
    private final Instant timestamp;
    private final BigDecimal price;
    private final CurrencyCode currencyCode;

    public Price(String id,
                 Instrument instrument,
                 Vendor vendor,
                 Instant timestamp,
                 BigDecimal price,
                 CurrencyCode currencyCode) {
        this.id = id;
        this.instrument = instrument;
        this.vendor = vendor;
        this.timestamp = timestamp;
        this.price = price;
        this.currencyCode = currencyCode;
    }

    public String getId() {
        return id;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return "Price{" +
            "id='" + id + '\'' +
            ", instrument=" + instrument +
            ", vendor=" + vendor +
            ", timestamp=" + timestamp +
            ", price=" + price +
            ", currencyCode=" + currencyCode +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Objects.equals(getId(), price1.getId()) &&
            Objects.equals(getInstrument(), price1.getInstrument()) &&
            Objects.equals(getVendor(), price1.getVendor()) &&
            Objects.equals(getTimestamp(), price1.getTimestamp()) &&
            Objects.equals(getPrice(), price1.getPrice()) &&
            getCurrencyCode() == price1.getCurrencyCode();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getInstrument(), getVendor(), getTimestamp(), getPrice(), getCurrencyCode());
    }
}
