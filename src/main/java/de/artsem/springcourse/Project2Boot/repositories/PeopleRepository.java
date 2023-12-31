package de.artsem.springcourse.Project2Boot.repositories;


import de.artsem.springcourse.Project2Boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    List<Person> findByName(String name);

    List<Person> findByNameOrderByAge(String name);

    Optional<Person> findByEmail(String email);

    List<Person> findByNameStartingWith(String startingWith);

    List<Person> findByNameOrEmail(String name, String email);

//    Page<Person> findAll(Pageable var1);

//    List<Person> findAll(Sort var1);


//    @Query("select p.name, p.age from Person p join" +
//            " Book b on b.person_id=p.id where b.id=?1")
//    Optional<Person> findByBookListAndBookIdd(int id);
//

    @Query(value = "select p.* from Person p join " +
            "Book b on b.person_id = p.id where b.id=?1", nativeQuery = true)
    Optional<Person> findBookOwner(int id);





}
