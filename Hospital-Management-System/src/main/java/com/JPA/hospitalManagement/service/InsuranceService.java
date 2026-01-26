package com.JPA.hospitalManagement.service;

import com.JPA.hospitalManagement.entity.Insurance;
import com.JPA.hospitalManagement.entity.Patient;
import com.JPA.hospitalManagement.repository.InsuranceRepository;
import com.JPA.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance,Long patientId) {
        Patient patient =  patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient not found with Id: " + patientId));

        patient.setInsurance(insurance);

        insurance.setPatient(patient); //Bidirectional Consistancy maintainence
        return patient;
    }

    @Transactional
    public Patient removeInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient not found with Id: " + patientId));

        patient.setInsurance(null);
        return patient;
    }
}
