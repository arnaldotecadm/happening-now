package com.happeningnow.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "organizer")
public class Organizer {
    @Id
    @UuidGenerator
    @Column(name = "organizer_Id")
    private UUID organizerId;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String addres;
    @Column(name = "other_fields_for_add")
    private String otherFieldsForAdds;
    @Column(name = "other_fields_for_people")
    private String otherFieldsForPeople;

    public Organizer(){
    }
    public Organizer(UUID organizerId, String name, String description, String addres, String otherFieldsForAdds, String otherFieldsForPeople) {
        this.organizerId = organizerId;
        this.name = name;
        this.description = description;
        this.addres = addres;
        this.otherFieldsForAdds = otherFieldsForAdds;
        this.otherFieldsForPeople = otherFieldsForPeople;
    }

    public UUID getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(UUID organizerId) {
        this.organizerId = organizerId;
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

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getOtherFieldsForAdds() {
        return otherFieldsForAdds;
    }

    public void setOtherFieldsForAdds(String otherFieldsForAdds) {
        this.otherFieldsForAdds = otherFieldsForAdds;
    }

    public String getOtherFieldsForPeople() {
        return otherFieldsForPeople;
    }

    public void setOtherFieldsForPeople(String otherFieldsForPeople) {
        this.otherFieldsForPeople = otherFieldsForPeople;
    }
}
