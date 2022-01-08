package net.junhabaek.aoporder.infra;

import net.junhabaek.aoporder.domain.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByName(String name);
}
