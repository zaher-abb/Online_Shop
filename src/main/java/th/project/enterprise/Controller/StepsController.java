package th.project.enterprise.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.project.enterprise.Entity.Steps;
import th.project.enterprise.Entity.User;

@Controller
@RequestMapping("/Steps")
public class StepsController {

    @GetMapping("/addSteps")
    public String viewAddStepsPage(Model model) {
        model.addAttribute("stp", new Steps());
        return "addSteps";
    }
}
