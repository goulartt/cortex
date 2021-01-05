package br.com.cortex.currency.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OriginalResponseCurrencyDTO implements Serializable {
	@JsonProperty("@odata.context")
	private String context;
	private List<ResponseCurrencyDTO> value;
	
	@JsonIgnore
	public Optional<ResponseCurrencyDTO> getResponse() {
		if (value == null) return Optional.empty();
		return value.stream().findFirst();
	}
}
