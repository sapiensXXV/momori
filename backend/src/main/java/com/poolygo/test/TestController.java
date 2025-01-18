package com.poolygo.test;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class TestController {

    @GetMapping("/admin")
    public String admin(HttpServletRequest request) {
        log.info("request={}", request);
        return "this is only admin api";
    }

    @GetMapping("/user")
    public String user(HttpServletRequest request) {
        log.info("request={}", request);
        return "this is only user api";
    }

    @GetMapping("/everyone")
    public String everyone(HttpServletRequest request) {
        log.info("request={}", request);
        return "this is for everyone";
    }
}
