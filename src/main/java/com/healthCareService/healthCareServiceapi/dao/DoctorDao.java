package com.healthCareService.healthCareServiceapi.dao;

import com.healthCareService.healthCareServiceapi.model.entities.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorDao extends CrudRepository<Doctor, Long> {

    //SQL Query to select a doctor based on his email
    @Query("SELECT d FROM "
            + "doctor d WHERE d.email = ?1")
    Doctor selectByEmail(String email);

    //SQl Query to check if email exist
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END FROM "
            + "doctor d WHERE d.email = ?1")
    Boolean findByEmail(String email);

    //SQL server Query to return next 10 rows starting from the offset
    @Query(value = "SELECT * FROM doctor ORDER BY id OFFSET ?1 ROWS FETCH NEXT 10 ROWS ONLY", nativeQuery = true)
    List<Doctor> selectByOffset(int id);




}
