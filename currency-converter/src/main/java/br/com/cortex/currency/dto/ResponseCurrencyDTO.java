package br.com.cortex.currency.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseCurrencyDTO implements Serializable {
	
	@JsonProperty("cotacaoCompra")
	private double buyPrice;
	@JsonProperty("cotacaoVenda")
	private double sellPrice;
	@JsonProperty("dataHoraCotacao")
	private String dateTimePrice;
	@JsonProperty("tipoBoletim")
	private String boletimType;
	
}
