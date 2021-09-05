package by.malinka.spring_person.controller;

import by.malinka.spring_person.forms.PersonForm;
import by.malinka.spring_person.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {
    private static final List<Person> persons = new ArrayList<>();
    static int iterator = 0;

    static {
        persons.add(new Person(iterator, "Max", "Malinousky"));
        persons.add(new Person(++iterator, "Liza", "Zinovich"));
    }

    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @GetMapping
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
        model.addAttribute("persons", persons);
        return modelAndView;
    }

    @RequestMapping(value = {"/add_person"}, method = RequestMethod.GET)
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("add_person");
        PersonForm personForm = new PersonForm();
        model.addAttribute("person_form", personForm);
        return modelAndView;
    }

    @RequestMapping(value = {"/add_person"}, method = RequestMethod.POST)
    public ModelAndView saveAddedPerson(Model model, @ModelAttribute("person_form") PersonForm personForm) {
        ModelAndView modelAndView = new ModelAndView();
        String name = personForm.getName();
        String surname = personForm.getSurname();
        if (name != null && name.length() > 0 && surname != null && surname.length() > 0) {
            modelAndView.setViewName("persons");
            Person newPerson = new Person(++iterator, name, surname);
            persons.add(newPerson);
            model.addAttribute("persons", persons);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("add_person");
        return modelAndView;
    }

    @RequestMapping(value = {"/delete_person"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deletePerson(Model model, @RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("persons");
        persons.removeIf(x -> x.getId() == id);
        model.addAttribute("persons", persons);
        return modelAndView;
    }

    @RequestMapping(value = {"/edit_person"}, method = RequestMethod.GET)
    public ModelAndView showEditPersonPage(Model model, @RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("edit_person");
        Optional<Person> optionalPerson = persons.stream().filter(a -> a.getId() == id).findFirst();
        if (optionalPerson.isPresent()) {
            Person personToEdit = optionalPerson.get();
            model.addAttribute("id", personToEdit.getId());
            PersonForm personForm = new PersonForm(personToEdit.getName(), personToEdit.getSurname());
            model.addAttribute("person_form", personForm);
        } else {
            //TODO: person do not exists
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/edit_person"}, method = RequestMethod.POST)
    public ModelAndView saveEditedPerson(Model model, @ModelAttribute("person_form") PersonForm personForm, @RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        String newName = personForm.getName();
        String newSurname = personForm.getSurname();
        if (newName != null && newName.length() > 0 && newSurname != null && newSurname.length() > 0) {
            modelAndView.setViewName("persons");
            Optional<Person> optionalPerson = persons.stream().filter(a -> a.getId() == id).findFirst();
            if (optionalPerson.isPresent()) {
                Person personToEdit = optionalPerson.get();
                personToEdit.setName(newName);
                personToEdit.setSurname(newSurname);
            }
            model.addAttribute("persons", persons);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("edit_person");
        return modelAndView;
    }
}
