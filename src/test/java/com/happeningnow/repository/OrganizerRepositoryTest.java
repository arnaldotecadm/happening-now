package com.happeningnow.repository;

import com.happeningnow.model.Organizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DataJpaTest
public class OrganizerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private  OrganizerRepository organizerRepository;

    @Test
    public void findOrganizer(){

        Organizer organizer1 = entityManager
                .persist(new Organizer(UUID.randomUUID(),"FunHour","Musical","Rua do Amial, 995",new ArrayList<>()));

        Organizer organizer2 = entityManager
                .persist(new Organizer(UUID.randomUUID(),"Piada","Organization for jokes","Rua do Amial, 854",new ArrayList<>()));

        List<Organizer> organizers = organizerRepository.findAll();
        Assertions.assertEquals(2, organizers.size());
    }

}
