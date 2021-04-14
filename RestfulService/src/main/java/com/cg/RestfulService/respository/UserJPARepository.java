package com.cg.RestfulService.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.RestfulService.entity.User;

@Repository(value = "mysqldao")
public interface UserJPARepository extends JpaRepository<User, Integer>, IDao {

	@Query(value = "from User where :username = name") // JPQL -> table name=Entity class
	Optional<User> findByName(String username);

}
