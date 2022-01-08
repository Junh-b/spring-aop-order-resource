package net.junhabaek.aoporder.runner.low;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.junhabaek.aoporder.common.aop.exception.TestException;
import net.junhabaek.aoporder.domain.Student;
import net.junhabaek.aoporder.service.StudentService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(value=3)
public class LowPrecedenceBeforeExceptionRunner implements ApplicationRunner {
    private final StudentService studentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String exceptionStudentName = "low-before";

        runServiceWithException(exceptionStudentName);
        checkStudentDoesNotExist(exceptionStudentName);
        log.info("low-before completed with expected result");
    }

    private void runServiceWithException(String name){
        try{
            studentService.createStudentWithLowPrecedenceBeforeException(name);
        }
        catch(Throwable t){
            Assert.isInstanceOf(TestException.class, t);
        }
    }

    private void checkStudentDoesNotExist(String name){
        Optional<Student> studentOptional = studentService.findStudentByName(name);

        Assert.isTrue(studentOptional.isEmpty(), "expected : not exist, actual : exist");
    }
}
