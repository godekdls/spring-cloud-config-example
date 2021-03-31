package com.dain.config.client.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Value("${foo}")
    private String foo;

    @Value("${message}")
    private String message;

    @Value("${my.secret}")
    private String secret;

    @Value("${friend}")
    private String friend;

    @Value("${app.friend:${friend}}")
    private String appFriend;

    @Value("${app.enemy}")
    private String appEnemy;

    @GetMapping("/foo")
    public String foo() {
        return foo;
    }

    @GetMapping("/message")
    public String message() {
        return message;
    }

    @GetMapping("/secret")
    public String secret() {
        return secret;
    }

    @GetMapping("/friend")
    public String friend() {
        return friend;
    }

    @GetMapping("/app.friend")
    public String appFriend() {
        return appFriend;
    }

    @GetMapping("/app.enemy")
    public String appEnemy() {
        return appEnemy;
    }
}
