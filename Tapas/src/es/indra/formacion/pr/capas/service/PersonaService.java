package es.indra.formacion.pr.capas.service;

import java.util.List;

import es.indra.formacion.pr.capas.exception.ServiceException;
import es.indra.formacion.pr.capas.model.Persona;

public interface PersonaService {
	public void agregarPersona(Persona p) throws ServiceException;
	public void modificarPersona(Persona p) throws ServiceException;
	public void eliminarPersona(Integer id) throws ServiceException;
	public List<Persona> obtenerPersonas() throws ServiceException;
	public Persona obtenerPersona(Integer id) throws ServiceException;
	public List<Persona> obtenerPersonasSegunEdad() throws ServiceException;
	public Persona obtenerPersonaMasJoven() throws ServiceException;
	public List<Persona> obtenerPersonasSegunAltura() throws ServiceException;
}
