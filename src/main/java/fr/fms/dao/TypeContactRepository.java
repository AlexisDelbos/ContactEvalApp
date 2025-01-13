package fr.fms.dao;

import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeContactRepository extends JpaRepository<TypeContact, Long> {
    
}
