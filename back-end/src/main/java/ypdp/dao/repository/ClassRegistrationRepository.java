package ypdp.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ypdp.dao.ClassRegistration;

import java.util.List;

public interface ClassRegistrationRepository extends JpaRepository<ClassRegistration, String> {

    long countByClassId(String classId);
    int deleteByClassIdAndUserId(String classId, String userId);
    List<ClassRegistration> findByUserId(String userId);
}
