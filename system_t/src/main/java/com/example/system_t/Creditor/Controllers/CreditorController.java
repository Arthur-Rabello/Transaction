package com.example.system_t.Creditor.Controllers;


import com.example.system_t.Creditor.Creditor;
import com.example.system_t.Creditor.Repositorys.CreditorRepository;
import com.example.system_t.Creditor.Services.CreditorService;
import com.example.system_t.Creditor.Dtos.CreditorRequestDTO;
import com.example.system_t.helper.NullAwareBeanUtilsBeans;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("creditor")
public class CreditorController {

    @Autowired
    private NullAwareBeanUtilsBeans beanUtilsBean;
    @Autowired
    private CreditorRepository creditorRepository;

    @Autowired
    private CreditorService creditorService;

    @GetMapping
    public List<Creditor> getAllCreditor(){
        return creditorService.creditorList();
    }

    @PostMapping
    public ResponseEntity<String> saveCreditor(@RequestBody @Valid Creditor data){
        creditorService.createCreditor(data);
        return ResponseEntity.ok().body("Creditor created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Creditor> getByIDCreditor(@PathVariable Long id){
        Optional<Creditor> creditor = creditorRepository.findById(id);
        if(creditor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(creditor.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCreditor(@PathVariable Long id){
        creditorService.deleteClient(id);
        return ResponseEntity.ok().body("Deleted Successfully");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCreditor(@PathVariable Long id, @RequestBody @Valid CreditorRequestDTO creditorRequestDTO){
        Optional<Creditor> creditor = creditorRepository.findById(id);
        if(creditor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Creditor not found");
        }
        var creditors = creditor.get();
        BeanUtils.copyProperties(creditorRequestDTO, creditors);
        return ResponseEntity.status(HttpStatus.OK).body(creditorRepository.save(creditors));

    }

    @SneakyThrows
    @PatchMapping("/{id}")
    public Creditor patchingCreditor(@PathVariable Long id, @RequestBody Creditor creditor){
        Optional<Creditor> creditor1 = creditorRepository.findById(id);
        var creditors = creditor1.get();
        beanUtilsBean.copyProperties(creditors, creditor);
        return creditorRepository.save(creditors);
    }

}
