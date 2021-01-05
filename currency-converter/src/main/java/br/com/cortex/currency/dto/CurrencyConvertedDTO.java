package br.com.cortex.currency.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyConvertedDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double value;
}
