package fr.fms.web;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.TypeContactRepository;
import fr.fms.entities.Contact;
import fr.fms.entities.TypeContact;
import fr.fms.exceptions.ManageErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TypeContactController {

    @Autowired
    IBusinessImpl businessImpl;

    private final Logger logger = LoggerFactory.getLogger(TypeContactController.class);

    @GetMapping("/contactByTypeContact")
    public String contactByTypeContact(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "keyword", defaultValue = "") String kw,
            Model model) throws Exception {

        Page<Contact> contacts = businessImpl.getContactsByTypeContact(id, page);
        model.addAttribute("listContacts", contacts.getContent());
        model.addAttribute("page", IntStream.range(0, contacts.getTotalPages()).boxed().collect(Collectors.toList()));
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);

        List<TypeContact> typeContacts = businessImpl.getTypeContacts();
        model.addAttribute("listTypeContacts", typeContacts);

        return "contacts";
    }

}
