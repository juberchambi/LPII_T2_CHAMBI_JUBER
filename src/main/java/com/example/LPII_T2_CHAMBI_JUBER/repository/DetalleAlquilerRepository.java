package com.example.LPII_T2_CHAMBI_JUBER.repository;

import com.example.LPII_T2_CHAMBI_JUBER.model.DetalleAlquiler;
import com.example.LPII_T2_CHAMBI_JUBER.model.DetalleAlquilerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleAlquilerRepository extends JpaRepository<DetalleAlquiler, DetalleAlquilerId> {

}
