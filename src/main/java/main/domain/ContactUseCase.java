package main.domain;

import main.dao.entity.Contact;
import main.dao.repository.ContactRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ContactUseCase {
    private final ContactRepository contactRepository;

    public ContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        contactRepository.findAll().forEach(contacts::add);
        return contacts;
    }

    public Contact getContactById(int id) {
        Optional<Contact> findContact = contactRepository.findById(id);
        return findContact.orElse(null);
    }

    public void deleteContact(int id) {
        contactRepository.deleteById(id);
    }

    public void editContact(int id, String name, String phone) {
        Optional<Contact> findContact = contactRepository.findById(id);
        if (findContact.isPresent()) {
            Contact contact = findContact.get();
            if (name != null) {
                contact.setName(name);
            }
            if (phone != null) {
                contact.setPhone(phone);
            }
            contactRepository.save(contact);
        }
    }

    public void addContact(String name, String phone) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhone(phone);
        contactRepository.save(contact);
    }

    public List<Contact> searchContact(String query) {
        return contactRepository.findContactsByNameContainsOrPhoneContains(query, query);
    }
}