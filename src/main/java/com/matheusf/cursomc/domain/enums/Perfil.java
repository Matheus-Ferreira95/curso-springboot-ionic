package com.matheusf.cursomc.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(int cod) {
		for (Perfil value : Perfil.values()) {
			if (value.getCod() == cod) {
				return value;
			}
		}
		throw new IllegalArgumentException("Perfil cliente inválido");
	}	
}
