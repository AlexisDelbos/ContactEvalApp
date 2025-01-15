package fr.fms;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ContactRepository;
import fr.fms.dao.TypeContactRepository;
import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class IBusinessContactTest {
    public static Contact contact;
    public static TypeContact typeContact;
    @InjectMocks
    IBusinessImpl business;
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private TypeContactRepository typeContactRepository;

    @BeforeAll
    public static void initializedData() {
        typeContact = new TypeContact(2L, "Personnel", null);
        contact = new Contact(1L, "Thomas", "Edison", "thomas.edison@gmail.com", "0102030405", "10 rue de l'espace", typeContact);
    }


    @Test
    void findByTypeContact() {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        when(typeContactRepository.findById(typeContact.getId())).thenReturn(Optional.ofNullable(typeContact));
        when(contactRepository.findByTypeContact(typeContact)).thenReturn(contactList);

        final List<Contact> expectedResults = business.findByTypeContact(typeContact);
        verify(contactRepository).findByTypeContact(typeContact);
        assertNotEquals(0, expectedResults.size());
    }
}
