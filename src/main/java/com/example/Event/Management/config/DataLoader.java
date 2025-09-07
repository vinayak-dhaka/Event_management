package com.example.Event.Management.config;

import com.example.Event.Management.model.*;
import com.example.Event.Management.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final CollegeRepository collegeRepository;
    private final StudentRepository studentRepository;
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    private final AttendanceRepository attendanceRepository;

    public DataLoader(CollegeRepository collegeRepository,
                      StudentRepository studentRepository,
                      EventRepository eventRepository,
                      RegistrationRepository registrationRepository,
                      AttendanceRepository attendanceRepository) {
        this.collegeRepository = collegeRepository;
        this.studentRepository = studentRepository;
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1️⃣ College
        College college = collegeRepository.findByName("ABC College")
                .orElseGet(() -> {
                    College c = new College();
                    c.setName("ABC College");
                    return collegeRepository.save(c);
                });

        // 2️⃣ Student
        Student student = studentRepository.findByEmail("john@example.com")
                .orElseGet(() -> {
                    Student s = new Student();
                    s.setName("John Doe");
                    s.setEmail("john@example.com");
                    s.setCollege(college);
                    return studentRepository.save(s);
                });

        // 3️⃣ Event
        Event event = eventRepository.findByNameAndCollege("Tech Fest", college)
                .orElseGet(() -> {
                    Event e = new Event();
                    e.setName("Tech Fest");
                    e.setCollege(college);
                    return eventRepository.save(e);
                });

        // 4️⃣ Registration
        Registration registration = registrationRepository.findByStudentAndEvent(student, event)
                .orElseGet(() -> {
                    Registration r = new Registration();
                    r.setStudent(student);
                    r.setEvent(event);
                    return registrationRepository.save(r);
                });

        // 5️⃣ Attendance
        attendanceRepository.findByRegistration(registration)
                .orElseGet(() -> {
                    Attendance a = new Attendance();
                    a.setRegistration(registration);
                    a.setPresent(true);
                    return attendanceRepository.save(a);
                });
    }
}
