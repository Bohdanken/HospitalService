package ukma.edu.ua.MedicineSourceService.controller;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.MedicineSourceService.entity.Drug;
import ukma.edu.ua.MedicineSourceService.service.DrugService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Drugs")
public class DrugController {

    private final DrugService drugService;

    // @GetMapping("/drugs")
    // public List<Drug> getAllDrugs(@RequestParam(required = false) String name,
    // @RequestParam(required = false) String producer,
    // @RequestParam(required = false) String genericName) {
    // if (name != null) {
    // return List.of(drugService.getDrugByName(name));
    // } else if (producer != null) {
    // return drugService.getDrugsByProducer(producer);
    // } else if (genericName != null) {
    // return drugService.getDrugsByGenericName(genericName);
    // } else {
    // return drugService.getAllDrugs();
    // }
    // }

    @GetMapping("/drugs")
    @Operation(summary = "Get all drugs")
    public List<Drug> getAllDrugs(@RequestParam(required = false) List<Long> ids) {
        if (ids != null) {
            return drugService.getAllDrugsById(ids);
        }

        return drugService.getAllDrugs();
    }

    @PostMapping("/admin/drugs")
    @Operation(summary = "Upload CSV file with drugs")
    public String uploadDrugsFromCSV(@RequestParam("file") MultipartFile file) {
        try {
            drugService.saveDrugsFromCSV(file);
            return "File uploaded successfully";
        } catch (Exception e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }
}
