package com.example.liquibase.web.api.competition;

import com.example.liquibase.domain.Competition;
import com.example.liquibase.service.DTO.CompetitionDTO;
import com.example.liquibase.service.DTO.mapper.CompetitionMapper;
import com.example.liquibase.service.implementations.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CompetitionMapper competitionMapper;

    public CompetitionController(CompetitionService competitionService, CompetitionMapper competitionMapper) {
        this.competitionService = competitionService;
        this.competitionMapper = competitionMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CompetitionDTO>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Competition> competitionPage = competitionService.getAll(page, size);
        Page<CompetitionDTO> competitionDTOPage = competitionPage.map((competitionMapper::toCompetitionDTO));
        return ResponseEntity.ok(competitionDTOPage);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_COMPETITION')")
    public ResponseEntity<Competition> createCompetition(@RequestBody @Valid Competition competition) {
        Competition createdCompetition = competitionService.save(competition);
        return ResponseEntity.ok(createdCompetition);
    }

    @DeleteMapping("/{competitionId}")
    @PreAuthorize("hasAuthority('DELETE_COMPETITION')")
    public ResponseEntity<String> deleteCompetition(@PathVariable("competitionId") UUID competitionId) {
        competitionService.delete(competitionId);
        return ResponseEntity.ok("Competition has been deleted succesfully");
    }

    @PutMapping("/{competitionId}")
    @PreAuthorize("hasAuthority('UPDATE_COMPETITION')")
    public ResponseEntity<Competition> updateCompetition(@PathVariable("competitionId") UUID competitionId, @RequestBody Competition competition) {
        competition.setId(competitionId);
        Competition updatedCompetition = competitionService.update(competition);
        return ResponseEntity.ok(updatedCompetition);
    }

//    @GetMapping("/search")
//    public List<Competition> searchCompetitions(
//            @RequestParam(required = false) String code,
//            @RequestParam(required = false) String location,
//            @RequestParam(required = false) LocalDateTime dateFrom,
//            @RequestParam(required = false) SpeciesType speciesType,
//            @RequestParam(required = false) Boolean openRegistration
//    ) {
//        return competitionService.searchCompetitions(
//                CompetitionService.CompetitionSearchRequest.builder()
//                        .code(code)
//                        .location(location)
//                        .dateFrom(dateFrom)
//                        .speciesType(speciesType)
//                        .openRegistration(openRegistration)
//                        .build()
//        );
//    }
}

