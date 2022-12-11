package esi.uclm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Entidad")
public class EntidadDeportiva {
	@Id
	private int numeroRegistro;
	private String nombreEntidad;
	private String tipo;
	private String localidad;
	private String provincia;
	private String imagen = 
			"https://img.freepik.com/vector-gratis/fondo-patrones-deportes-fisuras_98292-4294.jpg?w=740&t=st=1668067465~exp=1668068065~hmac=62371037abccd974b0644fa63740ce3ae691ea0fce53dba07a087dfe20d32b61";
	
	
	public EntidadDeportiva() {}
	
	public EntidadDeportiva(int numeroRegistro, String nombreEntidad, String tipoEntidad, String provincia,
			String localidad) {
		this.numeroRegistro = numeroRegistro;
		this.nombreEntidad = nombreEntidad;
		this.localidad = localidad;
		this.provincia = provincia;
		this.tipo = tipoEntidad;
	}

	public int getNumeroRegistro() {
		return numeroRegistro;
	}
	public void setNumeroRegistro(int numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}



