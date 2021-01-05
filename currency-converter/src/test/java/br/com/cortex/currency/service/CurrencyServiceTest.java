package br.com.cortex.currency.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cortex.currency.dto.CurrencyConvertedDTO;
import br.com.cortex.currency.dto.OriginalResponseCurrencyDTO;
import br.com.cortex.currency.dto.ResponseCurrencyDTO;
import br.com.cortex.currency.enums.CoinsEnum;
import br.com.cortex.currency.facade.CurrencyFacade;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceTest {
	
	@Mock
	private CurrencyFacade client;
	
	@InjectMocks
	private CurrencyService currencyService;
	
	
	@Test
	public void convertEURtoUSDTest() {
		LocalDate now = LocalDate.now();
		Mockito.when(client.getPrice(CoinsEnum.EUR, now))
			.thenReturn(Optional.of(OriginalResponseCurrencyDTO.builder()
					.value(Collections.singletonList(ResponseCurrencyDTO.builder()
							.sellPrice(new BigDecimal(6)).build()))
					.build()));
		Mockito.when(client.getPrice(CoinsEnum.USD, now))
		.thenReturn(Optional.of(OriginalResponseCurrencyDTO.builder()
				.value(Collections.singletonList(ResponseCurrencyDTO.builder()
						.sellPrice(new BigDecimal(5)).build()))
				.build()));
		
		var convert = currencyService.convert(CoinsEnum.EUR, CoinsEnum.USD, new BigDecimal(1000), now);
		assertEquals(1200.0, convert.getValue().doubleValue());
		
	}
	
	@Test
	public void convertEURtoEURTest() {
		LocalDate now = LocalDate.now();
		var convert = currencyService.convert(CoinsEnum.EUR, CoinsEnum.EUR, new BigDecimal(1000), now);
		assertEquals(1000.0, convert.getValue().doubleValue());
		
	}
	
	@Test
	public void convertBRLtoEURTest() {
		LocalDate now = LocalDate.now();
		Mockito.when(client.getPrice(CoinsEnum.BRL, now))
			.thenReturn(Optional.of(OriginalResponseCurrencyDTO.builder()
					.value(Collections.emptyList())
					.build()));
		Mockito.when(client.getPrice(CoinsEnum.EUR, now))
		.thenReturn(Optional.of(OriginalResponseCurrencyDTO.builder()
				.value(Collections.singletonList(ResponseCurrencyDTO.builder()
						.sellPrice(new BigDecimal(10)).build()))
				.build()));
		
		var convert = currencyService.convert(CoinsEnum.BRL, CoinsEnum.EUR, new BigDecimal(1000), now);
		assertEquals(100.0, convert.getValue().doubleValue());
		
	}
	
	@Test
	public void convertEURtoBRLTest() {
		LocalDate now = LocalDate.now();
		Mockito.when(client.getPrice(CoinsEnum.BRL, now))
			.thenReturn(Optional.of(OriginalResponseCurrencyDTO.builder()
					.value(Collections.emptyList())
					.build()));
		Mockito.when(client.getPrice(CoinsEnum.EUR, now))
		.thenReturn(Optional.of(OriginalResponseCurrencyDTO.builder()
				.value(Collections.singletonList(ResponseCurrencyDTO.builder()
						.sellPrice(new BigDecimal(10)).build()))
				.build()));
		
		var convert = currencyService.convert(CoinsEnum.EUR, CoinsEnum.BRL, new BigDecimal(1000), now);
		assertEquals(10000.0, convert.getValue().doubleValue());
		
	}


}
