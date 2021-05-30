package com.demo.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.demo.api.entity.Doctor;

public interface DoctorRepository extends Repository<Doctor, String> {

	void delete(String id);

	Page<Doctor> findAll(Pageable page);

	Doctor findOne(String id);

	Doctor save(Doctor doctor);
	
}
