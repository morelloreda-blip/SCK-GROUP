package com.sckgroup.app.controlador;

import com.sckgroup.app.modelo.Cliente;
import com.sckgroup.app.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controlador principal del modulo de clientes del proyecto SCK-GROUP.
 * Aqui se manejan las rutas (URL) de la aplicacion web.
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
@Controller
public class ClienteControlador {

    // Spring inyecta automaticamente el repositorio
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    /**
     * Pagina principal del sistema.
     * Ruta: GET /
     */
    @GetMapping("/")
    public String mostrarMenu() {
        return "index";
    }

    /**
     * Muestra el formulario para registrar un nuevo cliente.
     * RF5: Registro clientes nuevos.
     * Ruta: GET /registrar
     */
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model modelo) {
        modelo.addAttribute("cliente", new Cliente());
        return "registrar";
    }

    /**
     * Guarda el cliente nuevo en la base de datos.
     * Ruta: POST /guardarCliente
     */
    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute Cliente cliente, Model modelo) {

        // Se establece el estado inicial del credito
        cliente.setEstadoCredito("PENDIENTE");

        // El metodo save() del repositorio inserta el registro
        clienteRepositorio.save(cliente);

        modelo.addAttribute("mensaje", "Cliente registrado correctamente.");
        return "resultado";
    }

    /**
     * Muestra el formulario para consultar un cliente.
     * RF8: Consultar numero de identificacion.
     * Ruta: GET /consultar
     */
    @GetMapping("/consultar")
    public String mostrarFormularioConsulta() {
        return "consultar";
    }

    /**
     * Busca un cliente por su numero de identificacion.
     * Ruta: GET /buscarCliente
     */
    @GetMapping("/buscarCliente")
    public String buscarCliente(@RequestParam String identificacion, Model modelo) {

        Cliente cliente = clienteRepositorio.findByNumeroIdentificacion(identificacion);

        if (cliente != null) {
            modelo.addAttribute("encontrado", true);
            modelo.addAttribute("cliente", cliente);
        } else {
            modelo.addAttribute("encontrado", false);
        }

        return "consultaResultado";
    }

    /**
     * Lista todos los clientes registrados en el sistema.
     * RF3: Administrar base de datos.
     * Ruta: GET /listar
     */
    @GetMapping("/listar")
    public String listarClientes(Model modelo) {
        List<Cliente> listaClientes = clienteRepositorio.findAll();
        modelo.addAttribute("clientes", listaClientes);
        return "listar";
    }

    /**
     * Elimina un cliente segun su id.
     * RF3: Administrar base de datos.
     * Ruta: GET /eliminar
     */
    @GetMapping("/eliminar")
    public String eliminarCliente(@RequestParam int id) {
        clienteRepositorio.deleteById(id);
        return "redirect:/listar";
    }
}