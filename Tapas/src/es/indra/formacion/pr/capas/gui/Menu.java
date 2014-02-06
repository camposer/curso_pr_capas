package es.indra.formacion.pr.capas.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import es.indra.formacion.pr.capas.exception.ServiceException;
import es.indra.formacion.pr.capas.model.Persona;
import es.indra.formacion.pr.capas.service.PersonaService;
import es.indra.formacion.pr.capas.service.PersonaServiceImpl;

public class Menu {
	public Scanner scanner;
	private PersonaService personaService;
	
	public Menu() throws ServiceException {
		this.scanner = new Scanner(System.in);
		this.personaService = new PersonaServiceImpl();
	}
	
	public void iniciar() throws ServiceException {
		while(true) {
			System.out.println();
			System.out.println("1. Agregar una persona");	
			System.out.println("2. Lista de personas según altura (asc)");
			System.out.println("3. Lista de personas según edad (desc)");
			System.out.println("4. Persona más joven");
			System.out.println("5. Salir");
			System.out.println("? ");
			
			String opcion = scanner.nextLine();
			if (opcion.equals("1")) 
				agregarPersona();
			else if (opcion.equals("2")) 
				listarPersonasSegunAltura();
			else if (opcion.equals("3")) 
				listarPersonasSegunEdad();
			else if (opcion.equals("4"))
				listarPersonaMasJoven();
			else if (opcion.equals("5"))
				break;
		}		
	}

	private void listarPersonaMasJoven() {
		try {
			Persona p = personaService.obtenerPersonaMasJoven();
			
			System.out.println(p);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void listarPersonasSegunEdad() {
		try {
			List<Persona> personas = personaService.obtenerPersonasSegunEdad();
			
			for (Persona p : personas) 
				System.out.println(p);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void listarPersonasSegunAltura() {
		try {
			List<Persona> personas = personaService.obtenerPersonasSegunAltura();
			
			for (Persona p : personas) 
				System.out.println(p);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
	}

	private void agregarPersona() {
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		
		System.out.print("Apellido: ");
		String apellido = scanner.nextLine();
		
		System.out.print("Fecha de Nacimiento (yyyy-MM-dd): ");
		String sfechaNacimiento = scanner.nextLine();
		Date fechaNacimiento = null;
		try {
			fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd")
				.parse(sfechaNacimiento);
		} catch(Exception e) {}
		
		System.out.print("Altura: ");
		String saltura = scanner.nextLine();
		Float altura = null;
		try {
			altura = Float.parseFloat(saltura);
		} catch(Exception e) {}
		
		Persona p = new Persona(nombre, apellido, fechaNacimiento, altura);
		try {
			personaService.agregarPersona(p);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
