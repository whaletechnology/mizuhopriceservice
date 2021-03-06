package io.freddief.mizuho.priceservice.transformer;

import io.freddief.mizuho.priceservice.domain.instrument.Instrument;
import io.freddief.mizuho.priceservice.domain.price.CurrencyCode;
import io.freddief.mizuho.priceservice.domain.vendor.Vendor;
import io.freddief.mizuho.priceservice.dto.price.IgGroupPrice;
import io.freddief.mizuho.priceservice.dto.price.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class IgGroupPriceTransformerTest {

    @Mock
    private InstrumentTransformer instrumentTransformer;
    @Mock
    private VendorTransformer vendorTransformer;
    @InjectMocks
    private IgGroupPriceTransformer igGroupPriceTransformer;

    @Test
    public void transform_transformsInstrument() {

        Instrument instrument = mock(Instrument.class);
        Vendor vendor = mock(Vendor.class);
        IgGroupPrice igGroupPrice = new IgGroupPrice(
            "CODE",
            "GBP",
            BigDecimal.valueOf(12)
        );

        igGroupPriceTransformer.transform(
            igGroupPrice,
            instrument,
            vendor
        );

        verify(instrumentTransformer).toDto(instrument);
    }

    @Test
    public void transform_transformsVendor() {

        Instrument instrument = mock(Instrument.class);
        Vendor vendor = mock(Vendor.class);
        IgGroupPrice igGroupPrice = new IgGroupPrice(
            "CODE",
            "GBP",
            BigDecimal.valueOf(12)
        );

        igGroupPriceTransformer.transform(
            igGroupPrice,
            instrument,
            vendor
        );

        verify(vendorTransformer).toDto(vendor);
    }

    @Test
    public void transform_transformsPrice() {

        Instrument instrument = mock(Instrument.class);
        Vendor vendor = mock(Vendor.class);
        io.freddief.mizuho.priceservice.dto.instrument.Instrument dtoinstrument = mock(io.freddief.mizuho.priceservice.dto.instrument.Instrument.class);
        io.freddief.mizuho.priceservice.dto.vendor.Vendor dtoVendor = mock(io.freddief.mizuho.priceservice.dto.vendor.Vendor.class);
        IgGroupPrice igGroupPrice = new IgGroupPrice(
            "CODE",
            "GBP",
            BigDecimal.valueOf(12)
        );

        when(instrumentTransformer.toDto(instrument)).thenReturn(dtoinstrument);
        when(vendorTransformer.toDto(vendor)).thenReturn(dtoVendor);

        Price returned = igGroupPriceTransformer.transform(
            igGroupPrice,
            instrument,
            vendor
        );

        assertThat(returned.getId()).isNotNull();
        assertThat(returned.getTimestamp()).isNotNull();
        assertThat(returned.getCurrencyCode()).isEqualTo(CurrencyCode.GBP);
        assertThat(returned.getPrice()).isEqualTo(BigDecimal.valueOf(12));
        assertThat(returned.getVendor()).isEqualTo(dtoVendor);
        assertThat(returned.getInstrument()).isEqualTo(dtoinstrument);

    }

}