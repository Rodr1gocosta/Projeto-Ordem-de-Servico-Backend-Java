package com.rodrigo.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.os.Repositories.PessoaRepository;
import com.rodrigo.os.Repositories.TecnicoRepository;
import com.rodrigo.os.domain.Pessoa;
import com.rodrigo.os.domain.Tecnico;
import com.rodrigo.os.dtos.TecnicoDto;
import com.rodrigo.os.service.exceptions.DataIntegratyViolationException;
import com.rodrigo.os.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
	public Tecnico create(TecnicoDto objDto) {
		if(findByCPF(objDto) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		Tecnico newObj = new Tecnico(null, objDto.getNome(), objDto.getCpf(), objDto.getTelefone(), objDto.getEndereco());
		return repository.save(newObj);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDto objDto) {
		Tecnico oldObj = findById(id);
		
		if(findByCPF(objDto) != null && findByCPF(objDto).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		else {
			oldObj.setNome(objDto.getNome());
			oldObj.setCpf(objDto.getCpf());
			oldObj.setTelefone(objDto.getTelefone());
			oldObj.setEndereco(objDto.getEndereco());
			return repository.save(oldObj);
		}
	}
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui Ordem de Serviços, não pode ser deletado!");
		}
		repository.deleteById(id);
	}
	
	private Pessoa findByCPF(TecnicoDto objDto) {
		Pessoa obj = pessoaRepository.findByCPF(objDto.getCpf());
		if(obj != null) {
			return obj;
		}
		else {
			return null;
		}
	}
	


}
