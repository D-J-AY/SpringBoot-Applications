package com.JPA.hospitalManagement.controller;


import com.JPA.hospitalManagement.dto.AppointmentResponseDto;
import com.JPA.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.JPA.hospitalManagement.entity.Patient;
import com.JPA.hospitalManagement.service.AppointmentService;
import com.JPA.hospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }
    @GetMapping("/profile")
    private ResponseEntity<Patient> getPatientProfile() {
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }
}
