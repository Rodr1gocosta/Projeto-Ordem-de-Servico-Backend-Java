package com.rodrigo.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.os.Repositories.PessoaRepository;
import com.rodrigo.os.Repositories.ClienteRepository;
import com.rodrigo.os.domain.Pessoa;
import com.rodrigo.os.domain.Cliente;
import com.rodrigo.os.dtos.ClienteDto;
import com.rodrigo.os.service.exceptions.DataIntegratyViolationException;
import com.rodrigo.os.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente create(ClienteDto objDto) {
		if(findByCPF(objDto) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		Cliente newObj = new Cliente(null, objDto.getNome(), objDto.getCpf(), objDto.getTelefone());
		return repository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDto objDto) {
		Cliente oldObj = findById(id);
		
		if(findByCPF(objDto) != null && findByCPF(objDto).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		else {
			oldObj.setNome(objDto.getNome());
			oldObj.setCpf(objDto.getCpf());
			oldObj.setTelefone(objDto.getTelefone());
			return repository.save(oldObj);
		}
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui Ordem de Serviços, não pode ser deletado!");
		}
		repository.deleteById(id);
	}
	
	private Pessoa findByCPF(ClienteDto objDto) {
		Pessoa obj = pessoaRepository.findByCPF(objDto.getCpf());
		if(obj != null) {
			return obj;
		}
		else {
			return null;
		}
	}
	


}
