package esi.uclm.http;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import esi.uclm.model.EntidadDeportiva;
import esi.uclm.repositories.EntidadDao;


@RestController
@RequestMapping("/entidades")
@CrossOrigin(origins = {"http://localhost:4200", "https://seguridad2022-2023.web.app"})
public class EntidadController {
	
	@Autowired
	private EntidadDao entidadDao;

	@PostMapping("/crearEntidad")
	public EntidadDeportiva crearEntidad(@RequestBody Map<String, Object> datosEntidad) {
		try {
			
			JSONObject json = new JSONObject(datosEntidad);
			String nombreEntidad = json.getString("nombreEntidad");
			String tipoEntidad = json.getString("tipo");
			String localidad = json.getString("localidad");
			String provincia = json.getString("provincia");
			
			List<EntidadDeportiva>entidades = entidadDao.findAll(Sort.by(Sort.Direction.DESC, "numeroRegistro"));
			
			int numeroRegistro = entidades.get(0).getNumeroRegistro() +1;

			EntidadDeportiva entidad = new EntidadDeportiva(numeroRegistro,nombreEntidad,tipoEntidad,provincia,localidad);
			
			entidadDao.save(entidad);
			return entidad;
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping("/eliminarEntidad")
	public void eliminarEntidad(@RequestParam int numeroRegistro) {
		try {

			EntidadDeportiva entidad = entidadDao.findByNumeroRegistro(numeroRegistro);
			if(entidad != null) {
				entidadDao.delete(entidad);
			}
			

		} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			
		}
	}
	

	@PostMapping("/modificarEntidad")
	public EntidadDeportiva modificarEntidad(@RequestBody Map<String, Object> datosEntidad) {
		try {
			JSONObject json = new JSONObject(datosEntidad);
			int numeroRegistro = json.getInt("numeroRegistro");
			String nombreEntidad = json.getString("nombreEntidad");
			String tipoEntidad = json.getString("tipo");
			String localidad = json.getString("localidad");
			String provincia = json.getString("provincia");
			
			
			EntidadDeportiva antiguaEntidad = entidadDao.findByNumeroRegistro(numeroRegistro);

			if (antiguaEntidad == null)
				throw new Exception();
			else {
				EntidadDeportiva nuevaEntidad = new EntidadDeportiva(numeroRegistro,nombreEntidad,tipoEntidad,provincia,localidad);
				entidadDao.deleteByNumeroRegistro(antiguaEntidad.getNumeroRegistro());
				entidadDao.save(nuevaEntidad);
				return nuevaEntidad;
			}

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

	}
	

	@GetMapping("/mostrarEntidades")
	public List<EntidadDeportiva> verEntidades() {
		try {
			List<EntidadDeportiva> entidades = entidadDao.findAll();
			return entidades;
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	


	@GetMapping("/buscarEntidadPorNombre")
	public List<EntidadDeportiva> buscarEntidadPorNombre(@RequestParam String nombreEntidad) {
		try {
			List<EntidadDeportiva> entidades = entidadDao.findAllEntidadByNombreEntidadLike(nombreEntidad);
			return entidades;
		}catch(Exception e) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	

	@GetMapping("/mostrarEntidadPorNumeroRegistro")
	public EntidadDeportiva verEntidadNumeroRegistro(@RequestParam int numeroRegistro) {
		try {
			EntidadDeportiva entidad = entidadDao.findByNumeroRegistro(numeroRegistro);
			return entidad;
		}catch(Exception e) {
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			e.printStackTrace();
			return null;
		}
	
	}



	@PostMapping("/cargarEntidades")
	private void cargarEntidades() {
        String cadena = "C:/Users/rafas/Desktop/seguridad/datos practica/EntidadesCR.csv";
        List<EntidadDeportiva> entidadesLista = new ArrayList<EntidadDeportiva>();
        
        
        try {
        	@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(cadena));
        	String line = null;
        	while((line =reader.readLine())!=null) {
        		String []attributes = line.split(";");
     
        		entidadesLista.add(createEntidad(attributes));
        	}
        }catch(IOException e) {
        	e.printStackTrace();
        }
		entidadDao.saveAll(entidadesLista);
		
	}

	private static EntidadDeportiva createEntidad(String[] attributes) {
		// TODO Auto-generated method stub
		int numeroRegistro = Integer.parseInt(attributes[0]);
		String nombreEntidad = attributes[1];
		String tipo = attributes[2];
		String localidad = attributes[3];
		String provincia = attributes[4];
	
		EntidadDeportiva entidad = new EntidadDeportiva(numeroRegistro,nombreEntidad,tipo,provincia,localidad);
		return entidad;
	}	
}