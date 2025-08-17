// src/main/java/com/example/service/UserDynamicNativeService.java
package com.example.service;

import com.example.DTO.UserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDynamicNativeService {

    @PersistenceContext
    private EntityManager em;

    // Allowed sort columns to prevent SQL injection in ORDER BY
    private static final Set<String> SORT_WHITELIST = Set.of("id", "name", "email");

    public List<UserDTO> search(
            String nameContains,           // optional
            String emailDomain,            // optional, e.g. "gmail.com"
            String sortBy,                 // optional, default id
            String dir,                    // "asc"/"desc"
            Integer page, Integer size     // pagination
    ) {
        StringBuilder sql = new StringBuilder();
        sql.append("""
            SELECT u.id, u.name, u.email
            FROM users u
            WHERE 1=1
            """);

        Map<String, Object> params = new HashMap<>();

        if (nameContains != null && !nameContains.isBlank()) {
            sql.append(" AND LOWER(u.name) LIKE LOWER(CONCAT('%', :nameLike, '%'))");
            params.put("nameLike", nameContains);
        }
        if (emailDomain != null && !emailDomain.isBlank()) {
            sql.append(" AND LOWER(u.email) LIKE LOWER(CONCAT('%', :domain))");
            params.put("domain", "@" + emailDomain.toLowerCase());
        }

        // Sort (whitelisted)
        String sort = (sortBy != null && SORT_WHITELIST.contains(sortBy)) ? sortBy : "id";
        String direction = ("desc".equalsIgnoreCase(dir)) ? "DESC" : "ASC";
        sql.append(" ORDER BY u.").append(sort).append(" ").append(direction);

        Query q = em.createNativeQuery(sql.toString());

        // Pagination
        int pageNum = (page == null || page < 0) ? 0 : page;
        int pageSize = (size == null || size <= 0 || size > 100) ? 10 : size;
        q.setFirstResult(pageNum * pageSize);
        q.setMaxResults(pageSize);

        // Bind parameters
        params.forEach(q::setParameter);

        // Map Object[] -> DTO
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();

        List<UserDTO> result = new ArrayList<>(rows.size());
        for (Object[] r : rows) {
            result.add(new UserDTO(
                    ((Number) r[0]).longValue(),
                    (String) r[1],
                    (String) r[2]
            ));
        }
        return result;
    }
}
