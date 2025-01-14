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

import java.lang.reflect.Type;
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
    public String getArticlesByCategory(@RequestParam Long id, Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size) throws Exception {

        //Page<Contact> testTypeContact = businessImpl.getContactsByTypeContact(id, page);
        Page<Contact> typeContact = businessImpl.findByTypeContact(id, page);
        List<TypeContact> contacts = businessImpl.getTypeContacts();
        model.addAttribute("listContacts", typeContact);
        model.addAttribute("listTypeContacts", contacts);
        model.addAttribute("idTypeContact", id);
        return "contacts";
    }

}
