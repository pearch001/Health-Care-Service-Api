package com.healthCareService.healthCareServiceapi.dao;

import com.healthCareService.healthCareServiceapi.model.entities.Address;
import com.healthCareService.healthCareServiceapi.model.entities.Doctor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Slf4j
class DoctorDaoTest {
    @Autowired
    private DoctorDao doctorDao;

    @AfterEach
    void tearDown() {
        doctorDao.deleteAll();
    }

    @Test
    void selectByEmail() {
        //given
        //each test should be ran based on methods
        String email = "kasim@yahoo.com";

        Doctor doctor = new Doctor(1,"head", "tail", email, "09068058305",
                new Address(1,1,"Akande","Oshodi/Isolo","Lagos"));
        doctorDao.save(doctor);
        //when
        Doctor expected = doctorDao.selectByEmail(email);

        //then
        assertEquals(doctor,expected);


    }



    @Test
    void checkIfEmailExists() {
        //given
        String email = "kasim@yahoo.com";
        int id = 1;
        Doctor doctor = new Doctor(id,"head", "tail", email, "09068058305",
                new Address(id,id,"Akande","Oshodi/Isolo","Lagos"));
        doctorDao.save(doctor);
        //when
        boolean expected = doctorDao.findByEmail(email);
        //then
        assertTrue(expected);
    }

    @Test
    void checkIfEmailNotExists() {
        //given
        String email = "kasim@yahoo.com";
        int id = 1;
        Doctor doctor = new Doctor(id,"head", "tail", "boss@g.com", "09068058305",
                new Address(id,id,"Akande","Oshodi/Isolo","Lagos"));
        doctorDao.save(doctor);
        //when
        boolean expected = doctorDao.findByEmail(email);
        //then
        assertFalse(expected);
    }

    @Test
    void selectByOffset() {
        //given
        String email = "kasim@yahoo.com";
        int id = 1;
        Doctor doctor0 = new Doctor(id,"head", "tail", email, "09068058305",
                new Address(id,id,"Akande","Oshodi/Isolo","Lagos"));
        doctorDao.save(doctor0);
        Doctor doctor1 = new Doctor(id+1,"head", "tail", "boss", "09066058305",
                new Address(id+1,id+1,"Akande","Oshodi/Isolo","Lagos"));
        doctorDao.save(doctor1);

        List <Doctor> doctor = new ArrayList<Doctor>();
        doctor.add(doctor0);
        doctor.add(doctor1);
        //when
        List<Doctor> expected = doctorDao.selectByOffset(0);

        //then
        assertEquals(doctor,expected);


    }
}