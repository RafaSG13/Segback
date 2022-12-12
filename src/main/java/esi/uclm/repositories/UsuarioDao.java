package esi.uclm.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import esi.uclm.model.Usuario;



public interface UsuarioDao extends MongoRepository<Usuario, String> {

	Usuario findByDni(String dni);

	Usuario findByEmail(String email);

	Usuario findByEmailAndPassword(String email, String password);

	List<Usuario> findAllUsuarioByNombreLike(String nombre);

	List<Usuario> findAllUsuarioByEmailLike(String email);

	void deleteByEmail(String email);


}