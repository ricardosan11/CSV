package Main;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Persona {
	
	private String nombre, apellido, identificacion;
	private int edad, id;
	
	public Persona() {
		// TODO Auto-generated constructor stub
	}
	
	public Persona(String nombre, String apellido, String identificacion, int edad, int id){
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.identificacion = identificacion;
		this.edad = edad;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void listar(ArrayList<Persona> listaPersona, Conexion con) {
		
		for(Persona p : listaPersona) {
			
			System.out.println("Id: " + p.getId() + ". Nombre: " + p.getNombre());
			
			try {
				
				String query = "INSERT INTO personas (id_persona, nombre, apellido, identificacion, edad)" + "VALUES (?,?,?,?,?)";
				PreparedStatement pst = con.getConexion().prepareStatement(query);
				pst.setInt(1, p.getId());
				pst.setString(2, p.getNombre());
				pst.setString(3, p.getApellido());
				pst.setString(4, p.getIdentificacion());
				pst.setInt(5, p.getEdad());
				pst.execute();
				con.getConexion().close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} 
		
		
	}

}
