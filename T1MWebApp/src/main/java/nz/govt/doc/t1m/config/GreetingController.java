package nz.govt.doc.t1m.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

    @Value("${version}")
    protected String version;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("version", version);
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("version", version);
        return "login";
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }*/

    @RequestMapping("/secureLogin")
    public String secureLogin(Model model) {
        return "redirect:/#/person/search";
    }
}