package by.malinka.spring_person.controller;

import by.malinka.spring_person.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    private static List<Person> persons = new ArrayList<Person>();
    static {
        persons.add(new Person("Max", "Malinousky"));
        persons.add(new Person("Liza", "Zinovich"));
    }

    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        model.addAttribute("message", message);
        return modelAndView;
    }

    @RequestMapping(value = {"/persons"}, method = RequestMethod.GET)
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("persons");
        model.addAttribute("persons", persons   );
        return modelAndView;
    }

}
