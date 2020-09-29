package com.nelioalves.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int code;
	private String description;
	
	private EstadoPagamento(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String description() {
		return description;
	}
	
	public static EstadoPagamento toEnum(int code) {
		for (EstadoPagamento value : EstadoPagamento.values()) {
			if (value.getCode() == code	) {
				return value;
			}
		}
		throw new IllegalArgumentException("Id inválido " + code);
	}
}