package br.com.cortex.currency.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseCurrencyDTO implements Serializable {

	private static final long serialVersionUID = -6281914183581265334L;
	
	@JsonProperty("cotacaoCompra")
	private double buyPrice;
	@JsonProperty("cotacaoVenda")
	private double sellPrice;
	@JsonProperty("dataHoraCotacao")
	private String dateTimePrice;
	@JsonProperty("tipoBoletim")
	private String boletimType;
	
}
