package br.com.cortex.currency.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CurrencyConvertedDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal value;
}
