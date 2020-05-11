package main.presentation.controller;

import main.dao.entity.Contact;
import main.domain.ContactUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiContactController {
    private final ContactUseCase contactUseCase;

    public ApiContactController(ContactUseCase contactUseCase) {
        this.contactUseCase = contactUseCase;
    }

    @GetMapping("/contact")
    public String getContacts(Model model) {
        model.addAttribute("contacts", contactUseCase.getContacts());
        return "index";
    }

    @PostMapping("/contact")
    public String addContact(@RequestParam("name") String name, @RequestParam("phone") String phone) {
        contactUseCase.addContact(name, phone);
        return "redirect:/";
    }

    @GetMapping("/contact/search")
    public String searchContact(Model model, @RequestParam("query") String query) {
        model.addAttribute("contacts", contactUseCase.searchContact(query));
        return "index";
    }

    @GetMapping("/contact/{id}")
    public String deleteContact(@PathVariable("id") int id) {
        contactUseCase.deleteContact(id);
        return "redirect:/";
    }

    @PostMapping("/contact/{id}")
    public String editContact(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("phone") String phone) {
        contactUseCase.editContact(id, name, phone);
        return "redirect:/";
    }

    @GetMapping("/contact/edit")
    public String showEditContact(Model model, @RequestParam("id") int id) {
        Contact contact = contactUseCase.getContactById(id);
        if (contact == null) {
            return "redirect:/";
        }
        model.addAttribute("contact", contact);
        return "editContact";
    }

    @RequestMapping("/contact/add")
    public String showAddContact() {
        return "addContact";
    }
}