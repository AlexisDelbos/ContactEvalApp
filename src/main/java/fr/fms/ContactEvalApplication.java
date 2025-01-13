package fr.fms;

import fr.fms.business.IBusinessImpl;
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
    @Autowired
    IBusinessImpl businessImpl;

    @Override
    public void run(String... args) {
		// dataContact();
    }

    private void dataContact(){

        TypeContact personal = typeContactRepository.save(new TypeContact(null,"Personnel",null));
        TypeContact professional = typeContactRepository.save(new TypeContact(null,"Professionnel",null));
        contactRepository.save(new Contact(null,"John", "Smith", "john.smith@gmail.com", "0608070709", "4 rue de l'espace", personal));
        contactRepository.save(new Contact(null,"Tommy", "Merlin", "tommy.merlin@gmail.com", "0708091011", "6 rue de l'espace", professional));
        contactRepository.save(new Contact(null, "Alice", "Dupont", "alice.dupont@gmail.com", "0612345678", "12 rue des Lilas", personal));
        contactRepository.save(new Contact(null, "Marc", "Lemoine", "marc.lemoine@yahoo.fr", "0623456789", "8 avenue de la Liberté", professional));
        contactRepository.save(new Contact(null, "Chloé", "Perrin", "chloe.perrin@hotmail.com", "0634567890", "10 rue du Soleil", personal));
        contactRepository.save(new Contact(null, "Benoît", "Gauthier", "benoit.gauthier@outlook.com", "0645678901", "14 rue des Acacias", professional));
        contactRepository.save(new Contact(null, "Sophie", "Martin", "sophie.martin@live.fr", "0656789012", "16 boulevard Saint-Germain", personal));
        contactRepository.save(new Contact(null, "Vincent", "Dufresne", "vincent.dufresne@gmail.com", "0667890123", "18 rue de la Paix", professional));
        contactRepository.save(new Contact(null, "Lucie", "Lemoine", "lucie.lemoine@orange.fr", "0678901234", "20 rue des Roses", personal));
        contactRepository.save(new Contact(null, "David", "Robert", "david.robert@yahoo.com", "0689012345", "22 place de la Concorde", professional));
        contactRepository.save(new Contact(null, "Emilie", "Benoit", "emilie.benoit@free.fr", "0690123456", "24 rue de la Liberté", personal));
        contactRepository.save(new Contact(null, "Franck", "Deschamps", "franck.deschamps@gmail.com", "0701234567", "26 rue des Champs", professional));
        contactRepository.save(new Contact(null, "Isabelle", "Bernard", "isabelle.bernard@live.fr", "0712345678", "28 avenue des Champs-Élysées", personal));
        contactRepository.save(new Contact(null, "Pierre", "Girard", "pierre.girard@orange.fr", "0723456789", "30 rue de l'Église", professional));
        contactRepository.save(new Contact(null, "Amandine", "Joly", "amandine.joly@hotmail.com", "0734567890", "32 rue du Parc", personal));
        contactRepository.save(new Contact(null, "Julien", "Blanc", "julien.blanc@free.fr", "0745678901", "34 rue des Ormes", professional));
        contactRepository.save(new Contact(null, "Céline", "Fournier", "celine.fournier@outlook.com", "0756789012", "36 boulevard de la République", personal));
        contactRepository.save(new Contact(null, "Antoine", "Leclerc", "antoine.leclerc@gmail.com", "0767890123", "38 rue des Près", professional));
        contactRepository.save(new Contact(null, "Claire", "Vidal", "claire.vidal@orange.fr", "0778901234", "40 rue du Mont", personal));
        contactRepository.save(new Contact(null, "Geoffrey", "Muller", "geoffrey.muller@gmail.com", "0789012345", "42 avenue de la Gare", professional));
        contactRepository.save(new Contact(null, "Monique", "Garnier", "monique.garnier@live.fr", "0790123456", "44 rue des Lilas", personal));
        contactRepository.save(new Contact(null, "Thierry", "Rousseau", "thierry.rousseau@outlook.com", "0801234567", "46 place de l'Opéra", professional));
        contactRepository.save(new Contact(null, "Sébastien", "Lemoine", "sebastien.lemoine@free.fr", "0812345678", "48 rue des Vignes", personal));

    }




}
