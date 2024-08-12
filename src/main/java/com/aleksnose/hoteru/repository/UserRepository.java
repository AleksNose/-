package com.aleksnose.hoteru.repository;

import com.aleksnose.hoteru.models.User;
import com.aleksnose.hoteru.models.WorkerInHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;


public interface UserRepository extends JpaRepository<User, Integer> {
    //User findByNameAndSurname(String name, String surname);

    //@Query("select u from User u where u.Name = ?1 and u.Surname = ?2")
    List<User> findByNameAndSurname(String name, String surname);
}
