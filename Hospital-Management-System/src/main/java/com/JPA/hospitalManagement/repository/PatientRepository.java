package com.JPA.hospitalManagement.repository;

import com.JPA.hospitalManagement.dto.BloodGroupCountEntity;
import com.JPA.hospitalManagement.entity.Patient;
import com.JPA.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByName(String dj);

    List<Patient> findByDobOrEmail(LocalDate dob, String email);

    @Query("SELECT p FROM Patient p where p.dob > :dateOfBirth")
    List<Patient> findByBornAfterDate(@Param("dateOfBirth")LocalDate dob);

    @Query("select new com.JPA.hospitalManagement.dto.BloodGroupCountEntity(p.bloodGroup, Count(p)) from Patient p group by p.bloodGroup")
//    List<Object[]> countBloodGroup();
    List<BloodGroupCountEntity> countBloodGroup();

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name =:name where p.id=:id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);


//    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientWithAppointment();
}
