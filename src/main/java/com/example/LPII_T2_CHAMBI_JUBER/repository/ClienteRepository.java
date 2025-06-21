package com.example.LPII_T2_CHAMBI_JUBER.repository;

import com.example.LPII_T2_CHAMBI_JUBER.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
