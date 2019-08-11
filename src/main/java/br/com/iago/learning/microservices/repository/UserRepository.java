package br.com.iago.learning.microservices.repository;

import br.com.iago.learning.microservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    @Query("select u.name from User u where u.id in (:pIdList)")
    List<String> findByIdList(@Param("pIdList") List<Long> idList);
}
