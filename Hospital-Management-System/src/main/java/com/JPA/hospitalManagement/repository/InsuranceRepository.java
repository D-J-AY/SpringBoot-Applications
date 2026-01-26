package com.JPA.hospitalManagement.repository;

import com.JPA.hospitalManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}