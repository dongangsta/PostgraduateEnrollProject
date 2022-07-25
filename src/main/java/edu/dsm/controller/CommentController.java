package edu.dsm.controller;

import edu.dsm.service.CommentService;
import edu.dsm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

}
