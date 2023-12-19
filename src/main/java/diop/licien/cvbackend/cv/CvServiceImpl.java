package diop.licien.cvbackend.cv;

import diop.licien.cvbackend.education.Education;
import diop.licien.cvbackend.education.EducationRepository;
import diop.licien.cvbackend.experience.Experience;
import diop.licien.cvbackend.experience.ExperienceRepository;
import diop.licien.cvbackend.hobbie.Hobbie;
import diop.licien.cvbackend.hobbie.HobbieRepository;
import diop.licien.cvbackend.person.Person;
import diop.licien.cvbackend.person.PersonRepository;
import diop.licien.cvbackend.personalQuality.PersonalQuality;
import diop.licien.cvbackend.personalQuality.PersonalQualityRepository;
import diop.licien.cvbackend.skill.Skill;
import diop.licien.cvbackend.skill.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Transactional
    public class CvServiceImpl implements ICvService {
    private PersonRepository personRepository;
    private ExperienceRepository experienceRepository;
    private EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final HobbieRepository hobbieRepository;
    private final PersonalQualityRepository personalQualityRepository;
    private final CvRepository cvRepository;

    public CvServiceImpl(PersonRepository personRepository,
                         ExperienceRepository experienceRepository,
                         EducationRepository educationRepository,
                         SkillRepository skillRepository,
                         HobbieRepository hobbieRepository,
                         PersonalQualityRepository personalQualityRepository,
                         CvRepository cvRepository
    ) {
        this.personRepository = personRepository;
        this.experienceRepository = experienceRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.hobbieRepository = hobbieRepository;
        this.personalQualityRepository = personalQualityRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }
    @Override
    public Experience addExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public PersonalQuality addPersonalQuality(PersonalQuality personalQuality) {
        return personalQualityRepository.save(personalQuality);
    }

    @Override
    public Education addEducation(Education education) {
        return educationRepository.save(education);
    }

    @Override
    public List<Education> getAllEductions() {
        return educationRepository.findAll();
    }

    @Override
    public Education getEducationById(Long educationId) {
        Optional<Education> optionalEducation = educationRepository.findById(educationId);
        return optionalEducation.get() ;
    }

    @Override
    public Education updateEducation(Education education) {
        Education existingEducation = educationRepository.findById(education.getIdEducation()).get();
        existingEducation.setDegree(education.getDegree());
        existingEducation.setSchool(education.getSchool());
        existingEducation.setGraduationYear(education.getGraduationYear());
        return existingEducation;
    }

   /* @Override
    public void deleteEducation(Long educationId) {
        cvRepository.deleteById(educationId);
    }*/
   @Override
   public void deleteEducation(Long educationId) {
       Optional<Education> educationOptional = educationRepository.findById(educationId);
       if (educationOptional.isPresent()) {
           educationRepository.deleteById(educationId);
       } else {
           // L'éducation avec l'ID spécifié n'a pas été trouvée
           throw new EntityNotFoundException("Education with ID " + educationId + " not found");
       }
   }

    @Override
    public Hobbie addHobbie(Hobbie hobbie) {
        return hobbieRepository.save(hobbie);
    }

    @Override
    public Cv addCv(Cv cv) {
       // cv.setIdCv(UUID.randomUUID().toString());
        return cvRepository.save(cv);
    }

    @Override
    public void  deleteCv(Long cvId) {
        cvRepository.deleteById(cvId);
    }
    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }
    @Override
    public Person getPersonById(Long id){
        return cvRepository.findById(id).get().getPerson();
    }
    @Override
    public List<Cv> getAllCv() {
        return cvRepository.findAll();
    }

    @Override
    public Cv getCv(Long id) {
        return cvRepository.findById(id).get();
    }
}
