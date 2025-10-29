package managers;

import org.example.Articulo;
import org.example.FacturaDetalle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacturaDetalleManager {
    EntityManagerFactory emf = null;
    EntityManager em = null;

    public FacturaDetalleManager(boolean anularShowSQL) {
        Map<String, Object> properties = new HashMap<>();
        if (anularShowSQL) {
            properties.put("hibernate.show_sql", "false");
        } else {
            properties.put("hibernate.show_sql", "true");
        }
        emf = Persistence.createEntityManagerFactory("example-unit", properties);
        em = emf.createEntityManager();
    }

    // Ejercicio 7: Listar los artículos vendidos en una factura
    public List<Articulo> getArticulosPorFactura(Long facturaId) {
        String jpql = "SELECT DISTINCT d.articulo FROM FacturaDetalle d WHERE d.factura.id = :facturaId";
        Query query = em.createQuery(jpql);
        query.setParameter("facturaId", facturaId);
        List<Articulo> articulos = query.getResultList();
        return articulos;
    }

    // Ejercicio 8: Obtener el artículo más caro vendido en una factura
    public Articulo getArticuloMasCaroPorFactura(Long facturaId) {
        String jpql = "SELECT d.articulo FROM FacturaDetalle d " +
                "WHERE d.factura.id = :facturaId " +
                "AND d.precioUnitario = (" +
                "SELECT MAX(d2.precioUnitario) FROM FacturaDetalle d2 WHERE d2.factura.id = :facturaId)";
        Query query = em.createQuery(jpql);
        query.setParameter("facturaId", facturaId);
        Articulo articulo = (Articulo) query.getSingleResult();
        return articulo;
    }

    public void cerrarEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}