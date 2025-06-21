package com.example.LPII_T2_CHAMBI_JUBER.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;



import com.example.LPII_T2_CHAMBI_JUBER.model.Alquiler;
import com.example.LPII_T2_CHAMBI_JUBER.model.DetalleAlquiler;
import com.example.LPII_T2_CHAMBI_JUBER.model.DetalleAlquilerId;
import com.example.LPII_T2_CHAMBI_JUBER.model.Pelicula;
import com.example.LPII_T2_CHAMBI_JUBER.model.EstadoAlquiler;
import com.example.LPII_T2_CHAMBI_JUBER.model.Cliente;

import com.example.LPII_T2_CHAMBI_JUBER.repository.AlquilerRepository;
import com.example.LPII_T2_CHAMBI_JUBER.repository.DetalleAlquilerRepository;
import com.example.LPII_T2_CHAMBI_JUBER.repository.PeliculaRepository;
@Service
public class AlquilerService {

    private final AlquilerRepository alquilerRepo;
    private final DetalleAlquilerRepository detalleRepo;
    private final PeliculaRepository peliculaRepo;

    public AlquilerService(AlquilerRepository ar, DetalleAlquilerRepository dr, PeliculaRepository pr) {
        this.alquilerRepo = ar;
        this.detalleRepo = dr;
        this.peliculaRepo = pr;
    }

    public List<Pelicula> listarPeliculasConStock() {
        return peliculaRepo.findAll().stream()
            .filter(p -> p.getStock() > 0)
            .collect(Collectors.toList());
    }

    public Alquiler registrarAlquiler(Long clienteId, Long peliculaId, int cantidad) {
        Cliente cli = new Cliente();
        cli.setId(clienteId); // o fetch si quieres verificar existencia

        Pelicula peli = peliculaRepo.findById(peliculaId)
            .orElseThrow(() -> new IllegalArgumentException("Película no encontrada"));

        if (peli.getStock() < cantidad) throw new IllegalArgumentException("Stock insuficiente");

        Alquiler alq = new Alquiler();
        alq.setCliente(cli);
        alq.setFecha(LocalDate.now());
        alq.setEstado(EstadoAlquiler.ACTIVO);
        alq.setTotal(cantidad);
        alq = alquilerRepo.save(alq);

        DetalleAlquiler det = new DetalleAlquiler();
        det.setAlquiler(alq);
        det.setPelicula(peli);
        det.setCantidad(cantidad);
        det.setId(new DetalleAlquilerId(alq.getId(), peli.getIdPelicula()));
        detalleRepo.save(det);

        peli.setStock(peli.getStock() - cantidad);
        peliculaRepo.save(peli);

        return alq;
    }
    public List<Alquiler> obtenerTodosAlquileres() {
    return alquilerRepo.findAll();
}

public Alquiler obtenerAlquilerPorId(Long id) {
    return alquilerRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Alquiler no encontrado"));
}

}
