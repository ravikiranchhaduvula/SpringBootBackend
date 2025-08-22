package com.example.repository;

import com.example.DTO.UserDTO;
import com.example.entity.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//JpaSpecificationExecutor adds findAll(Specification, Pageable), count(Specification) etc.
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    //Now the result list (ids) is cached in hibernate.query.results, and individual entities
    // are read from the entity region.
    //Caveat: query cache must be invalidated on writes; it’s best for read-mostly data.
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    List<User> findByName(String name);

    // JPQL -> uses the ENTITY name (User) and its FIELD names (name), not table/column names.
    @Query("SELECT u FROM User u WHERE u.name = :userFirstName")
    List<User> findUserByExactName(@Param("userFirstName") String userName);

    // JPQL with LIKE + pagination
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :q, '%'))")
    Page<User> searchByNameLike(@Param("q") String query, Pageable pageable);

    // Native SQL variant (uses TABLE/COLUMN names)
    @Query(value = "SELECT * FROM users u WHERE u.name = :name", nativeQuery = true)
    List<User> findNativeByName(@Param("name") String name);

    @Query("SELECT new com.example.DTO.UserDTO(u.name, u.email) " +
            "FROM User u WHERE u.name = :userFirstName")
    List<UserDTO> findUserDetails(@Param("userFirstName") String userName);

    // Use EntityGraph to eagerly load roles (kills N+1) while keeping the query simple.
    @EntityGraph(attributePaths = "userRoles")
    List<User> findAll(Specification<User> spec);

    Optional<User> findByEmail(String email);   // we’ll login with email

    @EntityGraph(attributePaths = "userRoles")
    Optional<User> findWithRolesByEmail(String email);
}