package com.example.liquibase.web.api.hunt;

import com.example.liquibase.domain.Hunt;
import com.example.liquibase.service.DTO.HuntDTO;
import com.example.liquibase.service.DTO.ParticipationDTO;
import com.example.liquibase.service.interfaces.HuntInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hunt")
public class HuntController {
    private final HuntInterface huntInterface;

    @Autowired
    public HuntController(HuntInterface huntInterface) {
        this.huntInterface = huntInterface;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<HuntDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.valueOf(direction.toUpperCase()),
                sortBy
        );

        Page<HuntDTO> participations = huntInterface.findAll(pageable);
        return ResponseEntity.ok(participations);
    }


}