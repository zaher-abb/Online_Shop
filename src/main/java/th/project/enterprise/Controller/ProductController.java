package th.project.enterprise.Controller;

import th.project.enterprise.Entity.Product;
import th.project.enterprise.Entity.User;
import th.project.enterprise.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.project.enterprise.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/Home")
    public String showHomePage(Model model) {

        List<Product> products = productService.getAllProduct();

        model.addAttribute("p1", products);
        return "main";
    }

//    @GetMapping("/search")
//    public String search(@RequestParam(value = "search", required = false) Integer steps , Principal principal) {
//
//        User user1 = userService.findByEmail(principal.getName());
//        if (user1 == null) {
//            return "redirect:/User/logout";
//        } else {
//            userService.updateUserSteps(steps,user1.getId());
//            return "main";
//        }
//
//    }

    @GetMapping("/laptops")
    public String Laptops(Model model) {
        List<Product> products = productService.getAllProductWithCategorie("laptops");
        model.addAttribute("p1", products);
        return "main";
    }

    @GetMapping("/mobile")
    public String sport(Model model) {
        List<Product> products = productService.getAllProductWithCategorie("mobile");
        model.addAttribute("p1", products);
        return "main";
    }


    @GetMapping("view")
    public String viewSingelProduct(@Param("Pid") long Pid, Model model) {
        Product products = productService.getSingelProductById(Pid);
        model.addAttribute("p1", products);

        return "singelView";
    }


    @GetMapping("/orderByPriceAsc")
    public String OrderByPriceAsc(Model model) {
        List<Product> products = productService.sortProductByPriceAsc();
        model.addAttribute("p1", products);
        return "main";
    }

    @GetMapping("/orderByPriceDesc")
    public String OrderByPriceDesc(Model model) {
        List<Product> products = productService.sortProductByPriceDesc();
        model.addAttribute("p1", products);
        return "main";
    }


}

