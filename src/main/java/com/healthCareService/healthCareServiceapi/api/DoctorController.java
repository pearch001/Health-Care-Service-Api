package com.healthCareService.healthCareServiceapi.api;

import com.healthCareService.healthCareServiceapi.model.entities.Doctor;
import com.healthCareService.healthCareServiceapi.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    //endpoint to save a new doctor "/api/v1/doctors"
    @PostMapping("/doctors")
    public void saveUser(@RequestBody Doctor doctor){
        doctorService.saveDoctor(doctor);
    }

    //endpoint to get a list of doctors based off an offset and max number of returned rows = 10
    //"/api/v1/doctors"
    @GetMapping("/doctors")
    public List<Doctor> fetchDoctors(@RequestParam(required = false) Integer offset){
        if (offset == null){
            offset = 0;
        }
        return doctorService.fetchDoctors(offset);
    }

    //Select a single doctor based of email "/api/v1/doctors/{email}"

    @GetMapping("/doctors/{email}")
    public Doctor fetchDoctor(@PathVariable("email") String email){
        return doctorService.selectDoctor(email);
    }

    //Update doctors address "/api/v1/doctors/{email}"
    @PutMapping("/doctors/{email}")
    public Doctor updateStudent(@PathVariable("email") String email,
                                 @RequestParam(required = false) String homeAddress,
                                @RequestParam(required = false) String lga,
                                 @RequestParam(required = false) String state){

        return doctorService.editAddress(email,homeAddress,lga,state);
    }

    //delete a doctor entry "/api/v1/doctors/{email}"
    @DeleteMapping("/doctors/{email}")
    public void deleteStudent(@PathVariable("email") String email){
        doctorService.deleteDoctor(email);
    }
}
