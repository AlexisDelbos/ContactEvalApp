package fr.fms.business;

import fr.fms.dao.ContactRepository;
import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBusiness {

    public List<Contact> getContacts() throws Exception;

    public Page<Contact> getContacts(String kw, int page) throws Exception;

    public Page<Contact> getContactsByTypeContact(Long idCat, int page) throws Exception;

    public List<TypeContact> getTypeContacts() throws Exception;

    public Contact getOneContact(Long id) throws Exception;

    void createOneContact(Contact contact);
}
