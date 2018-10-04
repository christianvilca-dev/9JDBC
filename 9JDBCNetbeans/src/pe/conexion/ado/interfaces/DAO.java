/*
 * Interfaz general que nos permite realizar nuestro proceso independiente
 * de cual BD se esta trabajando
 */
package pe.conexion.ado.interfaces;

import java.util.List;

/**
 *
 * @author Christian
 */
public interface DAO<K, O> {
    public void insert(O o);
    public void modificar(O o);
    public void eliminar(O o);
    public O obtenerPorId(K k);
    public List<O> listar();
}
