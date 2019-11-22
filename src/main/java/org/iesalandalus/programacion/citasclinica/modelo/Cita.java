package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Cita {

	public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm:ss";
	private LocalDateTime fechaHora;
	private Paciente paciente;

	public Cita(Paciente paciente, LocalDateTime fechaHora) {

		setPaciente(paciente);
		setFechaHora(fechaHora);
	}

	public Cita(Cita cita) {

		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		} else {
			setPaciente(cita.getPaciente());
			setFechaHora(cita.getFechaHora());
		}
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {

		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}

		this.fechaHora = LocalDateTime.of(
				LocalDate.of(fechaHora.getYear(), fechaHora.getMonth(), fechaHora.getDayOfMonth()),
				LocalTime.of(fechaHora.getHour(), fechaHora.getMinute(), fechaHora.getSecond()));

	}

	public Paciente getPaciente() {
		return paciente;
	}

	private void setPaciente(Paciente paciente) {

		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		} else {
			this.paciente = new Paciente(paciente);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaHora == null) ? 0 : fechaHora.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// Comprobaciones
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cita))
			return false;
		// Comparamos
		Cita otro = (Cita) obj;
		if (this.getFechaHora().equals(otro.getFechaHora()))
			return true;
		return false;

	}

	@Override
	public String toString() {
		// fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA));
		return String.format("%s, fechaHora=%s", getPaciente(),
				getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
	}

}
