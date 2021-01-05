package br.com.cortex.currency.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cortex.currency.dto.CurrencyConvertedDTO;
import br.com.cortex.currency.dto.OriginalResponseCurrencyDTO;
import br.com.cortex.currency.enums.CoinsEnum;
import br.com.cortex.currency.facade.CurrencyFacade;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyService {

	private final CurrencyFacade client;

	public CurrencyConvertedDTO convert(CoinsEnum origin, CoinsEnum destiny, BigDecimal value, LocalDate referenceDate) {
		
		if (origin.equals(destiny)) return new CurrencyConvertedDTO(value);
		
		Optional<OriginalResponseCurrencyDTO> originPrice = client.getPrice(origin, referenceDate);
		Optional<OriginalResponseCurrencyDTO> destinyPrice = client.getPrice(destiny, referenceDate);

		return getFinalValue(origin, destiny, value, originPrice, destinyPrice);

	}

	private CurrencyConvertedDTO getFinalValue(CoinsEnum origin, CoinsEnum destiny, BigDecimal value,
			Optional<OriginalResponseCurrencyDTO> originPrice, Optional<OriginalResponseCurrencyDTO> destinyPrice) {

		if (CoinsEnum.BRL.equals(origin) && destinyPrice.get().getResponse().isPresent()) {
			return new CurrencyConvertedDTO(value.divide(destinyPrice.get().getResponse().get().getSellPrice(), RoundingMode.HALF_UP));
		}
		if (CoinsEnum.BRL.equals(destiny) && originPrice.get().getResponse().isPresent()) {
			return new CurrencyConvertedDTO(originPrice.get().getResponse().get().getSellPrice().multiply(value));
		}

		if (originPrice.isPresent() && originPrice.get().getResponse().isPresent() && destinyPrice.isPresent()
				&& destinyPrice.get().getResponse().isPresent()) {

			BigDecimal originValue = originPrice.get().getResponse().get().getSellPrice().multiply(value);
			BigDecimal finalValue = originValue.divide(destinyPrice.get().getResponse().get().getSellPrice(), RoundingMode.HALF_UP);
			return new CurrencyConvertedDTO(finalValue);
		}
		
		return null;
	}

}
