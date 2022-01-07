package net.junhabaek.aoporder.infra;

import net.junhabaek.aoporder.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
