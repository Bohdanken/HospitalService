package ukma.edu.ua.VisitService.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ukma.edu.ua.VisitService.entities.PatientVisit;
import ukma.edu.ua.VisitService.exceptions.BadRequestException;
import ukma.edu.ua.VisitService.exceptions.NotFoundException;
import ukma.edu.ua.VisitService.dto.UpdateVisitBody;
import ukma.edu.ua.VisitService.dto.VisitBody;
import ukma.edu.ua.VisitService.repositories.PatientVisitRepository;
import ukma.edu.ua.VisitService.security.User;

@Service
@RequiredArgsConstructor
public class PatientVisitService {
	private final PatientVisitRepository patientVisitRepository;
	private final UserService userService;

	public PatientVisit createPatientVisit(VisitBody data) {
		var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		var patient = userService.getUserData(user.getId());
		if (!patient.hasPatientDetails()) {
			throw new BadRequestException("Patient not found");
		}

		var doctor = userService.getUserData(data.getDoctorId());
		if (!doctor.hasDoctorDetails()) {
			throw new BadRequestException("Doctor not found");
		}

		var patientVisit = PatientVisit.builder()
				.doctorId(doctor.getId())
				.patientId(patient.getId())
				.dateOfVisit(data.getDateOfVisit())
				.doctorFullName(
						doctor.getDoctorDetails().getFirstName() + " " + doctor.getDoctorDetails().getLastName())
				.patientFullName(
						patient.getPatientDetails().getFirstName() + " " + patient.getPatientDetails().getLastName())
				.build();

		return patientVisitRepository.save(patientVisit);
	}

	public PatientVisit[] getAllPatientVisits() {
		var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		var visits = patientVisitRepository.findByPatientId(user.getId());
		return visits.toArray(new PatientVisit[] {});
	}

	public PatientVisit getPatientVisit(Long visitId) {
		var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return patientVisitRepository.findByIdAndPatientId(visitId, user.getId())
				.orElseThrow(() -> new NotFoundException("Visit not found"));
	}

	public PatientVisit updateVisit(Long visitId, UpdateVisitBody data) {
		var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		var visit = patientVisitRepository.findByIdAndPatientId(visitId, user.getId())
				.orElseThrow(() -> new NotFoundException("Visit not found"));

		visit.setDateOfVisit(data.getDateOfVisit());

		return patientVisitRepository.save(visit);
	}

	public void deletePatientVisit(long id) {
		var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var data = patientVisitRepository.findByIdAndPatientId(id, user.getId())
				.orElseThrow(() -> new NotFoundException("Visit not found"));

		patientVisitRepository.delete(data);
	}
}
