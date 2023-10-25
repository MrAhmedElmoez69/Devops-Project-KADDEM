package tn.esprit.spring.khaddem.dto;

import lombok.Getter;

@Getter
public class UniversiteDTO {
    // Getters and setters
    private String name;
    private String location;

    // Constructors
    public UniversiteDTO() {
    }

    public UniversiteDTO(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
