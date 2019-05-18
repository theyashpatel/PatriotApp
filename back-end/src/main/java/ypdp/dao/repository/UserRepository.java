package ypdp.dao.repository;

import ypdp.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {

    Users findByEmail(String email);
}
