package es.indra.formacion.pr.capas.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<TIPO, CLAVE> {
	public static final String URL = "jdbc:derby://localhost:1527/persona";
	public static final String USUARIO = "user";
	public static final String CLAVE = "123";

	public void agregar(TIPO obj) throws SQLException;
	public void modificar(TIPO obj) throws SQLException;
	public void eliminar(CLAVE id) throws SQLException;
	public List<TIPO> obtenerTodos() throws SQLException;
	public TIPO obtener(CLAVE id) throws SQLException;

}
