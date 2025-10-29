package managers;

import funciones.FuncionApp;
import org.example.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainConsultasJPQL {

    public static void main(String[] args) {
        //REPOSITORIO-> https://github.com/gerardomagni/jpqlquerys.git

        //buscarFacturas();
        //buscarFacturas();
        //buscarFacturasActivas();
        //buscarFacturasXNroComprobante();
        //buscarFacturasXRangoFechas();
        //buscarFacturaXPtoVentaXNroComprobante();
        //buscarFacturasXCliente();
        //buscarFacturasXCuitCliente();
        //buscarFacturasXArticulo();
        //mostrarMaximoNroFactura();
        //buscarClientesXIds();
        //buscarClientesXRazonSocialParcial();
        

        // Ejercicio 1
        buscarClientes();
        // Ejercicio 2
        buscarFacturasUltimoMes();

        // Ejercicios 3
        obtenerClienteConMasFacturas();
        // Ejercicio 4
        listarArticulosMasVendidos();

        // Ejercicio 13
        buscarArticulosPrecioMayorPromedio();
        // Ejercicio 14
        ejemplificarExists();
        //Ejercicios 9 y 10
        contarTotalFacturas();
        listarFacturasMayoresaXMonto();
    }


    public static void buscarFacturas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturas();
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasActivas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasActivas();
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXNroComprobante(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXNroComprobante(796910l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXRangoFechas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaInicio = FuncionApp.restarSeisMeses(fechaActual);
            List<Factura> facturas = mFactura.buscarFacturasXRangoFechas(fechaInicio, fechaActual);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturaXPtoVentaXNroComprobante(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Factura factura = mFactura.getFacturaXPtoVentaXNroComprobante(2024, 796910l);
            mostrarFactura(factura);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXCliente(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXCliente(7l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXCuitCliente(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXCuitCliente("27236068981");
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXArticulo(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXArticulo(6l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void mostrarMaximoNroFactura(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Long nroCompMax = mFactura.getMaxNroComprobanteFactura();
            System.out.println("N° " + nroCompMax);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarClientesXIds(){
        ClienteManager mCliente = new ClienteManager(true);
        try {
            List<Long> idsClientes = new ArrayList<>();
            idsClientes.add(1l);
            idsClientes.add(2l);
            List<Cliente> clientes = mCliente.getClientesXIds(idsClientes);
            for(Cliente cli : clientes){
                System.out.println("Id: " + cli.getId());
                System.out.println("CUIT: " + cli.getCuit());
                System.out.println("Razon Social: " + cli.getRazonSocial());
                System.out.println("-----------------");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mCliente.cerrarEntityManager();
        }
    }

    public static void buscarClientesXRazonSocialParcial(){
        ClienteManager mCliente = new ClienteManager(true);
        try {
            List<Long> idsClientes = new ArrayList<>();
            idsClientes.add(1l);
            idsClientes.add(2l);
            List<Cliente> clientes = mCliente.getClientesXRazonSocialParcialmente("Lui");
            for(Cliente cli : clientes){
                System.out.println("Id: " + cli.getId());
                System.out.println("CUIT: " + cli.getCuit());
                System.out.println("Razon Social: " + cli.getRazonSocial());
                System.out.println("-----------------");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mCliente.cerrarEntityManager();
        }
    }

    public static void buscarClientes(){
        System.out.println("----- buscarClientes -----");
        ClienteManager mCliente = new ClienteManager(true);
        try {
            List<Cliente> clientes = mCliente.getClientes();
            mostrarClientes(clientes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mCliente.cerrarEntityManager();
        }
    }

    public static void buscarFacturasUltimoMes(){
        System.out.println("----- buscarFacturasUltimoMes -----");
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasUltimoMes();
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    // Ejercicio 3: Obtener el cliente que ha generado más facturas
    public static void obtenerClienteConMasFacturas(){
        ClienteManager mCliente = new ClienteManager(true);
        try {
            Cliente cliente = mCliente.getClienteConMasFacturas();
            System.out.println("\n=== EJERCICIO 3: CLIENTE CON MAS FACTURAS ===");
            System.out.println("Id: " + cliente.getId());
            System.out.println("CUIT: " + cliente.getCuit());
            System.out.println("Razon Social: " + cliente.getRazonSocial());
            System.out.println("*************************\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mCliente.cerrarEntityManager();
        }
    }

    // Ejercicio 4: Listar los artículos más vendidos
    public static void listarArticulosMasVendidos(){
        FacturaManager mFactura = new FacturaManager(false);
        try {
            List<Object[]> resultados = mFactura.getArticulosMasVendidos();
            System.out.println("\n=== EJERCICIO 4: ARTICULOS MAS VENDIDOS ===");
            if(resultados.isEmpty()){
                System.out.println("No hay artículos vendidos.");
            } else {
                for(Object[] resultado : resultados){
                    Articulo articulo = (Articulo) resultado[0];
                    Long totalVendido = ((Number) resultado[1]).longValue();
                    System.out.println("Articulo: " + articulo.getDenominacion());
                    System.out.println("Codigo: " + articulo.getCodigo());
                    System.out.println("Cantidad Total Vendida: " + totalVendido + " unidades");
                    System.out.println("-----------------");
                }
            }
            System.out.println("*************************\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mFactura.cerrarEntityManager();
        }
    }

    //Ejercicio 9: Cantidad total de facturas generadas en el sistema
    public static void contarTotalFacturas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Long cantidadFacturas = mFactura.getCantidadFacturasTotal();
            System.out.println("\n=== EJERCICIO 9: CANTIDAD TOTAL DE FACTURAS ===");
            System.out.println("Cantidad Total de Facturas: " + cantidadFacturas);
            System.out.println("*************************\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mFactura.cerrarEntityManager();
        }
    }

    //Ejercicio 10: Listar facturas mayores a un monto dado
    public static void listarFacturasMayoresaXMonto() {
        FacturaManager mFactura = new FacturaManager(true);
        try{
            List<Factura> facturas = mFactura.getFacturasMayoresaXMonto(3000.0);
            System.out.println("\n=== EJERCICIO 10: FACTURAS MAYORES A $" + 3000 + " ===");
            mostrarFacturas(facturas);
            System.out.println("*************************\n");
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void mostrarFactura(Factura factura){
        List<Factura> facturas = new ArrayList<>();
        facturas.add(factura);
        mostrarFacturas(facturas);
    }

    public static void mostrarFacturas(List<Factura> facturas){
        for(Factura fact : facturas){
            System.out.println("N° Comp: " + fact.getStrProVentaNroComprobante());
            System.out.println("Fecha: " + FuncionApp.formatLocalDateToString(fact.getFechaComprobante()));
            System.out.println("CUIT Cliente: " + FuncionApp.formatCuitConGuiones(fact.getCliente().getCuit()));
            System.out.println("Cliente: " + fact.getCliente().getRazonSocial() + " ("+fact.getCliente().getId() + ")");
            System.out.println("------Articulos------");
            for(FacturaDetalle detalle : fact.getDetallesFactura()){
                System.out.println(detalle.getArticulo().getDenominacion() + ", " + detalle.getCantidad() + " unidades, $" + FuncionApp.getFormatMilDecimal(detalle.getSubTotal(), 2));
            }
            System.out.println("Total: $" + FuncionApp.getFormatMilDecimal(fact.getTotal(),2));
            System.out.println("*************************");
        }
    }

    public static void mostrarClientes(List<Cliente> clientes){
        for(Cliente cli : clientes){
            System.out.println("Id: " + cli.getId());
            System.out.println("CUIT: " + cli.getCuit());
            System.out.println("Razon Social: " + cli.getRazonSocial());
            System.out.println("-----------------");
        }
    }

    // Ejercicio 13: Mostrar artículos cuyo precio de venta es mayor que el promedio
    public static void buscarArticulosPrecioMayorPromedio(){
        System.out.println("----- buscarArticulosPrecioMayorPromedio -----");
        ArticuloManager mArticulo = new ArticuloManager(true);
        try{
            List<Articulo> articulos = mArticulo.getArticulosPrecioMayorPromedio();
            if(articulos.isEmpty()){
                System.out.println("No hay artículos por encima del promedio.");
            } else {
                for(Articulo a : articulos){
                    System.out.println("Id: " + a.getId() + " - " + a.getDenominacion() + " - Precio: $" + a.getPrecioVenta());
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            mArticulo.cerrarEntityManager();
        }
    }

    // Ejercicio 14: Ejemplo de EXISTS en JPQL
    // Busca categorías que tienen al menos un artículo con precio mayor a un umbral
    public static void ejemplificarExists(){
        System.out.println("Explicación: La cláusula EXISTS se usa para verificar si una subconsulta devuelve al menos una fila.\n" +
                "\n" +
                "Si la subconsulta devuelve una o más filas, EXISTS retorna verdadero (TRUE).\n" +
                "\n" +
                "Si la subconsulta no devuelve filas, EXISTS retorna falso (FALSE).\n" +
                "\n" +
                "Por lo tanto, EXISTS se utiliza frecuentemente en condiciones de WHERE para comprobar la existencia de registros relacionados en otra tabla.");
        System.out.println("----- ejemplificarExists (Categorias con artículos caros) -----");
        ArticuloManager mArticulo = new ArticuloManager(true);
        try{
            Double umbral = 1000d; // ejemplo: 1000 pesos
            List<Categoria> categorias = mArticulo.getCategoriasConArticuloConPrecioMayor(umbral);
            if(categorias.isEmpty()){
                System.out.println("No hay categorías con artículos por encima de $" + umbral);
            } else {
                for(Categoria c : categorias){
                    System.out.println("Categoria: " + c.getDenominacion() + " (id=" + c.getId() + ")");
                }
            }
            System.out.println("(Consulta usando EXISTS en una subconsulta JPQL)");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            mArticulo.cerrarEntityManager();
        }
    }
}
