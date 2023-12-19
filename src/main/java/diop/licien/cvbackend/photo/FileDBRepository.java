package diop.licien.cvbackend.photo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDBRepository extends JpaRepository<Photo, String> {
}
