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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeContactController {

    private final Logger logger = LoggerFactory.getLogger(TypeContactController.class);
    @Autowired
    IBusinessImpl businessImpl;

    /**
     * Récupère et affiche les contacts filtrés par type de contact
     *
     * @param id    l'identifiant du type de contact
     * @param model le modèle Spring MVC
     * @param page  le numéro de la page courante (par défaut 0)
     * @param size  le nombre d'éléments par page (par défaut 5)
     * @return la vue "contacts" avec la liste des contacts filtrée
     * @throws Exception si une erreur survient lors de la récupération des données
     */
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
