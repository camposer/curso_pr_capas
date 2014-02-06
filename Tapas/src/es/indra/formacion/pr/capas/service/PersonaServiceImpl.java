package es.indra.formacion.pr.capas.service;

import java.sql.SQLException;
import java.util.List;

import es.indra.formacion.pr.capas.dao.PersonaDao;
import es.indra.formacion.pr.capas.dao.PersonaDaoImpl;
import es.indra.formacion.pr.capas.exception.ServiceException;
import es.indra.formacion.pr.capas.model.Persona;

public class PersonaServiceImpl implements PersonaService {
	private PersonaDao personaDao;
	
	public PersonaServiceImpl() throws ServiceException {
		try {
			personaDao = new PersonaDaoImpl();
		} catch (SQLException ex) {
			throw new ServiceException("Error inicializando PersonaDaoImpl", ex);
		}
	}
	
	@Override
	public void agregarPersona(Persona p) throws ServiceException {
		try {
			personaDao.agregar(p);
		} catch (SQLException ex) {
			throw new ServiceException("Error agregando persona en BD", ex);
		}
	}

	@Override
	public void modificarPersona(Persona p) throws ServiceException {
		try {
			personaDao.modificar(p);
		} catch (SQLException ex) {
			throw new ServiceException("Error modificando persona en BD", ex);
		}
	}

	@Override
	public void eliminarPersona(Integer id) throws ServiceException {
		try {
			personaDao.eliminar(id);
		} catch (SQLException ex) {
			throw new ServiceException("Error eliminando persona en BD", ex);
		}
	}

	@Override
	public List<Persona> obtenerPersonas() throws ServiceException {
		try {
			return personaDao.obtenerTodos();
		} catch (SQLException ex) {
			throw new ServiceException("Error obteniendo personas en BD", ex);
		}
	}

	@Override
	public Persona obtenerPersona(Integer id) throws ServiceException {
		try {
			return personaDao.obtener(id);
		} catch (SQLException ex) {
			throw new ServiceException("Error obteniendo persona en BD", ex);
		}
	}

	@Override
	public List<Persona> obtenerPersonasSegunEdad() throws ServiceException {
		try {
			return personaDao.obtenerPersonasSegunEdad();
		} catch (SQLException ex) {
			throw new ServiceException("Error obteniendo personas según edad en BD", ex);
		}
	}

	@Override
	public Persona obtenerPersonaMasJoven() throws ServiceException {
		try {
			return personaDao.obtenerPersonaMasJoven();
		} catch (SQLException ex) {
			throw new ServiceException("Error obteniendo persona más joven en BD", ex);
		}
	}

	@Override
	public List<Persona> obtenerPersonasSegunAltura() throws ServiceException {
		try {
			return personaDao.obtenerPersonasSegunAltura();
		} catch (SQLException ex) {
			throw new ServiceException("Error obteniendo personas segun altura en BD", ex);
		}
	}

}
