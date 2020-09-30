package com.nelioalves.cursomc.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timeStamp;	
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	
	public StandardError(Instant timeStamp, Integer status, String error, String message, String path) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
		this.setError(error);
		this.setPath(path);
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMsg(String message) {
		this.message = message;
	}
	
	public Instant getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Instant timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}	
}