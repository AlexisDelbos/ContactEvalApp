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
public class IBusinessImpl implements IBusiness{
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
        return contactRepository.findByLastNameContains(kw , PageRequest.of(page, 5));
    }



    @Override
    public Page<Contact> getContactsByTypeContact(Long id, int page) throws Exception {
        return contactRepository.findByTypeContact(id, PageRequest.of(page, 5));
    }

    public List<TypeContact> getTypeContacts() throws Exception {
        return typeContactRepository.findAll();
    }

    @Override
    public Contact getOneContact(Long id) throws Exception {
        Optional<Contact> optional = contactRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public void createOneContact(Contact contact) {
        contactRepository.save(contact);
    }
}
