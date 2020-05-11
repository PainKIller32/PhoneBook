package main.dao.repository;

import main.dao.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findContactsByNameContainsOrPhoneContains(String name, String phone);
}