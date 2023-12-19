package diop.licien.cvbackend.photo;

import diop.licien.cvbackend.cv.ICvService;
import diop.licien.cvbackend.person.Person;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8888")
public class FileController {
    private final FileStorageService fileStorageService;
    private final ICvService cvService;

    public FileController(FileStorageService fileStorageService, ICvService cvService) {
        this.fileStorageService = fileStorageService;
        this.cvService = cvService;
    }

    @PostMapping("/{userId}/upload")
    public ResponseEntity<ResponseMessage> telechargerFichier(@RequestParam("file") MultipartFile fichier, @PathVariable Long userId) throws FileUploadException {
        Person person = cvService.getPersonById(userId);

        if (person != null) {
            try {
                fileStorageService.stockerFichier(fichier, person);
                return ResponseEntity.ok(new ResponseMessage("Fichier téléchargé avec succès pour l'utilisateur avec l'ID " + userId));
            } catch (IOException e) {
                throw new FileUploadException("Erreur lors du téléchargement du fichier.", e);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Utilisateur avec l'ID " + userId + " non trouvé."));
        }
    }

    @GetMapping("/persons/{userId}/photos")
    public ResponseEntity<List<ResponseFile>> obtenirPhotosUtilisateur(@PathVariable Long userId) {
        Person person = cvService.getPersonById(userId);

        if (person != null) {
            List<ResponseFile> responseFiles = person.getPhotos().stream()
                    .map(photo -> new ResponseFile(photo.getNomFichier(), photo.getEmplacement(), "image/jpeg", new File(photo.getEmplacement()).length()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseFiles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
