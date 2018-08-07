package br.com.lucasgeovanni.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lucasgeovanni.cursomc.domain.Categoria;
import br.com.lucasgeovanni.cursomc.repositories.CategoriaRepository;
import br.com.lucasgeovanni.cursomc.services.exceptions.DataIntegrityException;
import br.com.lucasgeovanni.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository _categoriaRepository;

	// Na versão 1.5x do spring-boot: para buscar um objeto pelo id se usa o
	// findOne(id);
	// Na versão 2.0x do spring-boot: para buscar um objeto pelo id se usa o
	// findById(id) e se retorna um objeto Optional ;

	public Categoria find(Integer Id) {

		Optional<Categoria> categoria = _categoriaRepository.findById(Id);

		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + Id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return _categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {

		find(categoria.getId());
		return _categoriaRepository.save(categoria);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			_categoriaRepository.deleteById(id);

		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}                                                                                                                                                                                                                                                    



}
