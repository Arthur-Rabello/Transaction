package com.example.system_t.Client.Controllers;


import com.example.system_t.Client.Client;
import com.example.system_t.Client.Repositorys.ClientRepository;
import com.example.system_t.Client.Services.ClientService;
import com.example.system_t.Client.dtos.RequestClientDTO;
import com.example.system_t.exception.ApiErrorException;
import com.example.system_t.exception.ApiSuccessException;
import com.example.system_t.helper.NullAwareBeanUtilsBeans;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("client")
@Validated
public class ClientController {


    @Autowired
    private NullAwareBeanUtilsBeans beanUtilsBean;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @GetMapping
    public List<Client> getAllClient(){return clientService.clientList();}

    @GetMapping("/{id}")
    public ResponseEntity<Client> getOneClient(@PathVariable Long id){
        Optional<Client> client = clientRepository.findById(id);
        if(client == null) throw new ApiErrorException("Client id don't exist");
        return ResponseEntity.ok(client.get());
        }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody @Valid RequestClientDTO requestClientDTO){
        Optional<Client> clients = clientRepository.findById(id);
        if(clients.isEmpty()){
           throw new ApiErrorException("this client don't exist"); }

        var client = clients.get();
        BeanUtils.copyProperties(requestClientDTO, client);
        clientRepository.save(client);
        throw new ApiSuccessException("Client updated successfully");

    }

    @PostMapping
    public ResponseEntity<String> saveClient(@RequestBody @Valid Client data){
        clientService.createClient(data);
        return ResponseEntity.ok().body("Client created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        if (clientRepository.findById(id).isEmpty()){ throw new ApiErrorException("Client doesn't exist");

        }
        clientService.deleteClient(id);
        return ResponseEntity.ok().body("Deleted successfully");
    }
    @SneakyThrows
    @PatchMapping("/{id}")
    public Client patchingCreditor(@PathVariable Long id, @RequestBody Client client){
        Optional<Client> client1 = clientRepository.findById(id);
        var clients = client1.get();
        beanUtilsBean.copyProperties(clients, client);
        return clientRepository.save(clients);
    }

}
