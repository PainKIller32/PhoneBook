package main.domain;

import main.dao.entity.Contact;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ContactUseCaseTest {

    @Autowired
    private ContactUseCase contactUseCase;

    @Test
    void addContact() {
        String addTestName = "addTestName";
        String addTestPhone = "addTestPhone";
        contactUseCase.addContact(addTestName, addTestPhone);
        Contact contact = contactUseCase.searchContact(addTestName).get(0);

        Assert.assertEquals(addTestName, contact.getName());
        Assert.assertEquals(addTestPhone, contact.getPhone());

        contactUseCase.deleteContact(contact.getId());
    }

    @Test
    void searchContact() {
        String searchTestName = "searchTestName";
        String searchTestPhone = "searchTestPhone";
        contactUseCase.addContact(searchTestName, searchTestPhone);
        Contact contact = contactUseCase.searchContact(searchTestName).get(0);

        Assert.assertEquals(searchTestPhone, contact.getPhone());
        Assert.assertEquals(searchTestName, contactUseCase.searchContact(searchTestPhone).get(0).getName());

        contactUseCase.deleteContact(contact.getId());
    }

    @Test
    void getContactById() {
        String getTestName = "getTestName";
        String getTestPhone = "getTestPhone";
        contactUseCase.addContact(getTestName, getTestPhone);
        Contact testContact = contactUseCase.searchContact(getTestName).get(0);
        Contact findContact = contactUseCase.getContactById(testContact.getId());

        Assert.assertEquals(testContact.getId(), findContact.getId());
        Assert.assertEquals(testContact.getName(), findContact.getName());
        Assert.assertEquals(testContact.getPhone(), findContact.getPhone());

        contactUseCase.deleteContact(testContact.getId());
    }

    @Test
    void editContact() {
        String editTestName = "editTestName";
        String editTestPhone = "editTestPhone";
        String newTestName = "newTestName";
        String newTestPhone = "newTestPhone";
        contactUseCase.addContact(editTestName, editTestPhone);
        Contact contact = contactUseCase.searchContact(editTestName).get(0);

        contactUseCase.editContact(contact.getId(), newTestName, editTestPhone);
        Assert.assertEquals(contact.getId(), contactUseCase.searchContact(newTestName).get(0).getId());
        contactUseCase.editContact(contact.getId(), editTestName, newTestPhone);
        Assert.assertEquals(contact.getId(), contactUseCase.searchContact(newTestPhone).get(0).getId());

        contactUseCase.deleteContact(contact.getId());
    }

    @Test
    void deleteContact() {
        String deleteTestName = "deleteTestName";
        String deleteTestPhone = "deleteTestPhone";
        contactUseCase.addContact(deleteTestName, deleteTestPhone);
        Contact contact = contactUseCase.searchContact(deleteTestName).get(0);
        int id = contact.getId();
        contactUseCase.deleteContact(id);

        Assert.assertNull(contactUseCase.getContactById(id));
    }
}