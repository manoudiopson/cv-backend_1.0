package diop.licien.cvbackend;

import diop.licien.cvbackend.cv.Cv;
import diop.licien.cvbackend.cv.CvRepository;
import diop.licien.cvbackend.cv.ICvService;
import diop.licien.cvbackend.experience.Experience;
import diop.licien.cvbackend.person.Person;
import diop.licien.cvbackend.person.PersonRepository;
import diop.licien.cvbackend.photo.FileStorageService;
import diop.licien.cvbackend.skill.Skill;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class CvBackendApplication implements CommandLineRunner{
	@Resource
	FileStorageService storageService;

	public static void main(String[] args) {

		SpringApplication.run(CvBackendApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
//    storageService.deleteAll();
		//storageService.init();
	}
	//@Bean
	CommandLineRunner start(ICvService cvService,
							CvRepository cvRepository,
							PersonRepository personRepository){
		return args -> {
			cvService.addExperience(new Experience(
					null,
					"Ingénieur Confirmé",
					"MTS-GROUP",
					Date.valueOf("2021-06-20"),
					Date.valueOf("2021-10-15"),
					null
			));

		/*	educationRepository.save( new Education(
					null,
					"Master 2 génie Logiciel",
					"UPPA",
					Date.valueOf("2023-10-15"),
					null
			));*/

			//personRepository.save(new Person(null, "lucienemmanueld@gmail.com", "manou", "1234", "photo_cv", "Lucien", "Diop", "243 Rue Lafayette", "Permis B",null));
			//experienceRepository.save(new Experience(null, "Ingénieur Stagiaire", "Engie", Date.valueOf("20-03-2023"), Date.valueOf("15-09-2023"),null));
			/*Stream.of("Emile", "Valentin")
					.forEach(firstName -> {
						Person person = new Person();
						person.setEmail(firstName+"@gmail.com");
						person.setUsername(firstName+"emzo");
						person.setPassword(firstName+"5678");
						person.setPhoto(firstName+"photo_cv1");
						person.setFirstName(firstName);
						person.setLastName("Diop");
						person.setAddress("Sénégal");
						person.setDrivingPermit("Permis C");
						person.setCvs(null);
						personRepository.save(person);
					});*/
			/*Stream.of("Jakarta EE", "Angular 16")
					.forEach(nameSkill -> {
						Skill skill = new Skill();
						skill.setNameSkill(nameSkill);
						skill.setLevel("Début");
						skill.setCv(null);
						skillRepository.save(skill);
					});*/
			/*Stream.of("Esprit d'équipe", "Curieux", "Capacité d'adaptation")
					.forEach(quality -> {
						PersonalQuality personalQuality = new PersonalQuality();
						personalQuality.setNamePersonalQuality(quality);
						personalQuality.setCv(null);
						personalQualityRepository.save(personalQuality);
					});
			Stream.of("Théatre", "Sport", "Musique")
					.forEach(hob -> {
						Hobbie hobbie = new Hobbie();
						hobbie.setCv(null);
						hobbie.setNameHobbie(hob);
						hobbieRepository.save(hobbie);
					});*/
			//Person person = personRepository.findById(1L).orElse(null);
			Person person1 = personRepository.findByFirstName("Emile");

			Cv cv = new Cv();
			cv.setTitleCv("cv-boa");
			cv.setPerson(person1);
			cvService.addCv(cv);

			cv =  cvRepository.findAll().get(5);

			Skill skill = new Skill();
			skill.setCv(cv);
			skill.setLevel("Confirmé");
			skill.setNameSkill("React-JS");
			cvService.addSkill(skill);

			/*PersonalQuality personalQuality = new PersonalQuality();
			personalQuality.setCv(cv);
			personalQuality.setNamePersonalQuality("Sérieux");
			personalQualityRepository.save(personalQuality);*/

			/*Hobbie hobbie = new Hobbie();
			hobbie.setCv(cv);
			hobbie.setNameHobbie("Chorale");
			hobbieRepository.save(hobbie);*/

			/*Experience experience = new Experience();
			experience.setCv(cv);
			experience.setTitle("Technicien");
			experience.setCompany("Wango");
			experience.setStartDate(Date.valueOf("2018-02-15"));
			experience.setEndDate(Date.valueOf("2018-06-15"));
			experienceRepository.save(experience);*/

			/*Education education = new Education();
			education.setCv(cv);
			education.setDegree("License");
			education.setSchool("Sup'Technology");
			education.setGraduationYear(Date.valueOf("2021-01-01"));
			educationRepository.save(education);*/
		};
	}
}
