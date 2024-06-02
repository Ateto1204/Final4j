package poateto.final4j.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/chatBot")
    public String chatBot() {
        return "chatBot";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
