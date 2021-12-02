package com.example.ppkwuzadanie5.controller;

import com.example.ppkwuzadanie5.PanoramaFirm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CompanyVCardController {

    @GetMapping("/{query}")
    public String getCompanyList(@PathVariable String query) throws IOException {
        return PanoramaFirm.getResults(query);
    }
}
