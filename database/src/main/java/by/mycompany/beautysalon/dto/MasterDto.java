package by.mycompany.beautysalon.dto;

import java.util.Set;

public class MasterDto {

    private int id;
    private String firstName;
    private String lastName;
    private Set<String> services;

    public MasterDto() {
    }

    public MasterDto(String firstName, String lastName, Set<String> services) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.services = services;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getServices() {
        return services;
    }

    public void setServices(Set<String> services) {
        this.services = services;
    }
}
