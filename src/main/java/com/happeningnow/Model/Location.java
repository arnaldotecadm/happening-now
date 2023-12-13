package com.happeningnow.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @UuidGenerator
    @Column(name = "location_id")
    private UUID locationId;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String adrres;
    @Column(name = "other_fields_for_add")
    private String otherFieldsForAdds;
    @OneToMany
    private List<Event> event;

    public Location(){
    }
    public Location(UUID locationId, String name, String description, String adrres, String otherFieldsForAdds, String otherFieldsForPeople) {
        this.locationId = locationId;
        this.name = name;
        this.description = description;
        this.adrres = adrres;
        this.otherFieldsForAdds = otherFieldsForAdds;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
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

    public String getAdrres() {
        return adrres;
    }

    public void setAdrres(String adrres) {
        this.adrres = adrres;
    }

    public String getOtherFieldsForAdds() {
        return otherFieldsForAdds;
    }

    public void setOtherFieldsForAdds(String otherFieldsForAdds) {
        this.otherFieldsForAdds = otherFieldsForAdds;
    }
}
