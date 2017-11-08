package model;

import org.codehaus.jackson.annotate.JsonProperty;


public class Cliente {
	
	//ATRIBUTOS
	
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="edad")
	private int edad;
	
	@JsonProperty(value="documento")
	private String documento;
	
	@JsonProperty(value="correo")
	private String correo;
	
	@JsonProperty(value="usuario")
	private String usuario;
	
	@JsonProperty(value="contrasena")
	private String contrasena;
	
	// Metodo Constructor

	public Cliente(int id, String nombre, int edad, String documento, String correo, String usuario,
			String contrasena) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.documento = documento;
		this.correo = correo;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	//GETTERS AND SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
	

}
