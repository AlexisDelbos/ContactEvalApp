package fr.fms;

import fr.fms.dao.ContactRepository;
import fr.fms.dao.TypeContactRepository;
import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactEvalApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ContactEvalApplication.class, args);
    }

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TypeContactRepository typeContactRepository;

    @Override
    public void run(String... args) {
		dataContact();
    }

    private void dataContact(){

        TypeContact personnal = typeContactRepository.save(new TypeContact(null,"Personnel",null));
        TypeContact professional = typeContactRepository.save(new TypeContact(null,"Professionnel",null));
        contactRepository.save(new Contact(1L,"John", "Smith", "john.smith@gmail.com", "4 rue de l'espace", "0608070709", personnal));
        contactRepository.save(new Contact(2L,"Tommy", "Merlin", "tommy.merlin@gmail.com", "6 rue de l'espace", "0708091011", professional));
    }




}
