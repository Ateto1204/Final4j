package poateto.final4j.Presentation.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoView {

    @GetMapping("/chatBot")
    public String chatBot() {
        return "chatBot";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
