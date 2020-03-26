package nl.qien.jenkinsdemo.service;

import nl.qien.jenkinsdemo.domain.Bicycle;
import nl.qien.jenkinsdemo.repository.BicycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BicycleService {
    @Autowired
    private BicycleRepository bicycleRepository;

    public Bicycle save(Bicycle bicycle) {
        return this.bicycleRepository.save(bicycle);
    }

    public Optional<Bicycle> findById(Long bicycleId) {
        return this.bicycleRepository.findById(bicycleId);
    }

    public Iterable<Bicycle> findAll() {
        return this.bicycleRepository.findAll();
    }

    public void deleteById(Long bicycleId) {
        this.bicycleRepository.deleteById(bicycleId);
    }

    public void deleteAll() {
        this.bicycleRepository.deleteAll();
    }
}
