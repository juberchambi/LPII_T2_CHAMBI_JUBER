package com.example.LPII_T2_CHAMBI_JUBER.repository;

import com.example.LPII_T2_CHAMBI_JUBER.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

}
