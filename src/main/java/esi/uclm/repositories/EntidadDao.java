package esi.uclm.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import esi.uclm.model.EntidadDeportiva;




public interface EntidadDao extends MongoRepository<EntidadDeportiva, String> {

	EntidadDeportiva findByNumeroRegistro(int numeroRegistro);

	EntidadDeportiva findByNombreEntidad(String nombreEntidad);

	List<EntidadDeportiva> findAll();
	
	List<EntidadDeportiva> findAllByNombreEntidad(String nombreEntidad);
	
	List<EntidadDeportiva> findAllEntidadByNombreEntidadLike(String nombreEntidad);

	void deleteByNumeroRegistro(int numeroRegistro);




	


}