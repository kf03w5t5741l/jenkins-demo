package nl.qien.jenkinsdemo.api;

import nl.qien.jenkinsdemo.domain.Fiets;
import nl.qien.jenkinsdemo.service.FietsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/fiets")
@RestController
public class FietsEndpoints {
    @Autowired
    private FietsService fietsService;

    @GetMapping
    public Iterable<Fiets> getAllFietsen() {
        return this.fietsService.findAll();
    }

    @GetMapping("/{fietsId}")
    public Optional<Fiets> getFiets(@PathVariable Long fietsId) {
        return this.fietsService.findById(fietsId);
    }

    @PostMapping
    public Fiets postFiets(@RequestBody Fiets fiets) {
        return this.fietsService.save(fiets);
    }

    @DeleteMapping("/{fietsId}")
    public void deleteFiets(@PathVariable Long fietsId) {
        this.fietsService.deleteById(fietsId);
    }

    @DeleteMapping
    public void deleteAllFietsen() {
        this.fietsService.deleteAll();
    }
}
