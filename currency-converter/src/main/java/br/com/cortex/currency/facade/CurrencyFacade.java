package br.com.cortex.currency.facade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cortex.currency.dto.OriginalResponseCurrencyDTO;
import br.com.cortex.currency.enums.CoinsEnum;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyFacade {
	
	private final ObjectMapper mapper;
	
	@Cacheable(value = "convert_currency", key = "#origin+'-'+#referenceDate")
	public Optional<OriginalResponseCurrencyDTO> getPrice(CoinsEnum origin, LocalDate referenceDate) {
		try {
			log.info("Calling external service to coin {} and date {}", origin.toString(), referenceDate);
			var body = Unirest.get(
					"https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaAberturaOuIntermediario(codigoMoeda=@codigoMoeda,dataCotacao=@dataCotacao)?@codigoMoeda='{coin}'&@dataCotacao='{referenceDate}'&$format=json")
					.routeParam("coin", origin.toString())
					.routeParam("referenceDate", referenceDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")))
					.header("Accept", "application/json;odata.metadata=minimal").asJson().getBody(); //

			return Optional.of(mapper.readValue(body.toString(), OriginalResponseCurrencyDTO.class));
		} catch (JsonProcessingException e) {
			log.error("Error proceeding getPrice: {}", e);
		}
		return Optional.empty();
	}
}
