package com.example.system_t.Creditor.Services;


import com.example.system_t.Creditor.Creditor;
import com.example.system_t.Creditor.Repositorys.CreditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CreditorService {
    @Autowired
    private CreditorRepository creditorRepository;

    public List<Creditor> creditorList(){return creditorRepository.findAll();}

    public List<Creditor> createCreditor(Creditor data){
        creditorRepository.save(data);
        return creditorList();
    }

    public List<Creditor> deleteClient(Long id) {
        creditorRepository.deleteById(id);
        return creditorList();
    }

}
