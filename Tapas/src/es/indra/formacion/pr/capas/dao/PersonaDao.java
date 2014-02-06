package es.indra.formacion.pr.capas.dao;

import java.sql.SQLException;
import java.util.List;

import es.indra.formacion.pr.capas.model.Persona;

public interface PersonaDao extends Dao<Persona, Integer> {
	public List<Persona> obtenerPersonasSegunEdad() throws SQLException;
	public Persona obtenerPersonaMasJoven() throws SQLException;
	public List<Persona> obtenerPersonasSegunAltura() throws SQLException;
}
