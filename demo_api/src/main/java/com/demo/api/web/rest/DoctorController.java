package com.demo.api.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.entity.Doctor;
import com.demo.api.repository.DoctorRepository;
import com.demo.api.web.rest.dto.DoctorDTO;
import com.demo.api.web.rest.mapper.DoctorMapper;
import com.demo.api.web.rest.util.HeaderUtil;
import com.demo.api.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class DoctorController {
	private final Logger log = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private DoctorMapper doctorMapper;

	@RequestMapping(value = "/doctor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DoctorDTO> create(
			@Valid @RequestBody DoctorDTO doctorDTO) throws URISyntaxException {
		log.debug("REST request to save doctor : {}", doctorDTO);
		if (doctorDTO.getId() != null) {
			return ResponseEntity
					.badRequest()
					.header("Failure", "A new doctor cannot already have an ID")
					.body(null);
		}
		
		Doctor doctor = doctorMapper.doctorDTOToDoctor(doctorDTO);

		Doctor result = doctorRepository.save(doctor);
		return ResponseEntity
				.created(new URI("/api/doctorentity/" + result.getId()))
				.headers(
						HeaderUtil.createEntityCreationAlert("doctorentity",
								result.getId().toString()))
				.body(doctorMapper.doctorToDoctorDTO(result));
	}

	@RequestMapping(value = "/getAllDoctor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(readOnly = true)
	public ResponseEntity<List<DoctorDTO>> getAll(
			@RequestParam(value = "page", required = false) Integer offset,
			@RequestParam(value = "per_page", required = false) Integer limit)
			throws URISyntaxException {
		Page<Doctor> page = doctorRepository.findAll(PaginationUtil
				.generatePageRequest(offset, limit));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
				page, "/api/doctor", offset, limit);
		return new ResponseEntity<>(page.getContent().stream()
				.map(doctorMapper::doctorToDoctorDTO)
				.collect(Collectors.toCollection(LinkedList::new)), headers,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/getSingleDoctor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(readOnly = true)
	public ResponseEntity<DoctorDTO> get(@PathVariable String id) {
		log.debug("REST request to get doctor : {}", id);
		return Optional
				.ofNullable(doctorRepository.findOne(id))
				.map(doctorMapper::doctorToDoctorDTO)
				.map(DoctorDTO -> new ResponseEntity<>(DoctorDTO, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/editDoctorDetails", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DoctorDTO> update(
			@Valid @RequestBody DoctorDTO doctorDTO) throws URISyntaxException {
		log.debug("REST request to update Doctor : {}", doctorDTO);
		if (doctorDTO.getId() == null) {
			return create(doctorDTO);
		}
		Doctor allocation = doctorMapper.doctorDTOToDoctor(doctorDTO);
		Doctor result = doctorRepository.save(allocation);
		return ResponseEntity
				.ok()
				.headers(
						HeaderUtil.createEntityUpdateAlert("doctor", doctorDTO
								.getId().toString()))
				.body(doctorMapper.doctorToDoctorDTO(result));
	}

	/**
	 * DELETE /removeDoctor/:id -> delete the "id" removeDoctor.
	 */
	@RequestMapping(value = "/removeDoctor/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		log.debug("REST request to delete doctor : {}", id);
		doctorRepository.delete(id);
		return ResponseEntity
				.ok()
				.headers(
						HeaderUtil.createEntityDeletionAlert("doctor",
								id.toString())).build();
	}

}
