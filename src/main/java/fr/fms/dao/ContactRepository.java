package fr.fms.dao;

import fr.fms.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Long> {
    public Page<Contact> findAll(Pageable pageable);

    public Page<Contact> findByLastNameContains(String lastname , Pageable pageable);
    public Page<Contact> findByTypeContact(Long id , Pageable pageable);

    Long id(Long id);
}