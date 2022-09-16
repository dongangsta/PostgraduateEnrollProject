package edu.dsm.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "题目管理")
@Controller
public class TextController {

    @GetMapping("toAddText")
    public String toAddText() {
        return "user_add_text";
    }
}
