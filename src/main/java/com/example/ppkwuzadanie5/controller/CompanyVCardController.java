package com.example.ppkwuzadanie5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyVCardController {

    @GetMapping("/{search}")
    public String getCompanyList(@PathVariable String search) {
        return "<b><h1>" + search + "</h1></b>";
    }
}
