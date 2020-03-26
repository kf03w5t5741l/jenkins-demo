package nl.qien.jenkinsdemo.repository;

import nl.qien.jenkinsdemo.domain.Bicycle;
import org.springframework.data.repository.CrudRepository;

public interface BicycleRepository extends CrudRepository<Bicycle, Long> {
}
