package io.zipcoder.persistenceapp.repository;

import io.zipcoder.persistenceapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    public List<Person> findAll();

    @Query(value = "select FIRST_NAME, count(FIRST_NAME) from person group by FIRST_NAME", nativeQuery = true)
    long countByFirstName();

    @Query(value = "select distinct LAST_NAME from person", nativeQuery = true)
    List<Person> findAllDistinctLastNames();

    @Query(value = "select * from person where LAST_NAME = :lastName", nativeQuery = true)
    List<Person> findAllByLastName(@Param("lastName") String LAST_NAME);

}
