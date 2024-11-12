package com.example.liquibase.service.interfaces;

import com.example.liquibase.domain.Hunt;
import com.example.liquibase.service.DTO.HuntDTO;
import com.example.liquibase.web.vm.HuntVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HuntInterface {
    Page<HuntDTO> findAll(Pageable pageable);


//    Hunt save(Hunt hunt);

    Hunt save(HuntVM huntVM);

    Optional<Hunt> findById(UUID id);

    Hunt update(Hunt hunt);

    void delete(UUID id);

    Optional<Hunt> findBySpeciesName(String name);

    List<Hunt> findByWeight(Double weight);
}
