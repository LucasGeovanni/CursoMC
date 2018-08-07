package br.com.lucasgeovanni.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasgeovanni.cursomc.domain.Pedido;
import br.com.lucasgeovanni.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService _pedidoService;

	// ResponseEntity serve para retornar uma resposta http valida
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Pedido pedido = _pedidoService.buscar(id);

		return ResponseEntity.ok(pedido);
	}

}