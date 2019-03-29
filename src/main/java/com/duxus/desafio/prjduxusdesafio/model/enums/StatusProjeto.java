package com.duxus.desafio.prjduxusdesafio.model.enums;

public enum StatusProjeto {
	
	CONCLUIDO(1,"Projeto Conluído"),
	PENDENTE(2,"Em Desenvolvimento");
	
	private StatusProjeto(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	private int cod;
	private String descricao;
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static StatusProjeto toEnum(Integer cod) {		
		if (cod == null) {
			return null;
		}
		
		for(StatusProjeto x : StatusProjeto.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
	
}
