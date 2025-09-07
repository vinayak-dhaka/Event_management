package com.example.Event.Management.controller;

import com.example.Event.Management.repository.RegistrationRepository;
import com.example.Event.Management.repository.AttendanceRepository;
import com.example.Event.Management.repository.StudentRepository;
import com.example.Event.Management.repository.EventRepository;
import com.example.Event.Management.model.Student;
import com.example.Event.Management.model.Event;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EventRepository eventRepository;

    // 1️⃣ Event Popularity → by number of registrations
    @GetMapping("/popularity")
    public List<Map<String, Object>> eventPopularity() {
        List<Event> events = eventRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Event e : events) {
            long count = registrationRepository.countByEventEventId(e.getEventId());
            Map<String, Object> map = new HashMap<>();
            map.put("eventName", e.getName());
            map.put("registrations", count);
            result.add(map);
        }

        // Sort descending by registrations
        result.sort((a, b) -> Long.compare((Long)b.get("registrations"), (Long)a.get("registrations")));
        return result;
    }

    // 2️⃣ Student Participation → how many events each student attended
    @GetMapping("/participation")
    public List<Map<String, Object>> studentParticipation() {
        List<Student> students = studentRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Student s : students) {
            long attended = attendanceRepository.countByRegistrationStudentStudentIdAndPresent(s.getStudentId(), true);
            Map<String, Object> map = new HashMap<>();
            map.put("studentName", s.getName());
            map.put("eventsAttended", attended);
            result.add(map);
        }

        // Sort descending by events attended
        result.sort((a, b) -> Long.compare((Long)b.get("eventsAttended"), (Long)a.get("eventsAttended")));
        return result;
    }

    // 3️⃣ Top 3 Most Active Students
    @GetMapping("/top-students")
    public List<Map<String, Object>> topStudents() {
        List<Map<String, Object>> participation = studentParticipation();
        return participation.stream().limit(3).collect(Collectors.toList());
    }
}
