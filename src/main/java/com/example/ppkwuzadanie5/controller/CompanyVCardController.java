package com.example.ppkwuzadanie5.controller;

import com.example.ppkwuzadanie5.PanoramaFirm;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CompanyVCardController {

    @GetMapping("/{query}")
    public String getCompanyList(@PathVariable String query) throws IOException {
        return PanoramaFirm.getResults(query);
    }

    @GetMapping(value = "/vcard/", produces = {"text/vcard"})
    public String getVcard(@RequestParam String name,
                           @RequestParam String telephone,
                           @RequestParam String email,
                           @RequestParam String website,
                           @RequestParam String street,
                           @RequestParam String postalCode,
                           @RequestParam String city) {
        return PanoramaFirm.getVcard(name, telephone, email, website, street, postalCode, city);
    }
}
