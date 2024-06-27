package com.happeningnow.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter

public abstract class BaseEntity {

    @Id
    @UuidGenerator
    private UUID id;
}
