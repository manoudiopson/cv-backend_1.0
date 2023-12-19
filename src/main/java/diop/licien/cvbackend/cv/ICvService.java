package diop.licien.cvbackend.cv;

import diop.licien.cvbackend.education.Education;
import diop.licien.cvbackend.experience.Experience;
import diop.licien.cvbackend.hobbie.Hobbie;
import diop.licien.cvbackend.person.Person;
import diop.licien.cvbackend.personalQuality.PersonalQuality;
import diop.licien.cvbackend.skill.Skill;

import java.util.List;

public interface ICvService {
    Person addPerson(Person person);
    Experience addExperience(Experience experience);
    Skill addSkill(Skill skill);
    PersonalQuality addPersonalQuality(PersonalQuality personalQuality);
    Education addEducation(Education education);
    List<Education> getAllEductions();
    Education getEducationById(Long educationId);
    Education updateEducation(Education education);
    void  deleteEducation(Long educationId);
    Hobbie addHobbie(Hobbie hobbie);
    Cv addCv(Cv cv);
    void  deleteCv(Long cvId);
    List<Person> getAllPerson();
    Person getPersonById(Long id);
    List<Cv> getAllCv();
    Cv getCv(Long id);
}
