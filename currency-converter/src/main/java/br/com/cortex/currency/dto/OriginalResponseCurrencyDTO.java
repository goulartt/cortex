package br.com.cortex.currency.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OriginalResponseCurrencyDTO implements Serializable {

	private static final long serialVersionUID = 5915980743726939087L;
	
	@JsonProperty("@odata.context")
	private String context;
	private List<ResponseCurrencyDTO> value;
	
	@JsonIgnore
	public Optional<ResponseCurrencyDTO> getResponse() {
		if (value == null) return Optional.empty();
		return value.stream().findFirst();
	}
}
