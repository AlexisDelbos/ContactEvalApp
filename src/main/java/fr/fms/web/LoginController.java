package fr.fms.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    /**
     * Affiche la page de connexion
     *
     * @return la vue "login"
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Gère la déconnexion de l'utilisateur
     *
     * @param request  la requête HTTP
     * @param response la réponse HTTP
     * @return redirige vers la page de connexion
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    /**
     * Redirige vers la page d'accueil après connexion
     *
     * @return la vue "landingpage"
     */
    @GetMapping("/landingpage")
    public String redirectToLandingPage() {
        return "landingpage";
    }


}
