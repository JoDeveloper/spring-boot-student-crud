package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
    boolean exists = studentRepository.existsById(id);
    if(!exists){
        throw new IllegalStateException("student with id "+id+" does not exist");
    }
    studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String email, String name) {
        System.out.println(email);
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalStateException("student with id "+id+" does not exist"));

        if(name !=null && name.length() > 0 && Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email != null && email.length() > 0 &&  Objects.equals(student.getEmail(),email)){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

            if(studentByEmail.isPresent()){
                throw new IllegalStateException("email already exists");
            }
            student.setEmail(email);
        }
    }
}
