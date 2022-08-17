package edu.dsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TextController {

    @GetMapping("toAddText")
    public String toAddText() {
        return "user_add_text";
    }
}
