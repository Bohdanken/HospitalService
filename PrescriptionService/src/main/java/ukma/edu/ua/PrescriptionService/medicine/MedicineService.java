package ukma.edu.ua.PrescriptionService.medicine;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.PrescriptionService.medicine.dto.DrugResponseBody;

@Service
@RequiredArgsConstructor
public class MedicineService {
	@Qualifier("medicineServiceClient")
	private final WebClient medicineServiceClient;

	public List<DrugResponseBody> getDrugsByIds(List<Long> ids) {
		try {
			return medicineServiceClient
					.get()
					.uri(uri -> uri.path("/api/drugs").queryParam("ids", ids).build())
					.retrieve()
					.toEntityList(DrugResponseBody.class)
					.block()
					.getBody();
		} catch (RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching list of drugs");
		}
	}
}
