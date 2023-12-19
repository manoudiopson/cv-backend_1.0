package diop.licien.cvbackend.education;

import com.fasterxml.jackson.annotation.JsonProperty;
import diop.licien.cvbackend.cv.Cv;
import diop.licien.cvbackend.person.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Education {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEducation;
    private String degree;
    private String school;
    private Date graduationYear;
    @ManyToOne @JoinColumn(name = "id_cv")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    private Cv cv;
}
