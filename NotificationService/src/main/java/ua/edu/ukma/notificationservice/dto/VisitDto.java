package ua.edu.ukma.notificationservice.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class VisitDto {
    private Long id;

    private Long patientId;

    private String patientFullName;

    private Long doctorId;

    private String doctorFullName;

    private Timestamp dateOfVisit;
}
