package br.com.lucasgeovanni.cursomc.domain.enums;

public enum TipoClienteEnum {

	PESSOAFISICA(1, "Pessoa Fisíca"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TipoClienteEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoClienteEnum toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoClienteEnum x : TipoClienteEnum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Enum Invalido: " + cod );
	}
}
