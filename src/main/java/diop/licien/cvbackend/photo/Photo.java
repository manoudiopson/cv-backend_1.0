package diop.licien.cvbackend.photo;

import com.fasterxml.jackson.annotation.JsonProperty;
import diop.licien.cvbackend.person.Person;
import jakarta.persistence.*;
import lombok.*;


@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Data
public class Photo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nomFichier;
        private String emplacement;
        @ManyToOne
        @JoinColumn(name = "id_user")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private Person person;
}



