package diop.licien.cvbackend.personalQuality;

import com.fasterxml.jackson.annotation.JsonProperty;
import diop.licien.cvbackend.cv.Cv;
import diop.licien.cvbackend.person.Person;
import jakarta.persistence.*;
import lombok.*;

@Data @Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class PersonalQuality {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonalQuality;
    private String namePersonalQuality;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    @ManyToOne @JoinColumn(name = "id_cv")
    private Cv cv;
}
