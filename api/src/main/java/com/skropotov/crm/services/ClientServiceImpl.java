package com.skropotov.crm.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skropotov.crm.models.Client;
import com.skropotov.crm.models.Status;
import com.skropotov.crm.models.User;
import com.skropotov.crm.repositories.ClientRepository;
import com.skropotov.crm.repositories.UserRepository;
import com.skropotov.crm.transfers.ClientDto;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<Client> findAllNonDeletedClients() {
		return clientRepository.findNonDeletedClients();
	}

	@Override
	public Client findOne(Long clientId) {
		return clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client not found"));
	}

	@Override
	public void deleteClient(Long clientId) {
		Client client = findOne(clientId);
		if (client.getStatus() == Status.DELETED) {
			throw new PersistenceException("Client already deleted");
		}
		client.setStatus(Status.DELETED);
		clientRepository.save(client);
	}

	@Override
	public void createClient(ClientDto clientDto) {
		User user = null;
		Client client = clientDto.toClient();
		if (clientDto.getManagerId() != null) {
			user = userRepository.findById(clientDto.getManagerId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
		}
		client.setManager(user);
		clientRepository.save(client);
	}

	@Override
	public void editClient(Long clientId, ClientDto clientDto) {
		Client savedClient = findOne(clientId);

		Client client = clientDto.toClient();
		User user = null;
		if (clientDto.getManagerId() != null) {
			user = userRepository.findById(clientDto.getManagerId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
		}
		client.setManager(user);
		client.setId(clientId);
		client.setCreatedBy(savedClient.getCreatedBy());
		client.setCreated(savedClient.getCreated());
		client.setVersion(savedClient.getVersion());
		
		clientRepository.save(client);
	}
}
