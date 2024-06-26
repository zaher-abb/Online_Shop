package th.project.enterprise.Controller;

import th.project.enterprise.Entity.*;
import th.project.enterprise.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/User")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    AdressService adressService;

    @Autowired
    StepsService stepsService;

    @Autowired
    TeamService teamService;

    @GetMapping("/register")
    public String viewRgisterPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String Register(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        if (userService.isUserPresent(user.getEmail())) {
            model.addAttribute("exist", true);
            return "signup";
        }

        String teamName = user.getTeamName().toLowerCase();

        if (teamService.chechTeamIsAlreadyExisted(teamName)){
            Team t = teamService.getTeamByName(teamName);
            user.setTeamName(teamName);
            user.setTeam(t);

        }
        else {
            teamService.addNewTeam(teamName);
            Team t = teamService.getTeamByName(teamName);
            user.setTeamName(teamName);
            user.setTeam(t);
        }
        userService.creatUser(user);
        model.addAttribute("success", true);
        try {
           emailService.registrationConfirmationEmail(user);
        } catch (MailException ignored) {

        }
        return "login";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/Admin/viewAdminPage";
        }
        return "redirect:/Steps/addSteps";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/Product/Home";
    }

    @GetMapping("/teamsRank")
    public String getTeamRank(Model model){
        model.addAttribute("steps",stepsService.getStepsSumByTeam());
        return "dashboard";
    }

    @GetMapping("/userEmailAlert")
    public void userEmailAlert(){
        LocalDate date = LocalDate.now();
        emailService.emailAlertToSubmitSteps(userService.getAllUsersWhoDoesNotSubmitSteps(date),date);
    }

//    @GetMapping("/userRank")
//    public String getUserRank(Model model, Principal principal){
//        User user1 = userService.findByEmail(principal.getName());
//        model.addAttribute("steps",stepsService.getStepsSumByUserInTeam(user1.getTeam().getId()));
//        return "dashboard_test";
//    }






    @GetMapping("/showUserProfile")
    public String showUserProfile(Principal principal, Model model) {

        User user1 = userService.findByEmail(principal.getName());
        if (user1 == null) {
            return "redirect:/User/logout";
        } else {
            if (user1.getAdress() == null) {
                Adress adress2 = new Adress();
                adress2.setCountry("not yet registered");
                adress2.setCity("not yet registered");
                adress2.setStreet("not yet registered");
                adress2.setHausNumber("not yet registered");
                adress2.setZip(0);
                model.addAttribute("adr", adress2);
                model.addAttribute("user", user1);
            } else {
                Adress adress1 = user1.getAdress();


                model.addAttribute("adr", adress1);
                model.addAttribute("user", user1);
            }
            return "profile";
        }
    }

    @GetMapping("/showUpdateProfileForm")
    public String showUpdateProfileForm(Model model) {
        model.addAttribute("user", new User());
        return "update";
    }


    @PostMapping("/updateUser")
    public String updateUser(@Valid User user, Principal principal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update";
        }
        if (userService.isUserPresent(user.getEmail())) {
            model.addAttribute("exist", true);
            return "update";
        }
        User user1 = userService.findByEmail(principal.getName());
        if (user1 == null) {
            return "redirect:/User/logout";
        } else {
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setPassword(user.getPassword());
            userService.creatUser(user1);
            model.addAttribute("success", true);

            return "redirect:/User/showUserProfile";
        }
    }


    @GetMapping("/showUpdateAdressForm")
    public String showUpdateAdressForm(Model model) {

        model.addAttribute("adr", new Adress());

        return "updateAdresse";
    }

    @PostMapping("/addAdresstoUser")
    public String addAdresstoUser(@Valid Adress adr, Principal principal, BindingResult result) {
        if (result.hasErrors()) {
            return "updateAdresse";
        }

        User user1 = userService.findByEmail(principal.getName());
        if (user1 == null) {
            return "redirect:/User/logout";
        } else {
            if (adressService.chechIfAdressIsAlreadyExisted(adr.getStreet(),
                    adr.getHausNumber(),
                    adr.getCity(),
                    adr.getCountry(),
                    adr.getZip())) {
                Adress adress = adressService.getIdAressThatAlreadyexisted(adr.getStreet(),
                        adr.getHausNumber(),
                        adr.getCity(),
                        adr.getCountry(),
                        adr.getZip());

                userService.updateUserAdreesID(adress, user1.getId());
            } else {
                adressService.addNewAdress(adr);
                userService.updateUserAdreesID(adr, user1.getId());
            }
            return "redirect:/User/showUserProfile";
        }
    }
}
