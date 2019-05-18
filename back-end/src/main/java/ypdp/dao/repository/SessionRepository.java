package ypdp.dao.repository;

import ypdp.dao.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, String> {

    List<Session> findByUserId(String userId);
}
