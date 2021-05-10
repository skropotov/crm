package com.skropotov.crm.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skropotov.crm.services.ClientService;
import com.skropotov.crm.transfers.ClientDto;

@RestController
public class ClientController {
	@Autowired
	ClientService clientService;
	
	@GetMapping("/clients")
	public List<ClientDto> getClients() {
		return clientService.findAllNonDeletedClients().stream()
				.map(x -> ClientDto.from(x))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/clients/{client-id}")
	public ClientDto getClient(@PathVariable("client-id") Long clientId) {
		return ClientDto.from(clientService.findOne(clientId));
	}
	
	@DeleteMapping("/clients/{client-id}")
	public void deleteClient(@PathVariable("client-id") Long clientId) {
		clientService.deleteClient(clientId);
	}
	
	@PostMapping("/clients")
	public void createClient(@RequestBody ClientDto clientDto) {
		clientService.createClient(clientDto);
	}
	
	@PutMapping("/clients/{client-id}")
	public void editClient(@PathVariable("client-id") Long clientId, @RequestBody ClientDto clientDto) {
		clientService.editClient(clientId, clientDto);
	}
}
