package diop.licien.cvbackend.auth;

import diop.licien.cvbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @Entity
public class RefreshToken {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne @JoinColumn(name = "id_user")
    private User user;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private Instant experyDate;
    private boolean revoked;
}
