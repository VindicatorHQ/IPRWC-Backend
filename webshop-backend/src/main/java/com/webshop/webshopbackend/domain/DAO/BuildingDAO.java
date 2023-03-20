package com.webshop.webshopbackend.domain.DAO;

import com.webshop.webshopbackend.domain.entity.Building;
import com.webshop.webshopbackend.domain.repository.BuildingRepository;
import com.webshop.webshopbackend.exception.NotFound;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuildingDAO implements DAO<Building> {

    private final BuildingRepository buildingRepository;

    public BuildingDAO(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public Building getById(String id) throws NotFound {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new NotFound("Building", id));
    }

    @Override
    public Building saveToDatabase(Building building) {
        return this.buildingRepository.save(building);
    }

    @Override
    public Building update(String id, Building buildingRequest) throws NotFound {
        Building building = this.buildingRepository.findById(id)
                .orElseThrow(() -> new NotFound("Building", id));

        building.setCity(buildingRequest.getCity());
        building.setAddress(buildingRequest.getAddress());
        building.setZipcode(buildingRequest.getZipcode());
        building.setName(buildingRequest.getName());
        return buildingRepository.save(building);
    }

    @Override
    public void delete(String id) throws NotFound {
        if (buildingRepository.existsById(id))
            this.buildingRepository.deleteById(id);
        else {
            throw new NotFound("Building", id);
        }
    }

    @Override
    public List<Building> getAll() {
        return this.buildingRepository.findAll();
    }
}
