package fr.fms.business;

import fr.fms.dao.ContactRepository;
import fr.fms.dao.TypeContactRepository;
import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBusinessImpl implements IBusiness {
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    TypeContactRepository typeContactRepository;

    public IBusinessImpl() {
    }

    @Override
    public List<Contact> getContacts() throws Exception {
        return contactRepository.findAll();
    }

    @Override
    public Page<Contact> getContacts(String kw, int page) throws Exception {
        return contactRepository.findByLastNameContains(kw, PageRequest.of(page, 5));
    }

    @Override
    public Page<Contact> findByTypeContact(Long typeContactId, int page) {
        return contactRepository.findByTypeContact_Id(typeContactId, PageRequest.of(page, 5));
    }

    public List<Contact> findByTypeContact(TypeContact typeContact) {
        return contactRepository.findByTypeContact(typeContact);
    }

    @Override
    public Page<Contact> getContactsByTypeContact(Long id, int page, int size) {
        return contactRepository.findByTypeContact_Id(id, PageRequest.of(page, 5));
    }

    public List<TypeContact> getTypeContacts() {
        return typeContactRepository.findAll();
    }

    @Override
    public Optional<Contact> getOneContact(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public void createOneContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

}
