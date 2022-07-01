package com.acoustic.controller;


import com.acoustic.entity.SicknessZus;
import com.acoustic.rate.RatesConfigurationProperties;
import com.acoustic.repository.SicknessZusRepository;
import com.acoustic.service.SalaryCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/sicknessZus")
@RequiredArgsConstructor
@Validated
public class SicknessZusController {

    private final SicknessZusRepository sicknessZusRepository;
    private final SalaryCalculatorService salaryCalculatorService;
    private final RatesConfigurationProperties ratesConfigurationProperties;


    @PostMapping("/getSicknessZus/{grossMonthlySalary}")
    public Map<String, BigDecimal> calculateSicknessZus(@PathVariable @Min(2000)BigDecimal grossMonthlySalary){
        var totalZus = salaryCalculatorService.apply(grossMonthlySalary);
        this.sicknessZusRepository.save(SicknessZus.builder().sicknessZusAmount(totalZus).sicknessZusRate(ratesConfigurationProperties.getSicknessZusRate()).build());
        return new LinkedHashMap<>(Map.of(salaryCalculatorService.getDescription(), totalZus));
    }
}
