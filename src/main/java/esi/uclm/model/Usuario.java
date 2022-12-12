package esi.uclm.model;

import java.util.regex.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.codec.digest.DigestUtils;


@Document("Usuario")
public class Usuario {
	@Id
	private String email;
	private String dni;
	private String nombre;
	private String apellido;
	private String password;
	private String rol;


	// Esto valdrá para validar el email
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public Usuario() {
	}

	public Usuario(String email, String dni, String nombre, String apellido, String password, String rol) {
		this.email = email;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.rol = rol;

	}

	public String getEmail() {
		return email;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public String getRol() {
		return rol;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPasswordModify(String password) {
		this.password = DigestUtils.sha1Hex(password);;
	}

}