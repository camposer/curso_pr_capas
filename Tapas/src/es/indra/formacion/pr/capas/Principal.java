package es.indra.formacion.pr.capas;

import es.indra.formacion.pr.capas.exception.ServiceException;
import es.indra.formacion.pr.capas.gui.Menu;

public class Principal {
	public static void main(String[] args) {
		try {
			new Menu().iniciar();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
