package com.matheusf.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.matheusf.cursomc.domain.Cidade;
import com.matheusf.cursomc.domain.Cliente;
import com.matheusf.cursomc.domain.Endereco;
import com.matheusf.cursomc.domain.enums.Perfil;
import com.matheusf.cursomc.domain.enums.TipoCliente;
import com.matheusf.cursomc.dto.ClienteDTO;
import com.matheusf.cursomc.dto.ClienteNewDTO;
import com.matheusf.cursomc.repositories.ClienteRepository;
import com.matheusf.cursomc.repositories.EnderecoRepository;
import com.matheusf.cursomc.security.UserSS;
import com.matheusf.cursomc.services.exceptions.AuthorizationException;
import com.matheusf.cursomc.services.exceptions.DataIntegrityException;
import com.matheusf.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente findById(Integer id) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Cliente insert(Cliente entity) {
		entity.setId(null);
		entity = repo.save(entity);
		enderecoRepository.saveAll(entity.getEnderecos());
		return entity;
	}
		
	public Cliente update(Integer id, Cliente obj) {
		try {
			Cliente entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
	}
	
	private void updateData(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());		
	}

	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionados");
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
	}	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}	
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()), pe.encode(dto.getSenha()));
		Cidade cid = new Cidade(dto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2() != null) {
			cli.getTelefones().add(dto.getTelefone2());
		}
		if (dto.getTelefone3() != null) {
			cli.getTelefones().add(dto.getTelefone3());
		}
		return cli;
	}
}