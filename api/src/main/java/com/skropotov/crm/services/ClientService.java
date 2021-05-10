package com.skropotov.crm.services;

import java.util.List;

import com.skropotov.crm.models.Client;
import com.skropotov.crm.transfers.ClientDto;

public interface ClientService {
	List<Client> findAllNonDeletedClients();
	Client findOne(Long clientId);
	
	void deleteClient(Long clientId);
	void createClient(ClientDto clientDto);
	void editClient(Long clientId, ClientDto clientDto);
}
