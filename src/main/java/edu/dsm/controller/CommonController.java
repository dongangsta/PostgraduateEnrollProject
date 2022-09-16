package edu.dsm.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The type Common controller.
 */
@Api(tags = "评论管理")
@Controller
public class CommonController {
    @Value("${author.name}")
    private String authorName;

    /**
     * 返回作者名称
     *
     * @return the string
     */
    @GetMapping("/getAuthorName")
    @ResponseBody
    public String getAuthorName(){
        return authorName;
    }
}
