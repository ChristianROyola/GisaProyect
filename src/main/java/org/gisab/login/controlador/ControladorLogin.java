package org.gisab.login.controlador;

import javax.persistence.Entity;

@Entity
public class ControladorLogin 
{
	private String nombre;
	private String usuario;
	private String password;
	private String salt;
		
	public void ingresouser()
	{
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "ControladorLogin [nombre=" + nombre + ", usuario=" + usuario + ", password=" + password + ", salt="
				+ salt + "]";
	}
	
}
