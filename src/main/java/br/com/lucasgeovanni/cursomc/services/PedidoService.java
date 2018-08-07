package br.com.lucasgeovanni.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucasgeovanni.cursomc.domain.Categoria;
import br.com.lucasgeovanni.cursomc.domain.Pedido;
import br.com.lucasgeovanni.cursomc.repositories.PedidoRepository;
import br.com.lucasgeovanni.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository _pedidoRepository;

	// Na versão 1.5x do spring-boot: para buscar um objeto pelo id se usa o
	// findOne(id);
	// Na versão 2.0x do spring-boot: para buscar um objeto pelo id se usa o
	// findById(id) e se retorna um objeto Optional ;

	public Pedido find(Integer Id) {

		Optional<Pedido> Pedido = _pedidoRepository.findById(Id);

		return Pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + Id + ", Tipo: " + Pedido.class.getName()));
	}

}
