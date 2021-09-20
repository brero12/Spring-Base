package co.brero12.springboot.jpah2base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.brero12.springboot.jpah2base.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
