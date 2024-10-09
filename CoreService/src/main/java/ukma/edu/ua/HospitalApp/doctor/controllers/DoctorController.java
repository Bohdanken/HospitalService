package ukma.edu.ua.HospitalApp.doctor.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.doctor.dto.UpdateDoctorBody;
import ukma.edu.ua.HospitalApp.doctor.services.DoctorServiceInternal;
import ukma.edu.ua.HospitalApp.common.Endpoints;
import ukma.edu.ua.HospitalApp.common.dto.DoctorDTO;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.prefix}" + Endpoints.DOCTOR)
public class DoctorController {
	private final DoctorServiceInternal doctorService;

	@PutMapping("/{id}")
	@Operation(summary = "Update doctor info")
	public DoctorDTO updateDoctor(
			@Valid @RequestParam Long id,
			@Valid @RequestBody UpdateDoctorBody body) {
		return doctorService.updateDoctor(id, body);
	}
}
