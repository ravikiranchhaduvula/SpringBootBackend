package com.example.repository;

import com.example.DTO.UserDTO;
import com.example.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserNativeRepository {

    @PersistenceContext
    private EntityManager em;

    public List<User> findByEmailEntity(String email) {
        return em.createNamedQuery("User.findByEmailNativeEntity", User.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<UserDTO> findAllAsDTOs() {
        @SuppressWarnings("unchecked")
        List<UserDTO> result = em.createNamedQuery("User.findAllDTOs")
                .getResultList();
        return result;
    }
}

