package br.com.fiap.infra.mongodb.vacina.repository;

import br.com.fiap.infra.mongodb.vacina.document.VacinaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VacinaMongoDBRepository extends MongoRepository<VacinaDocument, String> {

    Optional<VacinaDocument> findByNome(String cpf);

}