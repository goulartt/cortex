package br.com.cortex.currency.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cortex.currency.dto.CurrencyConvertedDTO;
import br.com.cortex.currency.dto.OriginalResponseCurrencyDTO;
import br.com.cortex.currency.enums.CoinsEnum;
import br.com.cortex.currency.facade.CurrencyClient;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CurrencyService {
	
	private final CurrencyClient client;

	public CurrencyConvertedDTO convert(CoinsEnum origin, CoinsEnum destiny, Double value, LocalDate referenceDate) {

		Optional<OriginalResponseCurrencyDTO> originPrice = client.getPrice(origin, referenceDate);
		Optional<OriginalResponseCurrencyDTO> destinyPrice = client.getPrice(destiny, referenceDate);
		
		if (originPrice.isPresent() && originPrice.get().getResponse().isPresent() && destinyPrice.isPresent() && destinyPrice.get().getResponse().isPresent()) {
			
			Double finalValue = getFinalValue(origin, destiny, value, originPrice, destinyPrice);
	
			return CurrencyConvertedDTO.builder().value(finalValue).build();
		}

		return null;
	}

	private Double getFinalValue(CoinsEnum origin, CoinsEnum destiny, Double value,
			Optional<OriginalResponseCurrencyDTO> originPrice, Optional<OriginalResponseCurrencyDTO> destinyPrice) {
		
		if (CoinsEnum.BRL.equals(origin) && destinyPrice.get().getResponse().isPresent()) {
			return value * destinyPrice.get().getResponse().get().getSellPrice();
		}
		if (CoinsEnum.BRL.equals(destiny) && originPrice.get().getResponse().isPresent()) {
			return originPrice.get().getResponse().get().getSellPrice() * value;
		}
		
		Double originValue = originPrice.get().getResponse().get().getSellPrice() * value;
		Double finalValue = originValue / destinyPrice.get().getResponse().get().getSellPrice();
		
		return finalValue;
	}

}
