package ukma.edu.ua.HospitalApp.medicine;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.medicine.dto.DrugResponseBody;

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
			return null;
		}
	}
}
