package com.projects.transactions.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.transactions.persistence.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}