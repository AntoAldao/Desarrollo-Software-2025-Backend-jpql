package managers;

import org.example.Articulo;
import org.example.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticuloManager {
    EntityManagerFactory emf = null;
    EntityManager em = null;

    public ArticuloManager(boolean anularShowSQL) {
        Map<String, Object> properties = new HashMap<>();
        if(anularShowSQL){
            properties.put("hibernate.show_sql", "false");
        }else{
            properties.put("hibernate.show_sql", "true");
        }
        emf = Persistence.createEntityManagerFactory("example-unit", properties);
        em = emf.createEntityManager();
    }

    //Ejercicio 12: Listar los Artículos filtrando por código parcial

    public List<Articulo> getArticulosPorCodigoParcial(String codigo){
        String jpql = "SELECT a FROM Articulo a WHERE a.codigo LIKE :codigo";
        Query query = em.createQuery(jpql);
        query.setParameter("codigo", "%" + codigo + "%");
        return query.getResultList();
    }

    // Ejercicio 13: Listar todos los Artículos cuyo precio sea mayor que el promedio de los precios de todos los Artículos
    public List<Articulo> getArticulosPrecioMayorPromedio(){
        String jpql = "SELECT a FROM Articulo a WHERE a.precioVenta > (SELECT AVG(a2.precioVenta) FROM Articulo a2)";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    // Ejercicio 14 (ejemplo de EXISTS): Obtener Categorías que tienen al menos un Artículo con precio mayor a un umbral
    // Demuestra el uso de EXISTS en JPQL
    public List<Categoria> getCategoriasConArticuloConPrecioMayor(Double precio){
        String jpql = "SELECT c FROM Categoria c WHERE EXISTS (SELECT a FROM Articulo a WHERE a MEMBER OF c.articulos AND a.precioVenta > :precio)";
        Query query = em.createQuery(jpql);
        query.setParameter("precio", precio);
        return query.getResultList();
    }

    public void cerrarEntityManager(){
        em.close();
        emf.close();
    }
}
