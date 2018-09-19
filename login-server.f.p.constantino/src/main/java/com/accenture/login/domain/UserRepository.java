package com.accenture.login.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long>{

	public List<User> findByLastName(@Param("lastname") String lastname);

	@Query("select u from User u where u.username = ?1 and u.password = ?2")
	public User findByUsernameAndPassword(String username, String password);

	public List<User> findAllByOrderByIdDesc();

	public User findOneById(Long id);


}
