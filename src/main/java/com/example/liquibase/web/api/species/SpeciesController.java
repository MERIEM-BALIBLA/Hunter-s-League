package com.example.liquibase.web.api.species;

import com.example.liquibase.domain.Species;
import com.example.liquibase.service.implementations.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {
    @Autowired
    private SpeciesService speciesService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('MANAGE_SPECIES') or hasAuthority('CAN_VIEW_SPECIES')")
    public ResponseEntity<Page<Species>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Species> speciesPage = speciesService.getAll(page, size);
        return ResponseEntity.ok(speciesPage);
    }

    @PostMapping("/addSpecies")
    @PreAuthorize("hasAuthority('MANAGE_SPECIES') and hasRole('ADMIN')")
    public ResponseEntity<Species> addSpecies(@RequestBody @Valid Species species) {
        Species createdSpecies = speciesService.createSpecie(species);
        return ResponseEntity.ok(createdSpecies);
    }

    @PutMapping("/{speciesId}")
    @PreAuthorize("hasAuthority('MANAGE_SPECIES')")
    public ResponseEntity<Species> updateSpecies(@PathVariable("speciesId") UUID speciesId, @RequestBody Species species) {
        species.setId(speciesId);
        Species updatedSpecies = speciesService.updateSpecies(species);
        return ResponseEntity.ok(updatedSpecies);
    }

    @DeleteMapping("/{speciesId}")
    @PreAuthorize("hasAuthority('MANAGE_SPECIES')")
    public ResponseEntity<String> deleteSpecies(@PathVariable("speciesId") UUID speciesId) {
        speciesService.deleteSpecies(speciesId);
        return ResponseEntity.ok("Species has been deleted succesfully");
    }


}
