package br.com.cortex.currency.controller;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cortex.currency.dto.CurrencyConvertedDTO;
import br.com.cortex.currency.dto.CurrencyCriteriaDTO;
import br.com.cortex.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {
	
	private final CurrencyService currencyService;
	private final JmsTemplate template;
	
	@Value("${convert.queue}")
	private String queue;
	
	@GetMapping("/convert")
	public CurrencyConvertedDTO convertCurrency(CurrencyCriteriaDTO criteria) {
				return currencyService.convert(criteria.getMoedaOrigem(), criteria.getMoedaDestino(), criteria.getValor(), criteria.getDataCotacao());
	
	}
	
	@GetMapping("/produce")
	public void produceMessage(CurrencyCriteriaDTO criteria) {
		log.info("Producing message to artemis consumer on queue: {}", queue);
		template.convertAndSend(queue, criteria, (Message jmsMessage) -> {
            jmsMessage.setJMSPriority(criteria.getPriority());
            return jmsMessage;
        });
	}

}
