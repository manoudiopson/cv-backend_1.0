package diop.licien.cvbackend.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByFirstName(String firstName);
}
