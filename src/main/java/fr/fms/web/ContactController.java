package fr.fms.web;

import fr.fms.business.IBusinessImpl;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    IBusinessImpl businessImpl;

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


    @PostMapping("/save")
    public String save(@Valid Contact contact, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("categories", businessImpl.getTypeContacts());
                return "contact";
            }
            businessImpl.createOneContact(contact);
        } catch (Exception e) {
            redirectAttrs.addAttribute("error", e.getMessage());
            logger.error("[Contact controller CONTROLLER : SAVE Contact] : {} ", e.getMessage());
        }
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

    @GetMapping("/edit")
    public String edit(Long id, Model model) {
        Optional<Contact> contact;
        try {
            contact = businessImpl.getOneContact(id);
            model.addAttribute("categories", businessImpl.getTypeContacts());
            model.addAttribute("article", contact);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error("[Contact CONTROLLER : EDIT] : {} ", e.getMessage());
        }
        return "edit";
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword, Long idTypeContact, RedirectAttributes redirectAttrs) {
        try {
            businessImpl.deleteContact(id);
        } catch (Exception e) {
            redirectAttrs.addAttribute("error", e.getMessage());
            logger.error("[Contact Controller : DELETE] : {} ", e.getMessage());
        }
        return "redirect:/index?page=" + page + "&keyword=" + keyword + "&idTypeContact=" + idTypeContact;
    }


}
