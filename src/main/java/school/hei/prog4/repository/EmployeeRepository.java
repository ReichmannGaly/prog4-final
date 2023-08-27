package school.hei.prog4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.hei.prog4.entity.EmployeeEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    @Query(value = "SELECT * FROM employee ORDER BY last_name ASC", nativeQuery = true)
    List<EmployeeEntity> findAllOrderByLastNameAsc();

    @Query(value = "SELECT * FROM employee ORDER BY last_name DESC", nativeQuery = true)
    List<EmployeeEntity> findAllOrderByLastNameDesc();
    @Query(value = "SELECT * FROM employee ORDER BY first_name ASC", nativeQuery = true)
    List<EmployeeEntity> findAllOrderByFirstNameAsc();
    @Query(value = "SELECT * FROM employee ORDER BY first_name DESC", nativeQuery = true)
    List<EmployeeEntity> findAllOrderByFirstNameDesc();

    @Query(value = "SELECT * FROM employee ORDER BY position ASC", nativeQuery = true)
    List<EmployeeEntity> findAlOrderByPositionAsc();

    @Query(value = "SELECT * FROM employee ORDER BY position DESC", nativeQuery = true)
    List<EmployeeEntity> findAlOrderByPositionDesc();

    @Query(value = "SELECT * FROM employee WHERE gender = :gender", nativeQuery = true)
    List<EmployeeEntity> findAllByGender(String gender);

    List<EmployeeEntity> findAllByFirstNameContainingIgnoreCase(String firstName);

    List<EmployeeEntity> findAllByLastNameContainingIgnoreCase(String lastName);

    List<EmployeeEntity> findAllByHireDateBetween(LocalDate startDate, LocalDate endDate);

    List<EmployeeEntity> findAllByResignationDateBetween(LocalDate startDate, LocalDate endDate);
}
