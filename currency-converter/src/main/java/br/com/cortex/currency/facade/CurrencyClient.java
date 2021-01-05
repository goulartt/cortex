package br.com.cortex.currency.facade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cortex.currency.dto.OriginalResponseCurrencyDTO;
import br.com.cortex.currency.enums.CoinsEnum;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CurrencyClient {
	
	private final ObjectMapper mapper;
	
	public Optional<OriginalResponseCurrencyDTO> getPrice(CoinsEnum origin, LocalDate referenceDate) {
		try {
			JsonNode body = Unirest.get(
					"https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaAberturaOuIntermediario(codigoMoeda=@codigoMoeda,dataCotacao=@dataCotacao)?@codigoMoeda='{coin}'&@dataCotacao='{referenceDate}'&$format=json")
					.routeParam("coin", origin.toString())
					.routeParam("referenceDate", referenceDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")))
					.header("Accept", "application/json;odata.metadata=minimal").asJson().getBody(); //

			return Optional.of(mapper.readValue(body.toString(), OriginalResponseCurrencyDTO.class));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
