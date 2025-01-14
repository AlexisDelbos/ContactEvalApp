package fr.fms.business;

import fr.fms.dao.ContactRepository;
import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBusiness {

    public List<Contact> getContacts() throws Exception;

    public Page<Contact> getContacts(String kw, int page) throws Exception;

    Page<Contact> findByTypeContact(Long typeContactId, int page);

    Page<Contact> getContactsByTypeContact(Long typeContactId, int page, int size);

    public List<TypeContact> getTypeContacts() throws Exception;

    public Optional<Contact> getOneContact(Long id);

    void createOneContact(Contact contact);

    }
