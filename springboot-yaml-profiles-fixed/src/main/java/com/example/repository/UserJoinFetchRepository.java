package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

//JOIN FETCH variant (simple by-id search; note: avoid Pageable with to-many fetch)
public interface UserJoinFetchRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "userRoles")
    @Query("""
    select u
    from User u
    where (:q is null
       or lower(u.name)  like lower(concat('%', :q, '%'))
       or lower(u.email) like lower(concat('%', :q, '%')))
  """)
    List<User> searchWithRoles(@Param("q") String q);
}


