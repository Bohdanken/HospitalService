package ukma.edu.ua.HospitalApp.services;

import ukma.edu.ua.HospitalApp.models.Hospital;

public interface HospitalService {
    Hospital createHospital(Hospital hospital);

    Hospital updateHospital(long id, Hospital hospital);

    void deleteHospital(Hospital hospital);
}
