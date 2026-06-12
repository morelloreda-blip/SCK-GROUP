package sckgroup.controlador;

import sckgroup.dao.ClienteDao;
import sckgroup.dao.AbonoDao;
import sckgroup.modelo.Cliente;

import java.util.Scanner;

/**
 * Clase principal con el menu del programa.
 * Aqui se ejecuta el modulo del proyecto SCK-GROUP
 * @author Cindy Johana Ramirez
 * @version 1.0
 */
public class MenuPrincipal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ClienteDao clienteDao = new ClienteDao();
        AbonoDao abonoDao = new AbonoDao();

        int opcion;

        do {
            System.out.println("\n===== SISTEMA SCK-GROUP =====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Buscar cliente por identificacion");
            System.out.println("3. Listar clientes por ruta");
            System.out.println("4. Actualizar estado del cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Registrar abono");
            System.out.println("7. Ver historial de abonos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                Cliente cliente = new Cliente();

                System.out.print("Numero de identificacion: ");
                cliente.setNumeroIdentificacion(sc.nextLine());

                System.out.print("Nombre completo: ");
                cliente.setNombreCompleto(sc.nextLine());

                System.out.print("Telefono: ");
                cliente.setTelefono(sc.nextLine());

                System.out.print("Ciudad: ");
                cliente.setCiudad(sc.nextLine());

                System.out.print("Monto del credito: ");
                cliente.setMontoCredito(sc.nextDouble());
                sc.nextLine();

                System.out.print("Numero de dias para pagar: ");
                cliente.setNumeroDias(sc.nextInt());
                sc.nextLine();

                System.out.print("Nombre de la ruta: ");
                cliente.setNombreRuta(sc.nextLine());

                cliente.setEstadoCredito("PENDIENTE");

                clienteDao.insertarCliente(cliente);

            } else if (opcion == 2) {
                System.out.print("Ingrese el numero de identificacion: ");
                String id = sc.nextLine();
                clienteDao.buscarPorIdentificacion(id);

            } else if (opcion == 3) {
                System.out.print("Ingrese el nombre de la ruta: ");
                String ruta = sc.nextLine();
                clienteDao.listarClientesPorRuta(ruta);

            } else if (opcion == 4) {
                System.out.print("Numero de identificacion del cliente: ");
                String id = sc.nextLine();
                System.out.print("Nuevo estado (AL_DIA / DEUDOR_MOROSO / PENDIENTE): ");
                String estado = sc.nextLine();
                clienteDao.actualizarEstadoCliente(id, estado);

            } else if (opcion == 5) {
                System.out.print("Numero de identificacion del cliente a eliminar: ");
                String id = sc.nextLine();
                clienteDao.eliminarCliente(id);

            } else if (opcion == 6) {
                System.out.print("Numero de identificacion del cliente: ");
                String id = sc.nextLine();

                System.out.print("Realizo el abono? (SI / NO): ");
                String realizo = sc.nextLine();

                double valor = 0;
                if (realizo.equalsIgnoreCase("SI")) {
                    System.out.print("Valor del abono: ");
                    valor = sc.nextDouble();
                    sc.nextLine();
                }

                System.out.print("Fecha (AAAA-MM-DD): ");
                String fecha = sc.nextLine();

                abonoDao.registrarAbono(id, valor, fecha, realizo);

            } else if (opcion == 7) {
                System.out.print("Numero de identificacion del cliente: ");
                String id = sc.nextLine();
                abonoDao.consultarHistorial(id);
            }

        } while (opcion != 0);

        System.out.println("Saliendo del sistema...");
        sc.close();
    }
}