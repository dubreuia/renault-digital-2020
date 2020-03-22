package com.renault.controllers;

import com.renault.entities.Language;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin
@RestController
@RequestMapping("/language")
public class LanguageController extends HttpServlet {

    @GetMapping("/")
    public List<String> getLanguages() {
        return Arrays.stream(Language.values()).map(Language::getName).collect(toList());
    }

}
