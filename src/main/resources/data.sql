-- HOSPITAL

INSERT INTO hospital (name, address)
VALUES ('New clinic', 'Wall str. 12A');

-- DOCTOR

INSERT INTO doctor (first_name, last_name, email, birth_date, doctor_type, hospital_id, password)
VALUES ('Will', 'Smith', 'doc@gmail.com', '1980-01-01', 'CARDIOLOGIST', 1,
        '$2a$12$1zdfH1oSTAEbaCODXcBnQuNvlf95SvsMto/C6CmXHircOqJluNFsa');

-- PATIENT

INSERT INTO patient (first_name, last_name, email, address, passport_number, birth_date, password)
VALUES ('Andrew', 'Tate', 'andrew@gmail.com', 'some address', 'YU123456', '1980-01-01',
        '$2a$12$1zdfH1oSTAEbaCODXcBnQuNvlf95SvsMto/C6CmXHircOqJluNFsa');

-- DRUG

INSERT INTO drug (name, producer)
VALUES ('Noshpa', 'Pfizer');
INSERT INTO drug (name, producer)
VALUES ('Vitamin B', 'Pfizer');
INSERT INTO drug (name, producer)
VALUES ('Folic acid', 'Moderna');

-- PRESCRIPTION

INSERT INTO prescription (date_of_issue, patient_id)
VALUES ('2023-09-01', 1);
INSERT INTO prescription (date_of_issue, patient_id)
VALUES ('2023-09-01', 1);
INSERT INTO prescription (date_of_issue, patient_id)
VALUES ('2023-09-05', 1);
INSERT INTO prescription (date_of_issue, patient_id)
VALUES ('2023-09-10', 1);
INSERT INTO prescription (date_of_issue, patient_id)
VALUES ('2023-10-01', 1);

INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (1, 1);
INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (1, 3);
INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (2, 2);
INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (2, 3);
INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (3, 1);
INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (4, 1);
INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (4, 2);
INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (4, 3);
INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (5, 1);
INSERT INTO prescription_drug_map (prescription_id, drug_id)
VALUES (5, 3);
