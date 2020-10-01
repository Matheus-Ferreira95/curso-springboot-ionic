package com.matheusf.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheusf.cursomc.domain.Produto;
import com.matheusf.cursomc.dto.ProdutoDTO;
import com.matheusf.cursomc.resources.utils.URL;
import com.matheusf.cursomc.services.ProdutoService;


@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(x -> new ProdutoDTO(x));		
		return ResponseEntity.ok().body(listDTO);		
	}
	/*
	apenas para fins de estudos, uma maneira de se receber as categorias em um array já, sem ser necessario
	fazer a classe auxiliar de decode
	
	@RequestParam(value = "categorias") Integer[] categorias) {	
	List<Integer> listCategorieIds = Objects.nonNull(categorias) ?   Arrays.asList(categorias) : Collections.emptyList();
	*/
}