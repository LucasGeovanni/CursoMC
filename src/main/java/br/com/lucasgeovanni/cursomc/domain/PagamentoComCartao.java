package br.com.lucasgeovanni.cursomc.domain;

import javax.persistence.Entity;
import br.com.lucasgeovanni.cursomc.domain.enums.EstadoPagamentoEnum;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public PagamentoComCartao() {

	}

	public PagamentoComCartao(Integer id, EstadoPagamentoEnum estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);

		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
}
