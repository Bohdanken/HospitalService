-- HOSPITAL

INSERT INTO hospital (name, address)
VALUES ('New clinic', 'Wall str. 12A');

-- USER

INSERT INTO app_user (email, password, role)
VALUES ('doc1@gmail.com', '$2a$12$1zdfH1oSTAEbaCODXcBnQuNvlf95SvsMto/C6CmXHircOqJluNFsa', 'DOCTOR');

INSERT INTO app_user (email, password, role)
VALUES ('patient1@gmail.com', '$2a$12$1zdfH1oSTAEbaCODXcBnQuNvlf95SvsMto/C6CmXHircOqJluNFsa', 'PATIENT');

-- DOCTOR DETAILS

INSERT INTO doctor_details (first_name, last_name, birth_date, doctor_type, hospital_id, user_id)
VALUES ('Will', 'Smith', '1980-01-01', 'CARDIOLOGIST', 1, 1);

-- PATIENT DETAILS

INSERT INTO patient_details (first_name, last_name, address, passport_number, birth_date, user_id)
VALUES ('Andrew', 'Tate', 'some address', 'YU123456', '1980-01-01', 2);

-- DRUG

INSERT INTO drug (name, producer)
VALUES ('Noshpa', 'Pfizer');
INSERT INTO drug (name, producer)
VALUES ('Vitamin B', 'Pfizer');
INSERT INTO drug (name, producer)
VALUES ('Folic acid', 'Moderna');

-- PRESCRIPTION

INSERT INTO prescription (date_of_issue, patient_details_id)
VALUES ('2023-09-01', 1);
INSERT INTO prescription (date_of_issue, patient_details_id)
VALUES ('2023-09-01', 1);
INSERT INTO prescription (date_of_issue, patient_details_id)
VALUES ('2023-09-05', 1);
INSERT INTO prescription (date_of_issue, patient_details_id)
VALUES ('2023-09-10', 1);
INSERT INTO prescription (date_of_issue, patient_details_id)
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
