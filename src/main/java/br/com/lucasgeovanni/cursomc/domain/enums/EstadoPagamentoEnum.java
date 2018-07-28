package br.com.lucasgeovanni.cursomc.domain.enums;

public enum EstadoPagamentoEnum {
	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(2, "Cancelado");

	private int cod;
	private String descricao;

	private EstadoPagamentoEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamentoEnum toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (EstadoPagamentoEnum x : EstadoPagamentoEnum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Enum Invalido: " + cod );
	}

}
