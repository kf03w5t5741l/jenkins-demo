package nl.qien.jenkinsdemo.service;

import nl.qien.jenkinsdemo.domain.Fiets;
import nl.qien.jenkinsdemo.repository.FietsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class FietsService {
    @Autowired
    private FietsRepository fietsRepository;

    public Fiets save(Fiets fiets) {
        return this.fietsRepository.save(fiets);
    }

    public Optional<Fiets> findById(Long fietsId) {
        return this.fietsRepository.findById(fietsId);
    }

    public Iterable<Fiets> findAll() {
        return this.fietsRepository.findAll();
    }

    public void deleteById(Long fietsId) {
        this.fietsRepository.deleteById(fietsId);
    }

    public void deleteAll() {
        this.fietsRepository.deleteAll();
    }
}
