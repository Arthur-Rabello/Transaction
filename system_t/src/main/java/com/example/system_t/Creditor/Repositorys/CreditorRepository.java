package com.example.system_t.Creditor.Repositorys;


import com.example.system_t.Creditor.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditorRepository extends JpaRepository <Creditor, Long> {

}
