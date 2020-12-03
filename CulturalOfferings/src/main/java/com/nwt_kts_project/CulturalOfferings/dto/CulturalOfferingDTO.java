package com.nwt_kts_project.CulturalOfferings.dto;

import com.nwt_kts_project.CulturalOfferings.model.*;

public class CulturalOfferingDTO {

    private Long id;

    private String location;

    private String name;

    private String description;


    public CulturalOfferingDTO() {
    }

    public CulturalOfferingDTO(Long id, String location, String name, String description) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return "CulturalOfferingDTO{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
