package com.example.liquibase.web.api.participation;

import com.example.liquibase.domain.Participation;
import com.example.liquibase.service.DTO.ParticipationDTO;
import com.example.liquibase.service.DTO.UserParticipationDTO;
import com.example.liquibase.service.DTO.mapper.ParticipationMapper;
import com.example.liquibase.service.interfaces.ParticipationInterface;
import com.example.liquibase.web.vm.ParticipationVM;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/participation")
public class ParticipationController {

    @Autowired
    private ParticipationInterface participationService;
    @Autowired
    private ParticipationInterface participationInterface;
    @Autowired
    private ParticipationMapper participationMapper;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('MANAGE_PARTICIPATION') and hasAnyRole('ADMIN', 'JURY')")
    public ResponseEntity<Page<ParticipationDTO>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Participation> userPage = participationService.findAll(page, size);
        Page<ParticipationDTO> userDTOPage = userPage.map(participationMapper::toParticipationDTO);
        return ResponseEntity.ok(userDTOPage);
    }

    @PostMapping("/create")
    public Participation saveParticipation(@RequestBody @Valid ParticipationVM participationVM) {
        return participationInterface.save(participationVM);
    }

    @PutMapping("/update")
    public ResponseEntity<Participation> update(@RequestBody @Valid Participation participation) {
        Participation updateParticipation = participationService.update(participation);
        return ResponseEntity.ok(updateParticipation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        participationService.delete(id);
        return ResponseEntity.ok("Participation has been deleted succesfully");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_PARTICIPATION_USER')")
    public ResponseEntity<List<UserParticipationDTO>> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(participationService.findByUserId(id));
    }

    @GetMapping("/podium")
    public ResponseEntity<List<ParticipationDTO>> topThree() {
        return ResponseEntity.ok(participationInterface.getTop3Participants());
    }

}
