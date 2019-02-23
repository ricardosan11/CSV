package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.csvreader.CsvWriter;


public class Main {
	
	public static void main(String[] args) {
		
		importar();
	}
	
	public static void importar() {
		
		ArrayList<Persona> listaPersona = new ArrayList<Persona>();
		Persona p = new Persona();
		Conexion con = new Conexion();
		
		try {
			
			FileReader archivoLeer = new FileReader("C:\\Users\\hogar\\Desktop\\base.csv");
			BufferedReader buffer = new BufferedReader(archivoLeer);
			boolean s = true;
			int contador = 0;
			
			while(s){
				
				String tx = buffer.readLine();
				if(tx != null){
						
						String [] valores;
						valores = tx.split(";");
						String id = valores[0];
						String nombre = valores[1];
						String apellido = valores[2];
						String identificacion = valores[3];
						String edad = valores[4];
						
						if (contador == 0) {
							
							System.out.println(id + " " + nombre + " " + apellido + " " + identificacion + " " + edad);
						}else {
							
							
							listaPersona.add(new Persona(nombre, apellido, identificacion, Integer.parseInt(edad), Integer.parseInt(id)));
						}
					contador++;					
				}else {
					s = false;
				}
			}
			
			p.listar(listaPersona, con);
			
			buffer.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void exportar(Conexion con) {
		
		ArrayList<Persona> listaPersona = new ArrayList<Persona>();
		ResultSet rs = null;
		try {
			
			String query = "SELECT * FROM personas";
			PreparedStatement ps = con.getConexion().prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Persona persona = new Persona();
				persona.setId(rs.getInt(1));
				persona.setNombre(rs.getString(2));
				persona.setApellido(rs.getString(3));
				persona.setIdentificacion(rs.getString(4));
				persona.setEdad(rs.getInt(5));
				listaPersona.add(persona);
			}
			
			String archivoGuardar = "C:\\Users\\hogar\\Desktop\\Exportar.csv";
			boolean existencia = new File(archivoGuardar).exists();
			
			if(existencia) {
				
				File archivoPersona = new File(archivoGuardar);
				archivoPersona.delete();
			}
			
			
			CsvWriter csvImportar = new CsvWriter(new FileWriter(archivoGuardar, true), ',');
			
			csvImportar.write("Id");
			csvImportar.write("Nombre");
			csvImportar.write("Apellido");
			csvImportar.write("Identificacion");
			csvImportar.write("Edad");
			csvImportar.endRecord();
			
			for(Persona persona : listaPersona) {
				
				csvImportar.write("" + persona.getId());
				csvImportar.write(persona.getNombre());
				csvImportar.write(persona.getApellido());
				csvImportar.write(persona.getIdentificacion());
				csvImportar.write("" + persona.getEdad());
				csvImportar.endRecord();
			}
			
			csvImportar.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
