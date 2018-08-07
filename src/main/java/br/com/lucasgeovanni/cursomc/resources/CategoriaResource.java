package br.com.lucasgeovanni.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lucasgeovanni.cursomc.domain.Categoria;
import br.com.lucasgeovanni.cursomc.dto.CategoriaDTO;
import br.com.lucasgeovanni.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService _categoriaService;

	// ResponseEntity serve para retornar uma resposta http valida
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria categoria = _categoriaService.find(id);

		return ResponseEntity.ok(categoria);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {

		categoria = _categoriaService.insert(categoria);
		// URI objeto que devolve o path que sera inseriado o novo objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) {

		categoria.setId(id);
		categoria = _categoriaService.update(categoria);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		_categoriaService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> categorias = _categoriaService.findAll();

		List<CategoriaDTO> categoriaDto = categorias.stream()
				.map(x -> new CategoriaDTO(x))
				.collect(Collectors.toList());

		return ResponseEntity.ok(categoriaDto);
	}

}
