package com.webshop.webshopbackend.domain.mapper;

import com.webshop.webshopbackend.domain.DAO.BuildingDAO;
import com.webshop.webshopbackend.domain.DTO.BuildingDTO;
import com.webshop.webshopbackend.domain.entity.Building;
import org.springframework.stereotype.Component;

@Component
public class BuildingMapper implements Mapper<Building, BuildingDTO> {

    private final BuildingDAO buildingDAO;

    public BuildingMapper(BuildingDAO buildingDAO) {
        this.buildingDAO = buildingDAO;
    }

    @Override
    public Building fromDTOToEntity(BuildingDTO buildingDTO) {

        if (buildingDTO == null) {
            return null;
        }

        Building building = new Building();

        building.setId(buildingDTO.getId());
        building.setZipcode(buildingDTO.getZipcode());
        building.setCity(buildingDTO.getCity());
        building.setAddress(buildingDTO.getAddress());
        building.setName(buildingDTO.getName());

        return building;
    }

    @Override
    public BuildingDTO fromEntityToDTO(Building building) {

        if (building == null) {
            return null;
        }

        BuildingDTO buildingDTO = new BuildingDTO();

        buildingDTO.setId(building.getId());
        buildingDTO.setZipcode(building.getZipcode());
        buildingDTO.setCity(building.getCity());
        buildingDTO.setAddress(building.getAddress());
        buildingDTO.setName(building.getName());

        return buildingDTO;
    }

    @Override
    public Building fromIdToEntity(String id) {

        if (id == null) {
            return null;
        }

        return buildingDAO.getById(id);
    }
}
