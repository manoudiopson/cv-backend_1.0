package diop.licien.cvbackend.photo;

import diop.licien.cvbackend.person.Person;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Transactional
    public void stockerFichier(MultipartFile fichier, Person person) throws IOException {
        File repertoireUpload = new File(uploadDir);
        if (!repertoireUpload.exists()) {
            repertoireUpload.mkdirs();
        }

        String nomFichierUnique = UUID.randomUUID().toString() + "_" + fichier.getOriginalFilename();
        Path cheminFichier = Path.of(uploadDir, nomFichierUnique);
        Files.copy(fichier.getInputStream(), cheminFichier, StandardCopyOption.REPLACE_EXISTING);

        Photo photo = new Photo();
        photo.setNomFichier(nomFichierUnique);
        photo.setEmplacement(cheminFichier.toString());
        photo.setPerson(person);
        person.getPhotos().add(photo);
    }
}

