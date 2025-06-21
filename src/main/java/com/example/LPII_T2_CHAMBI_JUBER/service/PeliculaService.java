package com.example.LPII_T2_CHAMBI_JUBER.service;

import com.example.LPII_T2_CHAMBI_JUBER.model.Pelicula;
import com.example.LPII_T2_CHAMBI_JUBER.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service // Marca esta clase como un "servicio" de Spring, para que sea manejada como un componente
public class PeliculaService {

    // Inyección del repositorio que permite interactuar con la base de datos Create Read Update Delete
    private final PeliculaRepository peliculaRepository;

    // Constructor para inyectar el repositorio (Spring lo hace automáticamente)
    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    // Método que devuelve todas las películas almacenadas en la base de datos
    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll(); // Usa el método findAll del repositorio
    }

    // Método que guarda una nueva película o actualiza una existente
    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.save(pelicula); // Usa el método save del repositorio
    }

    public Optional<Pelicula> obtenerPorId(Long id) {
    return peliculaRepository.findById(id);
}

public void eliminar(Long id) {
    peliculaRepository.deleteById(id);
}

}

