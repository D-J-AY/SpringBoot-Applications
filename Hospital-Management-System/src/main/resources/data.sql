INSERT INTO patient(name,dob,blood_group,email,gender)
VALUES
    ('Ram Sharma','1990-05-10','O_Positive','ram@gmail.com','MALE'),
    ('Kamla Patel','1995-08-20','A_Positive','kamla@gmail.com','FEMALE'),
    ('Rahul Varma','1988-03-15','A_Positive','rahul@gmail.com','MALE'),
    ('Neha Iyer','1992-10-01','AB_Positive','neha@gmail.com','FEMALE'),
    ('Kabir Singh','1993-07-11','O_Positive','kabir@gmail.com','MALE');

INSERT INTO doctor (name,specialization,email)
VALUES
    ('Dr.Rajesh Khetan','Cardiology','rajesh@gmail.com'),
    ('Dr.Sejal Kamble','Neurology','sejal@gmail.com'),
    ('Dr.Arjun Kapoor','Orthopedics','arjun@gmail.com');

INSERT INTO appointment(appointment_time,reason,patient_id,doctor_id)
VALUES
    ('2025-07-01 10:30:00','General Checkup',1,1),
    ('2025-07-02 10:30:00','Skin Rash',2,2),
    ('2025-07-03 10:30:00','Knee Pain',2,3),
    ('2025-07-04 10:30:00','Follow-Up',1,1),
    ('2025-07-05 10:30:00','Allergy Treatment',2,2);
