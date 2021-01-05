package br.com.cortex.currency.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cortex.currency.dto.CurrencyConvertedDTO;
import br.com.cortex.currency.dto.OriginalResponseCurrencyDTO;
import br.com.cortex.currency.enums.CoinsEnum;
import br.com.cortex.currency.facade.CurrencyClient;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CurrencyService {
	
	private final CurrencyClient client;

	public CurrencyConvertedDTO convert(CoinsEnum origin, CoinsEnum destiny, Double value, LocalDate referenceDate) {

		Optional<OriginalResponseCurrencyDTO> originPrice = client.getPrice(origin, referenceDate);
		Optional<OriginalResponseCurrencyDTO> destinyPrice = client.getPrice(destiny, referenceDate);
		
		if (originPrice.isPresent() && originPrice.get().getResponse().isPresent() && destinyPrice.isPresent() && destinyPrice.get().getResponse().isPresent()) {
			Double originValue = originPrice.get().getResponse().get().getSellPrice() * value;
			Double finalValue = originValue / destinyPrice.get().getResponse().get().getSellPrice();
			return CurrencyConvertedDTO.builder().value(finalValue).build();
		}

		return null;
	}

}
