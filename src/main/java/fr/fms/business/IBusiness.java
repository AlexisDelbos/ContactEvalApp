package fr.fms.business;

import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBusiness {

    List<Contact> getContacts() throws Exception;

    Page<Contact> getContacts(String kw, int page) throws Exception;

    Page<Contact> findByTypeContact(Long typeContactId, int page) throws Exception;

    Page<Contact> getContactsByTypeContact(Long typeContactId, int page, int size) throws Exception;

    List<TypeContact> getTypeContacts() throws Exception;

    Optional<Contact> getOneContact(Long id) throws Exception;

    void createOneContact(Contact contact) throws Exception;

    void deleteContact(Long id) throws Exception;
}
