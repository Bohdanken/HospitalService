package ukma.edu.ua.MedicineSourceService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ukma.edu.ua.MedicineSourceService.entity.Drug;
import ukma.edu.ua.MedicineSourceService.repository.DrugRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrugService {

    private final DrugRepository drugRepository;

    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    public List<Drug> getDrugsByProducer(String producer) {
        return drugRepository.findByProducerContainingIgnoreCase(producer);
    }

    public List<Drug> getDrugsByGenericName(String genericName) {
        return drugRepository.findByGenericNameContainingIgnoreCase(genericName);
    }

    public List<Drug> getAllDrugsById(List<Long> ids) {
        return drugRepository.findAllById(ids);
    }

    public Drug getDrugByName(String brandName) {
        return drugRepository.findByBrandName(brandName);
    }

    public void saveDrugsFromCSV(MultipartFile file) throws Exception {
        log.info("Inside saveDrugsFromCSV");

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> rows = csvReader.readAll();
            log.info("Parsed {} rows from CSV file.", rows.size());

            for (String[] row : rows) {
                Drug drug = new Drug();
                drug.setBrandName(row[0]);
                drug.setGenericName(row[1]);
                drug.setProducer(row[2]);
                drug.setDescription(row[3]);

                log.info("Saving drug: {}", drug.getBrandName());
                drugRepository.save(drug);
            }
        } catch (Exception e) {
            log.error("Error while processing CSV file: {}", e.getMessage());
            throw e;
        }
    }
}
