package com.example.demo.Repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByusername(String UserName);
    public List<User> findByEmail(String email);

    //JPQL
    @Query("SELECT u FROM User u WHERE u.username = :username")
List<User> findUserByUsername(@Param("username") String username);

}