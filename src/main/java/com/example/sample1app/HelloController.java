package com.example.sample1app;


//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.bind.annotation.RequestParam;

//import java.io.IOException;
//import java.io.Writer;

//import com.samskivert.mustache.Mustache.Lambda;
//import com.samskivert.mustache.Template.Fragment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.sample1app.repositories.PersonRepository;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController {

    @Autowired
    PersonRepository repository;

    @RequestMapping("/")
    public ModelAndView index(
        @ModelAttribute("formModel")Person Person,
        ModelAndView mav) { 
        mav.setViewName("index");
        mav.addObject("title","Hello page");
        mav.addObject("msg", "This is JPA sample data.");
        List<Person> list = repository.findAll();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value = "/", method=RequestMethod.POST)
    @Transactional
    public ModelAndView form(
        @ModelAttribute("formModel") Person Person,
         ModelAndView mav) {
            repository.saveAndFlush(Person);
            return new ModelAndView("redirect:/");
    }
    
}
