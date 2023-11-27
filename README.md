# Hospital service

This project exposes an API for private medical institutions to operate with their patients, allow patients operate on their visits and doctors to track visits and issue prescriptions.

There are be 4 roles in this project: admin, hospital, doctor, patient.

### Admin

- Admins are predefined and seeded to a database by a tech lead.
- Admins would have access to admin panel where they can register hospitals and see the overall info about the system.

### Hospital

- Hospitals are registered by admins and given an API key that is used to perform different actions.
- Hospital should be able to create/update/delete a visit for certain patient.
- Hospital can also register a doctor or a patient in the service.
- Same patient should be same across different hospitals

### Doctor

- Doctor can work in many hospitals
- Doctors can delete their visits if needed.
- Only doctor can issue a presription for a patient.

### Patient

- Patients are registered by themselves.
- Patients can manage their visits to doctors in certain hospitals on certain date and time.
- Patients can access their prescriptions.


#### Team 

- Bohdan Shevchenko
- Maksym Polishuk
- Ilya Dubov
