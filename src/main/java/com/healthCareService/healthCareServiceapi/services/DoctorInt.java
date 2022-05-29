package com.healthCareService.healthCareServiceapi.services;

import com.healthCareService.healthCareServiceapi.model.entities.Address;
import com.healthCareService.healthCareServiceapi.model.entities.Doctor;

import java.util.List;

public interface DoctorInt {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> fetchDoctors(int offset);
    Doctor selectDoctor(String email);
    Doctor editAddress(String email, String homeAddress, String lga, String state);
    void deleteDoctor(String email);
}
