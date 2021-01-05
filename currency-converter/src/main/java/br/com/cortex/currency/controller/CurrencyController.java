package br.com.cortex.currency.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cortex.currency.dto.CurrencyConvertedDTO;
import br.com.cortex.currency.enums.CoinsEnum;
import br.com.cortex.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {
	
	private final CurrencyService currencyService;
	
	@GetMapping("/convert")
	public CurrencyConvertedDTO convertCurrency(@RequestParam("dataCotacao") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate referenceDate,
			@RequestParam("moedaOrigem") CoinsEnum origin,
			@RequestParam("moedaDestino") CoinsEnum destiny,
			@RequestParam("valor") BigDecimal value) {
				return currencyService.convert(origin, destiny, value, referenceDate);
	
	}

}
