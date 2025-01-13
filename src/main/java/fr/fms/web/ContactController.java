package fr.fms.web;

import fr.fms.dao.ContactRepository;
import fr.fms.dao.TypeContactRepository;
import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TypeContactRepository typeContactRepository;

    @GetMapping("/403")
    public String error() {
        return "403";
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("listContacts", contacts);
        return "contact";
    }

}
