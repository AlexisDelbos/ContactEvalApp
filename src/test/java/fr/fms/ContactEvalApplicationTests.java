package fr.fms;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ContactRepository;
import fr.fms.entities.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ContactEvalApplicationTests {

    @Autowired
    IBusinessImpl business;

    @Autowired
    ContactRepository contactRepo;

    @Test
    void contextLoads() {
        assertNotEquals(1, 2);
    }

    @Test
    void testAddContact() {
        business.createOneContact(new Contact((long) 1, "John", "Smith", "john.smirth@gmail.com", "0102030405", "9 rue de l'espace", null));

        Contact contactToFind = contactRepo.findByEmailContains("john.smirth@gmail.com").get(0);

        assertEquals("john.smirth@gmail.com", contactToFind.getEmail());
    }


}
