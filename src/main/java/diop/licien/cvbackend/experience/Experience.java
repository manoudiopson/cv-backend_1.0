package diop.licien.cvbackend.experience;

import com.fasterxml.jackson.annotation.JsonProperty;
import diop.licien.cvbackend.cv.Cv;
import diop.licien.cvbackend.person.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data @Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Experience {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExperince;
    private String title;
    private String company;
    private Date startDate;
    private Date endDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    @ManyToOne @JoinColumn(name = "id_cv")
    private Cv cv;
}
