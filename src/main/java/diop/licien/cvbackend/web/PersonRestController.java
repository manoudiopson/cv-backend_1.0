package diop.licien.cvbackend.web;

import diop.licien.cvbackend.cv.Cv;
import diop.licien.cvbackend.cv.ICvService;
import diop.licien.cvbackend.person.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonRestController {
    private final ICvService cvService;

    public PersonRestController(ICvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/persons")
    public List<Person> personList() {
        return cvService.getAllPerson();
    }
    @GetMapping("/cvs")
    public List<Cv> cvList(){
        return cvService.getAllCv();
    }
}
