package diop.licien.cvbackend.cv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import diop.licien.cvbackend.education.Education;
import diop.licien.cvbackend.experience.Experience;
import diop.licien.cvbackend.hobbie.Hobbie;
import diop.licien.cvbackend.person.Person;
import diop.licien.cvbackend.personalQuality.PersonalQuality;
import diop.licien.cvbackend.skill.Skill;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data @Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Cv {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCv;
    private String titleCv;
    @ManyToOne @JoinColumn(name = "id_user")
    @JsonProperty(access = JsonProperty.Access.AUTO )
    private Person person;
    @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY)
    private List<Experience> experiences;
    @OneToMany(mappedBy = "cv", fetch = FetchType.EAGER)
    private List<Education> educations;
    @OneToMany(mappedBy = "cv", fetch = FetchType.EAGER)
    private List<Skill> skills;
    @OneToMany(mappedBy = "cv", fetch = FetchType.EAGER)
    private List<Hobbie> hobbies;
    @OneToMany(mappedBy = "cv", fetch = FetchType.EAGER)
    private List<PersonalQuality> personalQualities;
}
