package com.rodrigo.os.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.os.Repositories.OSRepository;
import com.rodrigo.os.domain.Cliente;
import com.rodrigo.os.domain.OS;
import com.rodrigo.os.domain.Tecnico;
import com.rodrigo.os.domain.enuns.Prioridade;
import com.rodrigo.os.domain.enuns.Status;
import com.rodrigo.os.dtos.OSDto;
import com.rodrigo.os.service.exceptions.ObjectNotFoundException;

@Service
public class OSService {

	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	
	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", tipo: " + OS.class.getName()));
	}

	public List<OS> findAll() {
		return repository.findAll();
	}
	
	public OS create(@Valid OSDto obj) {
		return fromDto(obj);
	}
	
	public OS update(@Valid OSDto obj) {
		findById(obj.getId());
		return fromDto(obj);
	}
	
	private OS fromDto(OSDto obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}

	
}