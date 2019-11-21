package org.iesalandalus.programacion.citasclinica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {

	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_TELEFONO = "[96]?[0-9]{8}";

	private String nombre;
	private String dni;
	private String telefono;

	public Paciente(String nombre, String dni, String telefono) {

		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Paciente(Paciente paciente) {

		if (paciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		} else {
			setNombre(paciente.getNombre());
			setDni(paciente.getDni());
			setTelefono(paciente.getTelefono());
		}
	}

	private String formateaNombre(String nombre) {
		nombre = nombre.trim();
		String[] trozosNombre = nombre.split(" ");
		String devolver = "", aux = "";
		for (int i = 0; i < trozosNombre.length; i++) {
			if (!trozosNombre[i].equals("")) {
				aux = trozosNombre[i];
				devolver += aux.substring(0, 1).toUpperCase();
				devolver += aux.substring(1, aux.length()).toLowerCase();
				devolver += " ";
			}
		}
		return devolver.trim();
	}

	private boolean comprobarLetraDni(String dni) {

		Pattern patron;
		Matcher comparador;

		patron = Pattern.compile(ER_DNI);
		comparador = patron.matcher(dni);

		if (comparador.matches()) {
			int numero = Integer.parseInt(comparador.group(1));
			String letra = comparador.group(2);
			switch (numero % 23) {
			case 0:
				if (letra.toUpperCase().equals("T"))
					return true;
				break;
			case 1:
				if (letra.toUpperCase().equals("R"))
					return true;
				break;
			case 2:
				if (letra.toUpperCase().equals("w"))
					return true;
				break;
			case 3:
				if (letra.toUpperCase().equals("A"))
					return true;
				break;
			case 4:
				if (letra.toUpperCase().equals("G"))
					return true;
				break;
			case 5:
				if (letra.toUpperCase().equals("M"))
					return true;
				break;
			case 6:
				if (letra.toUpperCase().equals("Y"))
					return true;
				break;
			case 7:
				if (letra.toUpperCase().equals("F"))
					return true;
				break;
			case 8:
				if (letra.toUpperCase().equals("P"))
					return true;
				break;
			case 9:
				if (letra.toUpperCase().equals("D"))
					return true;
				break;
			case 10:
				if (letra.toUpperCase().equals("X"))
					return true;
				break;
			case 11:
				if (letra.toUpperCase().equals("B"))
					return true;
				break;
			case 12:
				if (letra.toUpperCase().equals("N"))
					return true;
				break;
			case 13:
				if (letra.toUpperCase().equals("J"))
					return true;
				break;
			case 14:
				if (letra.toUpperCase().equals("Z"))
					return true;
				break;
			case 15:
				if (letra.toUpperCase().equals("S"))
					return true;
				break;
			case 16:
				if (letra.toUpperCase().equals("Q"))
					return true;
				break;
			case 17:
				if (letra.toUpperCase().equals("V"))
					return true;
				break;
			case 18:
				if (letra.toUpperCase().equals("H"))
					return true;
				break;
			case 19:
				if (letra.toUpperCase().equals("L"))
					return true;
				break;
			case 20:
				if (letra.toUpperCase().equals("C"))
					return true;
				break;
			case 21:
				if (letra.toUpperCase().equals("K"))
					return true;
				break;
			case 22:
				if (letra.toUpperCase().equals("E"))
					return true;
				break;
			}
		}
		return false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		}
		nombre = nombre.trim();
		if (nombre.equals("")) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		} else {
			this.nombre = formateaNombre(nombre);
		}

	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		}
		dni = dni.trim();
		if (dni.equals("")) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		} else {
			Pattern patron;
			Matcher comparador;

			patron = Pattern.compile(ER_DNI);
			comparador = patron.matcher(dni);
			if (comparador.matches()) {
				if (comprobarLetraDni(dni)) {
					this.dni = dni.substring(0, 8) + dni.substring(8, 9).toUpperCase();
				} else {
					throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
				}
			} else {
				throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
			}
		}

	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {

		if (telefono == null)
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		telefono = telefono.trim();
		if (telefono.equals("")) {

			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");

		} else {
			Pattern patron;
			Matcher comparador;

			patron = Pattern.compile(ER_TELEFONO);
			comparador = patron.matcher(telefono);
			if (comparador.matches()) {
				this.telefono = telefono;
			} else {
				throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// Comprobaciones
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Paciente))
			return false;
		// Comparamos
		Paciente otro = (Paciente) obj;
		if (this.getDni().equals(otro.getDni()))
			return true;
		return false;

	}

	private String getIniciales() {

		String nombre = getNombre();
		String[] trozosNombre = nombre.split(" ");
		String devolver = "";

		for (int i = 0; i < trozosNombre.length; i++) {
			devolver += trozosNombre[i].substring(0, 1);
		}
		return devolver;
	}

	@Override
	public String toString() {
		return String.format("nombre=%s (%s), DNI=%s, teléfono=%s", getNombre(), getIniciales(), getDni(),
				getTelefono());
	}

}
