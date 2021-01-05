package br.com.cortex.currency.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.cortex.currency.dto.CurrencyConvertedDTO;
import br.com.cortex.currency.dto.CurrencyCriteriaDTO;
import br.com.cortex.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ArtemisConsumer {
	
	private final CurrencyService service;
	
	@JmsListener(destination = "${convert.queue}")
	public void receive(CurrencyCriteriaDTO message) {
		CurrencyConvertedDTO convert = service.convert(message.getMoedaOrigem(), message.getMoedaDestino(), message.getValor(), message.getDataCotacao());
		log.info("The value converted is: {}", convert.getValue());
	}
}
