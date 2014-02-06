package es.indra.formacion.pr.capas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.indra.formacion.pr.capas.model.Persona;

public class PersonaDaoImpl implements PersonaDao {
	private Connection con;
	
	static {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PersonaDaoImpl() throws SQLException {
		con = DriverManager.getConnection(URL, USUARIO, CLAVE);
	}

	@Override
	public void agregar(Persona p) throws SQLException {
		String query = "INSERT INTO persona"
				+ "(nombre, apellido, fecha_nacimiento, altura) "
				+ "VALUES(?, ?, ?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, p.getNombre());
		pstmt.setString(2, p.getApellido());
		pstmt.setDate(3, p.getFechaNacimientoBd());
		pstmt.setFloat(4, p.getAltura());
		
		pstmt.executeUpdate();
	}

	@Override
	public void modificar(Persona p) throws SQLException {
		String query = "UPDATE persona"
				+ "SET nombre = ?, apellido = ?, fecha_nacimiento=?, altura=? "
				+ "WHERE id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, p.getNombre());
		pstmt.setString(2, p.getApellido());
		pstmt.setDate(3, p.getFechaNacimientoBd());
		pstmt.setFloat(4, p.getAltura());
		pstmt.setInt(5, p.getId());
		
		pstmt.executeUpdate();
	}

	@Override
	public void eliminar(Integer id) throws SQLException {
		String query = "DELETE FROM persona WHERE id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, id);
		
		pstmt.executeUpdate();
	}

	@Override
	public List<Persona> obtenerTodos() throws SQLException {
		String query = "SELECT * FROM persona";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		return obtenerListaPersonas(rs);
	}
	
	private List<Persona> obtenerListaPersonas(ResultSet rs) throws SQLException {
		List<Persona> personas = null;
		
		while(rs.next()) {
			if (personas == null)
				personas = new ArrayList<Persona>();
				
			Persona p = new Persona(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("apellido"),
					rs.getDate("fecha_nacimiento"),
					rs.getFloat("altura")
				);
			personas.add(p);
		}

		return personas;
	}

	@Override
	public Persona obtener(Integer id) throws SQLException {
		String query = "SELECT * FROM persona WHERE id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, id);

		List<Persona> personas = obtenerListaPersonas(pstmt.getResultSet()); 
		
		return (personas != null)?personas.get(0):null;
	}
	
	@Override
	public List<Persona> obtenerPersonasSegunEdad() throws SQLException {
		String query = "SELECT * FROM persona ORDER BY fecha_nacimiento ASC";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		return obtenerListaPersonas(rs);
	}

	@Override
	public Persona obtenerPersonaMasJoven() throws SQLException {
		String query = "SELECT * FROM persona ORDER BY fecha_nacimiento DESC OFFSET 1 ROWS FETCH NEXT ROW ONLY";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		List<Persona> personas = obtenerListaPersonas(rs); 
		
		return (personas != null)?personas.get(0):null;
	}

	@Override
	public List<Persona> obtenerPersonasSegunAltura() throws SQLException {
		String query = "SELECT * FROM persona ORDER BY altura ASC";
			
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		return obtenerListaPersonas(rs);
	}
	
}
