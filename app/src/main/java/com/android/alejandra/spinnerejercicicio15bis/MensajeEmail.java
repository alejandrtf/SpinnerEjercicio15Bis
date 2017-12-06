package com.android.alejandra.spinnerejercicicio15bis;

public class MensajeEmail {
	private String nombre;
	private String email;
	private String asunto;
	private String mensaje;

	public MensajeEmail(String nombre, String email, String asunto,
			String mensaje) {
		if (nombre.length() == 0)
			this.nombre = "";
		else
			this.nombre = nombre;
		this.email = email;
		if (asunto.length() == 0)
			this.asunto = "";
		else
			this.asunto = asunto;
		if (mensaje.length() == 0)
			this.mensaje = "";
		else
			this.mensaje = mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
