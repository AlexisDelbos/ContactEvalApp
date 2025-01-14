package fr.fms.web;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ContactRepository;
import fr.fms.dao.TypeContactRepository;
import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ContactController {

    @Autowired
    IBusinessImpl businessImpl;
    private final Logger logger = LoggerFactory.getLogger(ContactController.class);


    @GetMapping("/403")
    public String error() {
        return "403";
    }

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "keyword", defaultValue = "") String kw,
                        @RequestParam(name = "typeContactId", required = false) Long typeContactId) throws Exception {

        Page<Contact> contacts;
        if (typeContactId != null) {
            contacts = businessImpl.findByTypeContact(typeContactId, page);
            model.addAttribute("typeContactId", typeContactId);
        } else {
            contacts = businessImpl.getContacts(kw, page);
        }

        model.addAttribute("listContacts", contacts.getContent());
        model.addAttribute("pages", IntStream.range(0, contacts.getTotalPages()).boxed().collect(Collectors.toList()));
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
        List<TypeContact> typeContacts = businessImpl.getTypeContacts();
        model.addAttribute("listTypeContacts", typeContacts);
        return "contacts";
    }


    @PostMapping({"/save", "/save/{id}"})
    public String save(Model model, @Valid Contact contact, BindingResult bindingResult, @PathVariable(required = false) Long id) throws Exception {
        if (bindingResult.hasErrors()) {
            List<TypeContact> typeContacts = businessImpl.getTypeContacts();
            model.addAttribute("typeContacts", typeContacts);
            return "contact";
        }
        if (id != null) {
            businessImpl.getOneContact(id).ifPresent(contactUpdate -> {
                contactUpdate.setLastName(contact.getLastName());
                contactUpdate.setFirstName(contact.getFirstName());
                contactUpdate.setEmail(contact.getEmail());
                contactUpdate.setPhone(contact.getPhone());
                contactUpdate.setAddress(contact.getAddress());
                contactUpdate.setTypeContact(contact.getTypeContact());
                businessImpl.createOneContact(contactUpdate);
            });
        } else businessImpl.createOneContact(contact);
        return "redirect:/index";
    }



    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", new Contact());
        try {
            model.addAttribute("typeContacts", businessImpl.getTypeContacts());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error("[Contact CONTROLLER : MANAGE NEW ARTICLE] : {} ", e.getMessage());
        }
        return "contact";
    }






}
