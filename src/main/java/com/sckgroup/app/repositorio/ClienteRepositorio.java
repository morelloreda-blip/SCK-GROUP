package com.sckgroup.app.repositorio;

import com.sckgroup.app.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio del Cliente.
 * Spring Boot genera automaticamente los metodos para
 * insertar, consultar, actualizar y eliminar (CRUD)
 * gracias a la interfaz JpaRepository.
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

    /**
     * Metodo para buscar un cliente por su numero de identificacion.
     * RF8: Consultar numero de identificacion.
     * Spring genera la consulta SQL automaticamente
     * a partir del nombre del metodo.
     */
    Cliente findByNumeroIdentificacion(String numeroIdentificacion);

}