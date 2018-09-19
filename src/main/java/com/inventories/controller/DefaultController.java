package com.inventories.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DefaultController {

    @Value("${slide.url}")
    String slideURL;

    @GetMapping(value = "/slide")
    public void getSlide(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", slideURL);
        httpServletResponse.setStatus(302);
    }
}
