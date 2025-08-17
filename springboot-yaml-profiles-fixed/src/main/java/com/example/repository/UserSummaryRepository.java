package com.example.repository;

import com.example.DTO.UserWithRoleCountView;
import com.example.entity.User;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UserSummaryRepository extends JpaRepository<User, Long> {

    @Query(
            value = """
      select u.id as id,
             u.name as name,
             u.email as email,
             count(ur) as roleCount
      from User u
      left join u.userRoles ur
      where (:q is null
         or lower(u.name)  like lower(concat('%', :q, '%'))
         or lower(u.email) like lower(concat('%', :q, '%')))
      group by u.id, u.name, u.email
      order by u.id desc
      """,
            countQuery = """
      select count(u)
      from User u
      where (:q is null
         or lower(u.name)  like lower(concat('%', :q, '%'))
         or lower(u.email) like lower(concat('%', :q, '%')))
      """
    )
    Page<UserWithRoleCountView> findSummaries(@Param("q") String q, Pageable pageable);
}

