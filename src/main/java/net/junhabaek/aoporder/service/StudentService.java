package net.junhabaek.aoporder.service;

import lombok.RequiredArgsConstructor;
import net.junhabaek.aoporder.common.aop.exception.CreateExceptionAnnotation;
import net.junhabaek.aoporder.common.aop.order.HighPrecedenceAnnotation;
import net.junhabaek.aoporder.common.aop.order.MiddlePrecedenceAnnotation;
import net.junhabaek.aoporder.domain.Student;
import net.junhabaek.aoporder.infra.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional
    @HighPrecedenceAnnotation
    @MiddlePrecedenceAnnotation
    public Long createStudent(String name){
        Student student = Student.newStudent(name);
        studentRepository.save(student);
        return student.getId();
    }

    @Transactional
    @CreateExceptionAnnotation
    public Long createStudentWithException(String name){
        Student student = Student.newStudent(name);
        studentRepository.save(student);
        return student.getId();
    }
}
