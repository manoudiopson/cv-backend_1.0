package diop.licien.cvbackend.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import diop.licien.cvbackend.cv.Cv;
import diop.licien.cvbackend.enums.Role;
import diop.licien.cvbackend.photo.Photo;
import diop.licien.cvbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DiscriminatorValue("PERSON")
@Entity @Data  @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Person extends User {
    private String firstName;
    private String lastName;
    private String address;
    private String drivingPermit;
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Cv> cvs;
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    public Person(Long idUser, String email, String username, String password,
                  String firstName, String lastName, String address,
                  String drivingPermit, List<Cv> cvs, Role role
                  ){
        super(idUser, email, username, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.drivingPermit = drivingPermit;
        this.cvs = cvs;
    }



    }

