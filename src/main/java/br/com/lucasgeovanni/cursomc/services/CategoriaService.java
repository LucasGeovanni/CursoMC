package br.com.lucasgeovanni.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucasgeovanni.cursomc.domain.Categoria;
import br.com.lucasgeovanni.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository _categoriaRepository;

	public Categoria buscar(Integer Id) {

		Optional<Categoria> categoria = _categoriaRepository.findById(Id);

		return categoria.orElse(null);
	}

}
