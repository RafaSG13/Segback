package esi.uclm.http;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.apache.commons.codec.digest.DigestUtils;
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
import esi.uclm.model.Usuario;
import esi.uclm.repositories.UsuarioDao;




@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"http://localhost:4200", "https://seguridad2022-2023.web.app"})
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;

	private static final String EMAIL = "email";
	private static final String NOMBRE = "nombre";
	private static final String PASSWORD = "password";
	private static final String DNI = "dni";
	private static final String APELLIDO = "apellido";
	private static final String ROL = "rol";


	@PostMapping("/login")
	public Usuario login(@RequestBody Map<String, Object> datosUsuario) {
		try {
			JSONObject json = new JSONObject(datosUsuario);
			String email = json.getString(EMAIL);
			String password = json.getString(PASSWORD);
			String passHex = DigestUtils.sha1Hex(password);
			Usuario usuario = usuarioDao.findByEmailAndPassword(email,passHex);
			return usuario;
		
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("/crearUsuario")
	public void crearUsuario(@RequestBody Map<String, Object> datosUsuario) {
		try {
			JSONObject json = new JSONObject(datosUsuario);
			String email = json.getString(EMAIL);
			String dni = json.getString(DNI);
			String nombre = json.getString(NOMBRE);
			String password = json.getString(PASSWORD);
			String apellido = json.getString(APELLIDO);
			String rol = json.getString(ROL);
			
			Usuario usuario = new Usuario(email,dni,nombre,apellido,password,rol);
			usuarioDao.save(usuario);
			
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
	}
	

	@GetMapping("/verUsuarios")
	public List<Usuario> verUsuario() {
		List<Usuario> usuarios = usuarioDao.findAll();
		return usuarios;
	}
	

	@DeleteMapping("/eliminarUsuario")
	public void eliminarUsuario(@RequestBody Map<String, Object> datosUsuario) {
		try {

			JSONObject json = new JSONObject(datosUsuario);
			String email = json.getString(EMAIL);

			Usuario user = usuarioDao.findByEmail(email);
			usuarioDao.deleteByEmail(email);

		} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
	}
	

	@PostMapping("/modificarUsuario")
	public void modificarUsuario(@RequestBody Map<String, Object> datosUsuario) {
		try {
			JSONObject json = new JSONObject(datosUsuario);
			String email = json.getString(EMAIL);
			String dni = json.getString(DNI);
			String nombre = json.getString(NOMBRE);
			String apellido = json.getString(APELLIDO);
			String password = json.getString(PASSWORD);
			String rol = json.getString(ROL);
			Usuario antiguoUsuario = usuarioDao.findByEmail(email);

			if(dni != "")
				antiguoUsuario.setDni(dni);
			if(nombre != "")
				antiguoUsuario.setNombre(nombre);
			if(apellido != "")
				antiguoUsuario.setApellido(apellido);
			if(password != "")
				antiguoUsuario.setPasswordModify(password);
			if(rol != "")
				antiguoUsuario.setRol(rol);			
			
			usuarioDao.save(antiguoUsuario);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}


	@PostMapping("/cambiarContrasena")
	public void cambiarContrasena(@RequestBody Map<String, Object> datosUsuario) {
		try {
			JSONObject json = new JSONObject(datosUsuario);
			String email = json.getString(EMAIL);
			String password = json.getString(PASSWORD);
			
			Usuario usuario = usuarioDao.findByEmail(email);
			usuario.setPasswordModify(password);

			usuarioDao.save(usuario);
			
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
	}
	


	@GetMapping("/buscarUsuariosPorEmail")
	public List<Usuario> buscarUsuarioPorEmail(@RequestParam String email) {
		try {
			List<Usuario> usuarios = usuarioDao.findAllUsuarioByEmailLike(email);
			return usuarios;
		}catch(Exception e) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/mostrarUsuarioPorEmail")
	public Usuario mostrarUsuarioPorEmail(@RequestParam String email) {
		try {
			Usuario usuario = usuarioDao.findByEmail(email);
			return usuario;
		}catch(Exception e) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}