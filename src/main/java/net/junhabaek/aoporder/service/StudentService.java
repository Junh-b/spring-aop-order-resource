package net.junhabaek.aoporder.service;

import lombok.RequiredArgsConstructor;
import net.junhabaek.aoporder.common.aop.exception.high.HighPrecedenceAfterExceptionAnnotation;
import net.junhabaek.aoporder.common.aop.exception.high.HighPrecedenceBeforeExceptionAnnotation;
import net.junhabaek.aoporder.common.aop.exception.low.LowPrecedenceAfterExceptionAnnotation;
import net.junhabaek.aoporder.common.aop.exception.low.LowPrecedenceBeforeExceptionAnnotation;
import net.junhabaek.aoporder.common.aop.order.HighPrecedenceAnnotation;
import net.junhabaek.aoporder.common.aop.order.MiddlePrecedenceAnnotation;
import net.junhabaek.aoporder.domain.Student;
import net.junhabaek.aoporder.infra.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    // transaction not even start
    @Transactional
    @HighPrecedenceBeforeExceptionAnnotation
    public Long createStudentWithHighPrecedenceBeforeException(String name){
        Student student = Student.newStudent(name);
        studentRepository.save(student);
        return student.getId();
    }

    // transaction will be committed
    @Transactional
    @HighPrecedenceAfterExceptionAnnotation
    public Long createStudentWithHighPrecedenceAfterException(String name){
        Student student = Student.newStudent(name);
        studentRepository.save(student);
        return student.getId();
    }

    // transaction will be rolled back
    @Transactional
    @LowPrecedenceBeforeExceptionAnnotation
    public Long createStudentWithLowPrecedenceBeforeException(String name){
        Student student = Student.newStudent(name);
        studentRepository.save(student);
        return student.getId();
    }

    // transaction will be rolled back
    @Transactional
    @LowPrecedenceAfterExceptionAnnotation
    public Long createStudentWithLowPrecedenceAfterException(String name){
        Student student = Student.newStudent(name);
        studentRepository.save(student);
        return student.getId();
    }

    public Optional<Student> findStudentByName(String name){
        Optional<Student> studentOptional = studentRepository.findByName(name);
        return studentOptional;
    }
}
