package com.example.liquibase.web.api;

import com.example.liquibase.domain.Species;
import com.example.liquibase.service.implementations.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {
    @Autowired
    private SpeciesService speciesService;

    @PostMapping("/addSpecies")
    public ResponseEntity<Species> addSpecies(@RequestBody @Valid Species species) {
        Species createdSpecies = speciesService.createSpecie(species);
        return ResponseEntity.ok(createdSpecies);
    }

    @PutMapping("/{speciesId}")
    public ResponseEntity<Species> updateSpecies(@PathVariable("speciesId") UUID speciesId, @RequestBody Species species) {
        species.setId(speciesId);
        Species updatedSpecies = speciesService.updateSpecies(species);
        return ResponseEntity.ok(updatedSpecies);
    }

    @DeleteMapping("/{speciesId}")
    public ResponseEntity<String> deleteSpecies(@PathVariable("speciesId") UUID speciesId) {
        speciesService.deleteSpecies(speciesId);
        return ResponseEntity.ok("Species has been deleted succesfully");
    }

}
