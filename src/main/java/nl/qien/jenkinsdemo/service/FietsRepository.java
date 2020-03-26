package nl.qien.jenkinsdemo.service;

import nl.qien.jenkinsdemo.domain.Fiets;
import org.springframework.data.repository.CrudRepository;

public interface FietsRepository extends CrudRepository<Fiets, Long> {
}
