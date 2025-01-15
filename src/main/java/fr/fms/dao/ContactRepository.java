package fr.fms.dao;

import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContactRepository extends JpaRepository<Contact, Long> {
    Page<Contact> findAll(Pageable pageable);

    Page<Contact> findByLastNameContains(String lastname, Pageable pageable);

    Page<Contact> findByTypeContact_Id(Long typeContactId, Pageable pageable);


    List<Contact> findByEmailContains(String email);

    List<Contact> findByTypeContact(TypeContact typeContact);
}