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

    /**
     * Affiche la page d'erreur 403 (Accès refusé)
     *
     * @return la vue "403"
     */
    @GetMapping("/403")
    public String error() {
        return "403";
    }


    /**
     * Affiche la page d'index avec la liste des contacts
     *
     * @param model         le modèle Spring MVC
     * @param page          le numéro de la page courante (par défaut 0)
     * @param kw            le mot-clé de recherche (par défaut "")
     * @param typeContactId l'ID du type de contact pour le filtrage (optionnel)
     * @return la vue "contacts"
     * @throws Exception si une erreur survient lors de la récupération des données
     */
    @GetMapping({"/index", "/"})
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "keyword", defaultValue = "") String kw,
                        @RequestParam(name = "typeContactId", required = false) Long typeContactId) throws Exception {

        Page<Contact> contacts;
        try {


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
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error("[Contact Controller : /index] : {} ", e.getMessage());
        }
        return "contacts";
    }

    /**
     * Sauvegarde un contact
     *
     * @param contact       le contact à sauvegarder
     * @param bindingResult le résultat de la validation
     * @param model         le modèle Spring MVC
     * @param redirectAttrs les attributs de redirection
     * @return redirige vers la page d'index ou retourne la vue "contact" en cas d'erreur de validation
     */

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


    /**
     * Affiche le formulaire de création d'un nouveau contact
     *
     * @param model le modèle Spring MVC
     * @return la vue "contact"
     */
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

    /**
     * Affiche le formulaire d'édition d'un contact existant
     *
     * @param id    l'identifiant du contact à éditer
     * @param model le modèle Spring MVC
     * @return la vue "edit" ou redirige vers l'index en cas d'erreur
     */

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        try {
            Optional<Contact> contactOpt = businessImpl.getOneContact(id);
            if (contactOpt.isPresent()) {
                model.addAttribute("contact", contactOpt.get());
                model.addAttribute("typeContacts", businessImpl.getTypeContacts());
            } else {
                model.addAttribute("error", "Contact non trouvé");
                return "redirect:/index";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error("[Contact CONTROLLER : EDIT] : {} ", e.getMessage());
            return "redirect:/index";
        }
        return "edit";
    }


    /**
     * Supprime un contact
     *
     * @param id            l'identifiant du contact à supprimer
     * @param page          le numéro de la page courante
     * @param keyword       le mot-clé de recherche
     * @param idTypeContact l'ID du type de contact
     * @param redirectAttrs les attributs de redirection
     * @return redirige vers la page d'index avec les paramètres de pagination
     */

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
