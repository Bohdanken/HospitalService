package ua.edu.ukma.medicinesourceservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import ua.edu.ukma.medicinesourceservice.entity.Drug;
import ua.edu.ukma.medicinesourceservice.service.DrugService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DrugController {

    private final DrugService drugService;

    @GetMapping("/drugs")
    public List<Drug> getAllDrugs(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String producer,
                                  @RequestParam(required = false) String genericName) {
        if (name != null) {
            return List.of(drugService.getDrugByName(name));
        } else if (producer != null) {
            return drugService.getDrugsByProducer(producer);
        } else if (genericName != null) {
            return drugService.getDrugsByGenericName(genericName);
        } else {
            return drugService.getAllDrugs();
        }
    }

    @PostMapping("/admin/drugs")
    public String uploadDrugsFromCSV(@RequestParam("file") MultipartFile file, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "Invalid API Key";
        }

        try {
            drugService.saveDrugsFromCSV(file);
            return "File uploaded successfully";
        } catch (Exception e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }
}

