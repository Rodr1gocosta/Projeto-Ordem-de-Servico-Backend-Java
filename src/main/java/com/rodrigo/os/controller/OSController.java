package com.rodrigo.os.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.os.dtos.OSDto;
import com.rodrigo.os.service.OSService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OSController {
	@Autowired
	private OSService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OSDto> findByID(@PathVariable Integer id) {
		OSDto obj = new OSDto(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<OSDto>> findAll() {
		List<OSDto> listDto = service.findAll().stream().map(obj -> new OSDto(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<OSDto> create(@Valid @RequestBody OSDto obj) {
		obj = new OSDto(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<OSDto> update(@Valid @RequestBody OSDto obj) {
		obj = new OSDto(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
}
