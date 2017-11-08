package model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Tienda {
	
	
	//ATRIBUTOS
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="horario")
	private String horario;
	
	@JsonProperty(value="direccion")
	private String direccion;
	
	
	//METODO CONSTRUCTOR
	public Tienda(String nombre, int id, String horario, String direccion) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.horario = horario;
		this.direccion = direccion;
	}
	
	//GETTER AND SETTER

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setDescripcion(int id) {
		this.id = id;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	

}
