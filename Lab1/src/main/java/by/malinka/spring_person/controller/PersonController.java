package by.malinka.spring_person.controller;

import by.malinka.spring_person.forms.PersonForm;
import by.malinka.spring_person.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class PersonController {
    private static final List<Person> persons = new ArrayList<>();
    static int iterator = 0;

    static {
        persons.add(new Person(iterator, "Max", "Malinousky"));
        persons.add(new Person(++iterator, "Liza", "Zinovich"));
    }
    //private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PersonController.class);

    @Value("${welcome.message}")
    private String message;
    @Value("${error.message.AllFieldsRequired}")
    private String errorAllFieldRequired;

    @Value("${error.message.CantBeEmpty}")
    private String errorCantBeEmpty;

    @GetMapping
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        model.addAttribute("message", message);
        log.info("going to main page");
        return modelAndView;
    }

    @GetMapping("/persons")
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("persons");
        model.addAttribute("persons", persons);
        log.info("going to persons page");
        return modelAndView;
    }

    @GetMapping("/add_person")
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("add_person");
        PersonForm personForm = new PersonForm();
        model.addAttribute("person_form", personForm);
        log.info("going to add_person page");
        return modelAndView;
    }

    @PostMapping("/add_person")
    public ModelAndView saveAddedPerson(Model model, @ModelAttribute("person_form") PersonForm personForm) {
        ModelAndView modelAndView = new ModelAndView();
        String name = personForm.getName();
        String surname = personForm.getSurname();
        if (name != null && name.length() > 0 && surname != null && surname.length() > 0) {
            modelAndView.setViewName("persons");
            Person newPerson = new Person(++iterator, name, surname);
            persons.add(newPerson);
            model.addAttribute("persons", persons);
            log.info("new person added: " + newPerson);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorAllFieldRequired);
        modelAndView.setViewName("add_person");
        return modelAndView;
    }

    @GetMapping("/delete_person")
    @ResponseBody
    public ModelAndView deletePerson(Model model, @RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("persons");
        persons.removeIf(x -> x.getId() == id);
        model.addAttribute("persons", persons);
        log.info("person with id = " + id + " has been deleted");
        return modelAndView;
    }

    @GetMapping("/edit_person")
    public ModelAndView showEditPersonPage(Model model, @RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("edit_person");
        Optional<Person> optionalPerson = persons.stream().filter(a -> a.getId() == id).findFirst();
        if (optionalPerson.isPresent()) {
            Person personToEdit = optionalPerson.get();
            model.addAttribute("id", personToEdit.getId());
            PersonForm personForm = new PersonForm(personToEdit.getName(), personToEdit.getSurname());
            model.addAttribute("person_form", personForm);
            log.info("going to edit page");
        }
        return modelAndView;
    }

    @PostMapping("/edit_person")
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
            log.info("person with id = " + id + " has been edited");
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorCantBeEmpty);
        modelAndView.setViewName("edit_person");
        return modelAndView;
    }
}
