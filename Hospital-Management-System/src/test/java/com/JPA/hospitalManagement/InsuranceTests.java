package com.JPA.hospitalManagement;

import com.JPA.hospitalManagement.entity.Appointment;
import com.JPA.hospitalManagement.entity.Insurance;
import com.JPA.hospitalManagement.entity.Patient;
import com.JPA.hospitalManagement.service.AppointmentService;
import com.JPA.hospitalManagement.service.InsuranceService;
import com.JPA.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {
    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void insuranceTest(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);

        var newPatient = insuranceService.removeInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }

    @Test
    public void createAppointmentTest(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,1,14,0,0))
                .reason("Cancer")
                .build();

        var newAppointment = appointmentService.createAppointment(appointment,1L,2L);

        System.out.println(newAppointment);

        var updatedAppointment = appointmentService.reassignAppointmentToAnotherDoctor(newAppointment.getId(), 2L);
        System.out.println(updatedAppointment);
    }

}
