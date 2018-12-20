package priv.shen.hospitalregistersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import priv.shen.hospitalregistersystem.entity.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query(nativeQuery = true,
            value = "SELECT name FROM doctor WHERE department=?1")
    String[] findDoctorNamesByDepartment(String department);

    List<Doctor> findAllByNameLike(String name);
}
