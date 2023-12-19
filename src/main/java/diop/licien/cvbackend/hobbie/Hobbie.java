package diop.licien.cvbackend.hobbie;

import com.fasterxml.jackson.annotation.JsonProperty;
import diop.licien.cvbackend.cv.Cv;
import diop.licien.cvbackend.person.Person;
import jakarta.persistence.*;
import lombok.*;

@Data @Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Hobbie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHobbie;
    private String nameHobbie;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    @ManyToOne @JoinColumn(name = "id_cv")
    private Cv cv;
}
