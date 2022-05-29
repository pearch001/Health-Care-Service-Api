package com.healthCareService.healthCareServiceapi.services;

import com.healthCareService.healthCareServiceapi.dao.DoctorDao;
import com.healthCareService.healthCareServiceapi.model.entities.Address;
import com.healthCareService.healthCareServiceapi.model.entities.Doctor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class DoctorServiceTest {
    @Mock private DoctorDao doctorDao;
    private AutoCloseable autoCloseable;
    private DoctorService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DoctorService(doctorDao);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveDoctor() {
        //given
        //each test should be ran individually
        String email = "kasim@yahoo.com";

        Doctor doctor = new Doctor(1,"head", "tail", email, "09068058305",
                new Address(1,1,"Akande","Oshodi/Isolo","Lagos"));
        //when
            underTest.saveDoctor(doctor);
        //then
            verify(doctorDao).save(doctor);
    }

    @Test
    void fetchDoctors() {
        //when
        underTest.fetchDoctors(0);
        //then
        verify(doctorDao).selectByOffset(0);
    }

    @Test
    void selectDoctor() {
        //given
        //each test should be ran individually
        String email = "kasim@yahoo.com";

        Doctor doctor = new Doctor(1,"head", "tail", email, "09068058305",
                new Address(1,1,"Akande","Oshodi/Isolo","Lagos"));
        doctorDao.save(doctor);
        //when
        underTest.selectDoctor(email);
        //then
        verify(doctorDao).selectByEmail(email);
    }

    @Test
    void checkIfEditWith0ParamWorks() {
        //given
        String email = "kasim@yahoo.com";
        int id = 1;
        Doctor doctor = new Doctor(id,"head", "tail", "boss@g.com", "09068058305",
                new Address(id,id,"Akande","Oshodi/Isolo","Lagos"));
        doctorDao.save(doctor);
        underTest.editAddress(email,null,null,null);
        //when
        Doctor expected = doctorDao.selectByEmail(email);

        //then
        assertEquals(doctor,expected);
    }

    @Test
    void deleteDoctor() {
        //given
        //each test should be ran individually
        String email = "kasim@yahoo.com";

        Doctor doctor = new Doctor(1,"head", "tail", email, "09068058305",
                new Address(1,1,"Akande","Oshodi/Isolo","Lagos"));
        doctorDao.save(doctor);
        //when
        underTest.deleteDoctor(email);
        //then
        verify(doctorDao).delete(doctor);
    }
}