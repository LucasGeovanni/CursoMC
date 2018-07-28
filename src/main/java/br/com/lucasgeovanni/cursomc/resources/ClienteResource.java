package br.com.lucasgeovanni.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasgeovanni.cursomc.domain.Cliente;
import br.com.lucasgeovanni.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService _clienteService;
	//ResponseEntity serve para retornar uma resposta http valida
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Cliente cliente = _clienteService.buscar(id);
		
		return ResponseEntity.ok(cliente);
	}

}