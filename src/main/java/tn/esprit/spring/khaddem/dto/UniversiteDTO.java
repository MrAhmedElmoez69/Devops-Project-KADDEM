package tn.esprit.spring.khaddem.dto;

public class UniversiteDTO {
    private String name;
    private String location;

    // Constructors, getters, setters, etc.

    // Constructors
    public UniversiteDTO() {
    }

    public UniversiteDTO(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

