package com.example.sample1app;

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

import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

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
        @ModelAttribute("formModel") @Validated Person person,
        BindingResult result,
         ModelAndView mav) {
            ModelAndView res = null;
            System.out.println(result.getFieldErrors());
            if (!result.hasErrors()){
                repository.saveAndFlush(person);
                res = new ModelAndView("redirect:/");
            } else {
                mav.setViewName("index");
                mav.addObject("title", "Hello page");
                mav.addObject("msg", "sorry,error is occurred...");
                Iterable<Person> list = repository.findAll();
                mav.addObject("datalist", list);
                res = mav;
            }
            return res;
    }
 


@RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
public ModelAndView edit(@ModelAttribute Person Person,
        @PathVariable int id,ModelAndView mav) {
            mav.setViewName("edit");
            mav.addObject("title", "edit Person.");
            Optional<Person> data = repository.findById((long)id);
            mav.addObject("formModel", data.get());
    return mav;
}

@RequestMapping(value = "/edit", method=RequestMethod.POST)
@Transactional
public  ModelAndView updata(@ModelAttribute Person Person,
    ModelAndView mav) {
        repository.saveAndFlush(Person);
        return new ModelAndView("redirect:/");
}


@RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
public ModelAndView delete(@PathVariable int id,
    ModelAndView mav) {
        mav.setViewName("delete");
        mav.addObject("title","Delete Person.");
        mav.addObject("msg","Can I delete this recode?");
        Optional<Person> data = repository.findById((long)id);
        mav.addObject("formModel",data.get());
        return mav;
}

@RequestMapping(value = "/delete", method = RequestMethod.POST)
@Transactional
public ModelAndView remove(@RequestParam long id,
    ModelAndView mav){
        repository.deleteById(id);
        return new ModelAndView("redirect:/");
    }

}