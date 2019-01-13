package com.academy.uaa.dao;




import com.academy.uaa.pojo.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerDao extends JpaRepository<Manager,Long> {
    Manager findByName(String name);
}
