package fun.zepo.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fun.zepo.auth.model.User;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         <strong>User Repo using JPA to get User From MySQL DB</strong>
 */
public interface UserRepository extends JpaRepository<User, String>
{
	User findByEmail(String email);
}
