package net.junhabaek.aoporder.runner.low;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.junhabaek.aoporder.common.aop.exception.TestException;
import net.junhabaek.aoporder.domain.Student;
import net.junhabaek.aoporder.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(value=4)
public class LowPrecedenceAfterExceptionRunner implements ApplicationRunner {
    private final StudentService studentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String exceptionStudentName = "low-after";

        runServiceWithException(exceptionStudentName);
        checkStudentDoesNotExist(exceptionStudentName);
        log.info("low-after completed with expected result");
    }

    private void runServiceWithException(String name){
        try{
            studentService.createStudentWithLowPrecedenceAfterException(name);
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
