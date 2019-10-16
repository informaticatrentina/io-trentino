package it.tndigit.iot.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.codehaus.jackson.map.ext.JodaDeserializers;
import org.codehaus.jackson.map.ext.JodaSerializers;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class CommonDTO implements Serializable {

	/**
	 * 
	 */

	protected Long idObj;

	public CommonDTO() {
	}

	public CommonDTO(Long idObj) {
		this.idObj = idObj;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	protected Integer version;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String erroreImprevisto;
//
//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	@JsonFormat(pattern = "YYYY-MM-dd HH:mm")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonIgnore
	private LocalDateTime dataModifica;

//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	@JsonFormat(pattern = "YYYY-MM-dd HH:mm")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonIgnore
	private LocalDateTime dataInserimento;


	public Long getIdObj() {
		return idObj;
	}

	public void setIdObj(Long idObj) {
		this.idObj = idObj;
	}


	public String getErroreImprevisto() {
		return erroreImprevisto;
	}

	public void setErroreImprevisto(String erroreImprevisto) {
		this.erroreImprevisto = erroreImprevisto;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LocalDateTime getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(LocalDateTime dataModifica) {
		this.dataModifica = dataModifica;
	}

	public LocalDateTime getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDateTime dataInserimento) {
		this.dataInserimento = dataInserimento;
	}


}


