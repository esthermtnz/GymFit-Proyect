/**
 * Este fichero muestra todo lo que tiene que ver con la clase Main
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.*;
import gui.controladores.*;
import maintester.gymmain;
import aplicacion.Gimnasio;
/**
 * esta es una clase que representa Main
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Main {
	/**
	 * El ejecutable para que se cree el gimnasio
	 * @param args los argumentos recibidos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {						
					Window window = new Window();
					try {
						File direccion = new File("./resources");
						File archivo = new File(direccion, "gimnasio.txt");
					    Controlador controlador = new Controlador(window, Gimnasio.getGimnasio().cargarAplicacion(archivo));
						window.setControlador(controlador);
						window.setVisible(true);
					} catch (FileNotFoundException e1) {
						Controlador controlador = new Controlador(window,Gimnasio.getGimnasio());
						window.setControlador(controlador);
						window.setVisible(true);
					} catch (IOException | ClassNotFoundException e) {
					    e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}