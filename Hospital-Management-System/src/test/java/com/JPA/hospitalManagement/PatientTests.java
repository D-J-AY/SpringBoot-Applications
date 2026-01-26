package com.JPA.hospitalManagement;

import com.JPA.hospitalManagement.dto.BloodGroupCountEntity;
import com.JPA.hospitalManagement.entity.Patient;
import com.JPA.hospitalManagement.repository.PatientRepository;
import com.JPA.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        List<Patient> patientList = patientRepository.findAllPatientWithAppointment();
        System.out.println(patientList);

    }

    @Test
    public void testTransactionMethods(){
//        Patient patient = patientService.getPatientById(1L);
//       Patient patient = patientRepository.findByName("Dj");

//        List<Patient>  patientList = patientRepository.findByDobOrEmail(LocalDate.of(2003, 5,25), "ram@gmail.com");

//        List<Patient>  patientList = patientRepository.findByBornAfterDate(LocalDate.of(2003, 5,24));

//        Page<Patient> patientList = patientRepository.findAllPatients(PageRequest.of(0, 2, Sort.by("id").descending()));
//        for(Patient patient : patientList){
//            System.out.println(patient);
//        }
////
//    List<Object[]> bloodGroupList =  patientRepository.countBloodGroup();
//
//       for(Object[] bloodGroup : bloodGroupList){
//           System.out.println(bloodGroup[0]+" "+bloodGroup[1]);
//       }

//    int rowsUpdated = patientRepository.updateNameWithId("Djay",1L);
//       System.out.println(rowsUpdated);
//
//        List<BloodGroupCountEntity> bloodGroupList =  patientRepository.countBloodGroup();
//       for(BloodGroupCountEntity BGCountResponse : bloodGroupList){
//           System.out.println(BGCountResponse);
//       }
    }
}
