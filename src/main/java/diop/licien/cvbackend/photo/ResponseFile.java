package diop.licien.cvbackend.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ResponseFile {
    private String nom;
    private String url;
    private String type;
    private long taille;


}
