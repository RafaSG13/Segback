package esi.uclm.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import esi.uclm.model.EntidadDeportiva;




public interface EntidadDao extends MongoRepository<EntidadDeportiva, String> {

	EntidadDeportiva findByNumeroRegistro(int numeroRegistro);

	EntidadDeportiva findByNombreEntidad(String nombreEntidad);

	List<EntidadDeportiva> findAll();
	
	List<EntidadDeportiva> findAllByLocalidad(String Localidad);
	
	List<EntidadDeportiva> findAllByProvincia(String provincia);

	List<EntidadDeportiva> findAllByNombreEntidad(String nombreEntidad);
	
	List<EntidadDeportiva> findAllByTipo(String tipo);

	List<EntidadDeportiva> findAllEntidadByNombreEntidadLike(String nombreEntidad);

	void deleteByNumeroRegistro(int numeroRegistro);




	


}