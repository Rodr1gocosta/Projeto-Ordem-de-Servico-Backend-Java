package com.rodrigo.os;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.os.Repositories.ClienteRepository;
import com.rodrigo.os.Repositories.OSRepository;
import com.rodrigo.os.Repositories.TecnicoRepository;
import com.rodrigo.os.domain.Cliente;
import com.rodrigo.os.domain.OS;
import com.rodrigo.os.domain.Tecnico;
import com.rodrigo.os.domain.enuns.Prioridade;
import com.rodrigo.os.domain.enuns.Status;

@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OSRepository osRepository;
	
	public void instaciaDB() {
		Tecnico t1 = new Tecnico(null, "Rodrigo Costa", "770.859.180-55", "(61) 99408-5634","Asa Norte");
		Tecnico t2 = new Tecnico(null, "Lucas Felipe", "472.152.640-76", "(61) 99408-5234","Asa Sul");
		Tecnico t3 = new Tecnico(null, "Fabio Moura", "765.991.920-25", "(61) 98408-3212","Lago Sul");
		
		Cliente c1 = new Cliente(null, "Betina Campos", "856.561.720-31", "(61) 99408-2550","Sobradinho");
		Cliente c2 = new Cliente(null, "Gustavo Araújo", "359.422.370-18", "(61) 99345-2530","Noroeste");
		
		
		OS os1 = new OS(null, Prioridade.ALTA, "Teste Create 00", Status.ANDAMENTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3));
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		osRepository.saveAll(Arrays.asList(os1));

	}
}
