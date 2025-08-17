package com.example.repository;

import com.example.DTO.UserDTO;
import com.example.entity.User;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

//Repository with constructor projection (pageable & sortable safely)
public interface UserDTORepository extends JpaRepository<User, Long> {

    @Query(
            value = """
    select new com.example.DTO.UserDTO(u.id, u.name, u.email)
    from User u
    where (:q is null
       or lower(u.name)  like lower(concat('%', :q, '%'))
       or lower(u.email) like lower(concat('%', :q, '%')))
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
    Page<UserDTO> searchAsDto(@Param("q") String q, Pageable pageable);
}


