package br.com.lucasgeovanni.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucasgeovanni.cursomc.domain.Categoria;
import br.com.lucasgeovanni.cursomc.domain.Cliente;
import br.com.lucasgeovanni.cursomc.repositories.ClienteRepository;
import br.com.lucasgeovanni.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository _clienteRepository;

	// Na versão 1.5x do spring-boot: para buscar um objeto pelo id se usa o
	// findOne(id);
	// Na versão 2.0x do spring-boot: para buscar um objeto pelo id se usa o
	// findById(id) e se retorna um objeto Optional ;

	public Cliente buscar(Integer Id) {

		Optional<Cliente> cliente = _clienteRepository.findById(Id);

		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + Id + ", Tipo: " + Cliente.class.getName()));
	}

}
