package com.example.system_t.Client.Services;

import com.example.system_t.Client.Client;
import com.example.system_t.Client.Repositorys.ClientRepository;
import com.example.system_t.exception.ApiErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    public List<Client> clientList(){return clientRepository.findAll();}
    public List<Client> createClient(Client data){
        if (data == null || data.getBalance() == null || data.getEmail() == null || data.getName() == null || data.getCredit_limit() == null){
            throw new ApiErrorException("The values of client can't be null");
        }
        clientRepository.save(data);
        return clientList();
    }

    public List<Client> deleteClient(Long id) {
            clientRepository.deleteById(id);
            return clientList();
    }

}
