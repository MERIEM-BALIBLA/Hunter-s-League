package com.example.liquibase.web.api.species;

import com.example.liquibase.domain.Species;
import com.example.liquibase.domain.User;
import com.example.liquibase.service.DTO.UserDTO;
import com.example.liquibase.service.implementations.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {
    @Autowired
    private SpeciesService speciesService;

    @GetMapping("/list")
    /*public ResponseEntity<List<Species>> species(){
        List<Species> species = speciesService.getAll();
        return ResponseEntity.ok(species);
    }*/
    public ResponseEntity<Page<Species>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Species> userPage = speciesService.getAll(page, size);
//        Page<UserDTO> userDTOPage = userPage.map(userMapper::toUserDTO);
        return ResponseEntity.ok(userPage);
    }

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
