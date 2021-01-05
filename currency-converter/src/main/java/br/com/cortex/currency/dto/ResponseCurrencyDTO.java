package br.com.cortex.currency.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCurrencyDTO implements Serializable {

	private static final long serialVersionUID = -6281914183581265334L;
	
	@JsonProperty("cotacaoCompra")
	private BigDecimal buyPrice;
	@JsonProperty("cotacaoVenda")
	private BigDecimal sellPrice;
	@JsonProperty("dataHoraCotacao")
	private String dateTimePrice;
	@JsonProperty("tipoBoletim")
	private String boletimType;
	
}
