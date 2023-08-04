package com.hope.hope.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Hello Admin!";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "Hello User!";
    }

    @GetMapping("/special")
    public String specialEndpoint() {
        return "Hello Special User!";
    }
}

