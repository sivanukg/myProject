package com.demo.api.web.rest.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.api.entity.Doctor;
import com.demo.api.web.rest.dto.DoctorDTO;

@Component
public class DoctorMapper {

	public DoctorDTO doctorToDoctorDTO(Doctor doctor) {
		if (doctor == null) {
			return null;
		}

		DoctorDTO doctorDTO = new DoctorDTO();

		doctorDTO.setId(doctor.getId());
		doctorDTO.setFirstname(doctor.getFirstname());
		doctorDTO.setLastname(doctor.getLastname());
		doctorDTO.setAddress(doctor.getAddress());

		return doctorDTO;
	}

	public Doctor doctorDTOToDoctor(DoctorDTO doctorDTO) {
		if (doctorDTO == null) {
			return null;
		}

		Doctor doctor = new Doctor();

		doctor.setId(doctorDTO.getId());
		doctor.setFirstname(doctorDTO.getFirstname());
		doctor.setLastname(doctorDTO.getLastname());
		doctor.setAddress(doctorDTO.getAddress());

		return doctor;
	}

}
