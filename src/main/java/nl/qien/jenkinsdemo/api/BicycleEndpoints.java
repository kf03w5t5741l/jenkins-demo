package nl.qien.jenkinsdemo.api;

import nl.qien.jenkinsdemo.domain.Bicycle;
import nl.qien.jenkinsdemo.service.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/bicycles")
@RestController
public class BicycleEndpoints {
    @Autowired
    private BicycleService bicycleService;

    @GetMapping
    public Iterable<Bicycle> getAllBicycles() {
        return this.bicycleService.findAll();
    }

    @GetMapping("/{bicycleId}")
    public Optional<Bicycle> getBicycle(@PathVariable Long bicycleId) {
        return this.bicycleService.findById(bicycleId);
    }

    @PostMapping
    public Bicycle postBicycle(@RequestBody Bicycle bicycle) {
        return this.bicycleService.save(bicycle);
    }

    @PutMapping("/{bicycleId")
    public Bicycle updateBicycle(@RequestParam Long bicycleId,
                               @RequestBody Bicycle bicycle) {
        bicycle.setBicycleId(bicycleId);
        return this.bicycleService.save(bicycle);
    }

    @DeleteMapping("/{bicycleId}")
    public void deleteBicycle(@PathVariable Long bicycleId) {
        this.bicycleService.deleteById(bicycleId);
    }

    @DeleteMapping
    public void deleteAllBicycles() {
        this.bicycleService.deleteAll();
    }
}
