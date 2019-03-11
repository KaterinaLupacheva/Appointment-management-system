package by.mycompany.beautysalon.dto;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MasterDto {

    private int id;
    private String firstName;
    private String lastName;
    private String mainService;
    private Set<String> services;

    public MasterDto() {
    }

    public MasterDto(String firstName, String lastName, Set<String> services) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.services = services;
    }

    public Integer getId() {
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

    public String getMainService() {
        return mainService;
    }

    public void setMainService(String mainService) {
        this.mainService = mainService;
    }

    public Set<String> getServices() {
        return services;
    }

    public void setServices(Set<String> services) {
        this.services = services;
    }

    public  boolean isPersisted() {
        return id > 0;
    }

    @Override
    public String toString() {
        return "MasterDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mainService='" + mainService + '\'' +
                ", services=" + services +
                '}';
    }
}
