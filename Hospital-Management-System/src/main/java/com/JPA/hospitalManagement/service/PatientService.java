package com.JPA.hospitalManagement.service;

import com.JPA.hospitalManagement.dto.PatientResponseDto;
import com.JPA.hospitalManagement.entity.Patient;
import com.JPA.hospitalManagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow();
    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDto.class))
                .collect(Collectors.toList());
    }
}
