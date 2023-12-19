package diop.licien.cvbackend.skill;

import com.fasterxml.jackson.annotation.JsonProperty;
import diop.licien.cvbackend.cv.Cv;
import diop.licien.cvbackend.person.Person;
import jakarta.persistence.*;
import lombok.*;

@Data @Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Skill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkill;
    private String nameSkill;
    private String level;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    @ManyToOne @JoinColumn(name = "id_cv")
    private Cv cv;
}
