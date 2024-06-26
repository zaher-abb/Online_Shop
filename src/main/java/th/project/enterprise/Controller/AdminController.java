package th.project.enterprise.Controller;

import th.project.enterprise.Entity.FileUploader;
import th.project.enterprise.Entity.Product;
import th.project.enterprise.Entity.User;
import th.project.enterprise.Service.EmailService;
import th.project.enterprise.Service.ProductService;
import th.project.enterprise.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("Admin")
public class AdminController {


    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @GetMapping("/viewAddProductPage")
    public String viewPageaddProductFromAdmin(Model model) {

        model.addAttribute("p1", new Product());

        return "addProduct";
    }

    @GetMapping("/viewAdminPage")
    public String viewAdminPage(Model model) {


        List<Product> products = productService.getAllProduct();

        model.addAttribute("p1", products);
        return "AdminSeite";
    }


    @GetMapping("/update")
    public String viewUpdateForm(@Param("id") long id, Model model) {
        Product product = productService.getSingelProductById(id);
        model.addAttribute("p1", product);
        return "addProduct";
    }

    @PostMapping("/AddProduct")
    public String uploadFile(@Param("image") MultipartFile image, Product p) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        p.setPictureUrl("/images/" + fileName);
        String uploadDir = "C:\\Users\\zaher\\IntelliJ_EE_Projecte\\enterprise\\src\\main\\resources\\static\\images";

        FileUploader.saveFile(uploadDir, fileName, image);
        productService.addProduct(p);
        return "redirect:/Admin/viewAdminPage";
    }

    @GetMapping("/delete")
    public String deleteProudct(@Param("id") long id) {
        productService.deleteProductById(id);

        return "redirect:/Admin/viewAdminPage";
    }


    @GetMapping("/addUser")
    public String viewAddUSerForm(Model model) {
        model.addAttribute("user", new User());
        return "AdminAddUser";
    }

    @PostMapping("/addUser")
    public String addUserByAdmin(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "AdminAddUser";
        }

        if (userService.isUserPresent(user.getEmail())) {
            model.addAttribute("exist", true);
            return "AdminAddUser";
        }
        userService.creatUser(user);
        model.addAttribute("success", true);
        try {

            //        emailService.registrationConfirmationEmail(user);
        } catch (MailException ignored) {

        }
        return "redirect:/Admin/viewAdminPage";
    }

    @GetMapping("/viewAdminDashboardPage")
    public String viewAdminDashboardPage(Model model) {

        model.addAttribute("steps", new Product());

        return "addProduct";
    }


}
