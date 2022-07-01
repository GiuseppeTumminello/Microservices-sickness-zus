package com.acoustic.controller;


import com.acoustic.entity.PensionZus;
import com.acoustic.rate.RatesConfigurationProperties;
import com.acoustic.repository.PensionZusRepository;
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
@RequestMapping("/pensionZus")
@RequiredArgsConstructor
@Validated
public class TotalZusController {

    private final PensionZusRepository pensionZusRepository;
    private final SalaryCalculatorService salaryCalculatorService;
    private final RatesConfigurationProperties ratesConfigurationProperties;


    @PostMapping("/getPensionZus/{grossMonthlySalary}")
    public Map<String, BigDecimal> calculateTotalZus(@PathVariable @Min(2000)BigDecimal grossMonthlySalary){
        var totalZus = salaryCalculatorService.apply(grossMonthlySalary);
        this.pensionZusRepository.save(PensionZus.builder().pensionZusAmount(totalZus).pensionZusRate(ratesConfigurationProperties.getPensionZusRate()).build());
        return new LinkedHashMap<>(Map.of(salaryCalculatorService.getDescription(), totalZus));
    }
}
