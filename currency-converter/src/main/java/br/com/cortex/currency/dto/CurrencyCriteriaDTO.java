package br.com.cortex.currency.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.cortex.currency.enums.CoinsEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyCriteriaDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1281808353830056807L;

	private CoinsEnum moedaOrigem;
	private CoinsEnum moedaDestino;
	private BigDecimal valor;
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private LocalDate dataCotacao;
	
	private int priority;
	
	public int getPriority() {
		if (priority < 1 || priority > 9) 
			return 4;
		return priority;
	}

}
