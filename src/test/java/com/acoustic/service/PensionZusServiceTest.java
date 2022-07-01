package com.acoustic.service;

import com.acoustic.rate.RatesConfigurationProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class PensionZusServiceTest {

    @InjectMocks
    private PensionZusService salaryCalculatorService;

    @Mock
    private RatesConfigurationProperties ratesConfigurationProperties;

    @Test
    void getDescription() {
        assertThat(salaryCalculatorService.getDescription()).isEqualTo("Total zus");
    }

    @ParameterizedTest
    @CsvSource({"6000, 90.00, 0.0150", "7000, 105.00, 0.0150", "15143.99,227.16, 0.0150"})
    public void getTotalZus(BigDecimal input, BigDecimal expected, BigDecimal rate) {
        given(ratesConfigurationProperties.getPensionZusRate()).willReturn(rate);
        assertThat(salaryCalculatorService.apply(input)).isEqualTo(expected);
    }
}