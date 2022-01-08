package net.junhabaek.aoporder.runner;

import lombok.RequiredArgsConstructor;
import net.junhabaek.aoporder.service.StudentService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(value = 0)
public class OrderRunner implements ApplicationRunner {
    private final StudentService studentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Long id = studentService.createStudent("jun");
    }
}
