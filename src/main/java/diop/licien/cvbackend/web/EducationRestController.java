package diop.licien.cvbackend.web;

import diop.licien.cvbackend.cv.ICvService;
import diop.licien.cvbackend.education.Education;
import diop.licien.cvbackend.exceptions.EducationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EducationRestController {
    private final ICvService cvService;

    public EducationRestController(ICvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/educations")
    public List<Education> educationList() {
        return cvService.getAllEductions();
    }

    @GetMapping("/educations/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable("id") Long educationId) {
        Education education = cvService.getEducationById(educationId);
        if (education == null) {
            throw new EducationNotFoundException(education + " n'existe pas");
        }
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @PutMapping("/educations/update/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable("id") Long educationId,
                                                     @RequestBody Education education) {
        education.setIdEducation(educationId);
        Education updateEdu = cvService.updateEducation(education);
        return new ResponseEntity<>(updateEdu, HttpStatus.OK);
    }

    @PostMapping("/educations/add")
    public ResponseEntity<Education> createEducation(@RequestBody Education education) {
        Education savedEducation = cvService.addEducation(education);
        return new ResponseEntity<>(savedEducation, HttpStatus.CREATED);
    }

    @DeleteMapping("/educations/delete/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable("id") Long idEducation) {
        try {
            cvService.deleteEducation(idEducation);
            return new ResponseEntity<>("Education successfully deleted !", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // ou loggez l'exception
            return new ResponseEntity<>("Failed to delete education.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(EducationNotFoundException.class)
    public ResponseEntity<String> handleEducationNotFoundException(EducationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
