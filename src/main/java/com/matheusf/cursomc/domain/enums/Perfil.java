package com.matheusf.cursomc.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"), //ROLE_ é exigência do springSecurity
	CLIENTE(2, "ROLE_CLIENTE");	
	
	private int code;
	private String description;
	
	private Perfil(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Perfil toEnum(int code) {
		for (Perfil value : Perfil.values()) {
			if (value.getCode() == code	) {
				return value;
			}
		}
		throw new IllegalArgumentException("Id inválido " + code);
	}
}