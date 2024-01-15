package kr.co.chunjaeshop.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
@Log4j2
public class ErrorController {

    @GetMapping(value = "/404")
    public String error404() {
        return "error/error404";
    }
}
