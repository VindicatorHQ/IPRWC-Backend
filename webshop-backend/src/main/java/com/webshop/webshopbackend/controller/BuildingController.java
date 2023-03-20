package com.webshop.webshopbackend.controller;


import com.webshop.webshopbackend.domain.DAO.BuildingDAO;
import com.webshop.webshopbackend.domain.DTO.BuildingDTO;
import com.webshop.webshopbackend.domain.entity.Building;
import com.webshop.webshopbackend.domain.mapper.BuildingMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/building")
public class BuildingController {
    
    private final BuildingDAO buildingDAO;
    private final BuildingMapper buildingMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BuildingDTO createBuilding(@RequestBody @Valid BuildingDTO buildingDTO){
        Building buildingRequest = buildingMapper.fromDTOToEntity(buildingDTO);
        Building building = this.buildingDAO.saveToDatabase(buildingRequest);

        return buildingMapper.fromEntityToDTO(building);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BuildingDTO> getAllBuildings(){
        return buildingDAO.getAll().stream().map(buildingMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BuildingDTO getBuildingByID(@PathVariable String id){
        Building building = this.buildingDAO.getById(id);

        return buildingMapper.fromEntityToDTO(building);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteBuilding(@PathVariable String id) {
        this.buildingDAO.delete(id);

        return "Building deleted.";
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BuildingDTO updateBuilding(@PathVariable String id, @RequestBody BuildingDTO buildingDTO) {
        buildingDTO.setId(id);
        Building buildingRequest = buildingMapper.fromDTOToEntity(buildingDTO);
        Building building = buildingDAO.update(id, buildingRequest);

        return buildingMapper.fromEntityToDTO(building);
    }
}
