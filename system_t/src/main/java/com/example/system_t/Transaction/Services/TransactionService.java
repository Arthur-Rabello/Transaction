package com.example.system_t.Transaction.Services;


import com.example.system_t.Transaction.Repositorys.TransactionRepository;
import com.example.system_t.Transaction.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> transactionList(){
        return transactionRepository.findAll();
    }
    public List<Transaction> deleteTransaction (Long id){
        transactionRepository.deleteById(id);
        return transactionList();

    }

    public ResponseEntity<Transaction> transactionListById(Long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return ResponseEntity.ok().body(transaction.get());
    }

    public List<Transaction> createTransaction(Transaction data) {
        transactionRepository.save(data);
        return transactionList();
    }

}
