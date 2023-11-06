package com.example.system_t.Transaction.Controllers;


import com.example.system_t.Transaction.Dtos.TransactionRequestDTO;
import com.example.system_t.Transaction.Transaction;
import com.example.system_t.Transaction.Repositorys.TransactionRepository;
import com.example.system_t.Transaction.Services.TransactionService;
import com.example.system_t.helper.NullAwareBeanUtilsBeans;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private NullAwareBeanUtilsBeans beanUtilsBean;

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransaction() {return transactionService.transactionList();}


    @GetMapping("/{id}")
    public ResponseEntity<String> getTransactionByClientId(@PathVariable Long id) {
        transactionService.transactionListById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Transaction got successfully");
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id , @RequestBody TransactionRequestDTO data) {
        Optional <Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
        }
        var transactions = transaction.get();
        BeanUtils.copyProperties(data, transactions);

        transactionRepository.save(transactions);
        return ResponseEntity.status(HttpStatus.OK).body("Transaction updated successfully");
    }


    @PostMapping
    List<Transaction> createTransaction(@RequestBody Transaction data) {

        return  transactionService.createTransaction(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().body("Transaction deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTransaction() {

        transactionRepository.deleteAll();

        return ResponseEntity.ok().body("All transaction was deleted successfully");
    }

    @SneakyThrows
    @PatchMapping("/{id}")
    public Transaction patchingTransaction(@PathVariable Long id, @RequestBody Transaction transaction){
        Optional<Transaction> transactionExist = transactionRepository.findById(id);
        var transactions = transactionExist.get();
        beanUtilsBean.copyProperties(transactions, transaction);
        return transactionRepository.save(transactions);
    }



}





