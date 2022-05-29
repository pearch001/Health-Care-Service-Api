package com.healthCareService.healthCareServiceapi.services;

import com.healthCareService.healthCareServiceapi.dao.DoctorDao;
import com.healthCareService.healthCareServiceapi.model.entities.Address;
import com.healthCareService.healthCareServiceapi.model.entities.Doctor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DoctorService implements DoctorInt{

    private final DoctorDao doctorDao;

    //Saves Doctor Object
    @Override
    public Doctor saveDoctor(Doctor doctor) {
        doctor = doctorDao.save(doctor);
        Address address = doctor.getAddress();
        int doctorId =  doctor.getId();
        address.setDoctor_id(doctorId);
        doctor.setAddress(address);
        return doctor;
    }

    //Returns a list of doctor objects
    @Override
    public List<Doctor> fetchDoctors(int offset) {
        return (List<Doctor>) doctorDao.selectByOffset(offset);
    }

    //Returns a doctor selected based on the email
    @Override
    public Doctor selectDoctor(String email) {
        Boolean value = doctorDao.findByEmail(email);
        if (!value){
            throw new IllegalStateException("Doctor not found");
        }
        return doctorDao.selectByEmail(email);
    }

    //Edits Doctors address
    //it takes in a compulsory argument of email the rest can be null depending on the user discretion
    //it edits the chosen field of the user
    @Override
    public Doctor editAddress(String email, String homeAddress, String lga, String state) {
        Boolean value = doctorDao.findByEmail(email);

        if (!value){
            throw new IllegalStateException("Doctor not found");
        }

        Doctor doctor = doctorDao.selectByEmail(email);
        if (homeAddress != null && homeAddress.length() > 0 && !Objects.equals(homeAddress,doctor.getAddress().getHomeAddress())){
            Address address = doctor.getAddress();
            address.setHomeAddress(homeAddress);
            doctor.setAddress(address);
        }

        if (lga != null && lga.length() > 0 && !Objects.equals(lga,doctor.getAddress().getLga())){
            Address address = doctor.getAddress();
            address.setLga(lga);
            doctor.setAddress(address);
        }

        if (state != null && state.length() > 0 && !Objects.equals(state,doctor.getAddress().getState())){
            Address address = doctor.getAddress();
            address.setState(state);
            doctor.setAddress(address);
        }

        return doctor;
    }

    //Delete doctor objects

    @Override
    public void deleteDoctor(String email) {
        Boolean value = doctorDao.findByEmail(email);
        if (!value){
            throw new IllegalStateException("Doctor not found");
        }
        Doctor doctor = doctorDao.selectByEmail(email);

        doctorDao.delete(doctor);
    }
}
