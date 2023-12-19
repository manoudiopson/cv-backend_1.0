package diop.licien.cvbackend.web;

import diop.licien.cvbackend.cv.Cv;
import diop.licien.cvbackend.cv.ICvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CvRestController {
    private final ICvService cvService;

    public CvRestController(ICvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping("/addCv")
    public Cv addCv(@RequestBody Cv cv){
        return cvService.addCv(cv);
    }

    @DeleteMapping("/deleteCv/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long cvId){
        cvService.deleteCv(cvId);
        return new ResponseEntity<>("User successfully deleted!",
                HttpStatus.OK);
    }
}
