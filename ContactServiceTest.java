/* 
 * ContactServiceTest Class
 * Chris Marrs
 * 01/24/20204
 * Description: The contact service TEST class
 */

package com.snhu.CS320.Contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact("ID12345678", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertEquals(contact, service.getContact("ID12345678"));
    }

    @Test
    public void testAddContactWithDuplicateId() {
        Contact contact1 = new Contact("ID12345678", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact1);
        Contact contact2 = new Contact("ID12345678", "Jane", "Smith", "0987654321", "456 Elm St");
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
    }

    @Test
    public void testDeleteExistingContact() {
        Contact contact = new Contact("ID12345678", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.deleteContact("ID12345678");
        assertNull(service.getContact("ID12345678"));
    }

    @Test
    public void testDeleteNonExistingContact() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("ID99999999"));
    }

    @Test
    public void testUpdateExistingContact() {
        Contact contact = new Contact("ID12345678", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.updateContact("ID12345678", "Jane", "Smith", "0987654321", "456 Elm St");
        Contact updatedContact = service.getContact("ID12345678");
        assertAll("update",
            () -> assertEquals("Jane", updatedContact.getFirstName()),
            () -> assertEquals("Smith", updatedContact.getLastName()),
            () -> assertEquals("0987654321", updatedContact.getPhone()),
            () -> assertEquals("456 Elm St", updatedContact.getAddress())
        );
    }

    @Test
    public void testUpdateNonExistingContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("ID99999999", "Jane", "Smith", "0987654321", "456 Elm St");
        });
    }

    // Tests for attempting to update with invalid data (null or invalid length)
    @Test
    public void testUpdateContactWithInvalidFirstName() {
        Contact contact = new Contact("ID12345678", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("ID12345678", "Johnathannn", "Smith", "0987654321", "456 Elm St");
        });
    }

    @Test
    public void testUpdateContactWithInvalidLastName() {
        Contact contact = new Contact("ID12345678", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("ID12345678", "John", "BuckyBuckyBucky", "0987654321", "456 Elm St");
        });
    }

    @Test
    public void testUpdateContactWithInvalidPhone() {
        Contact contact = new Contact("ID12345678", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("ID12345678", "John", "Doe", null, "456 Elm St");
            service.updateContact("ID12345678", "John", "Doe", "1", "456 Elm St");
            service.updateContact("ID12345678", "John", "Doe", "09876543211", "456 Elm St");
            service.updateContact("ID12345678", "John", "Doe", "09876A5432", "456 Elm St");
        });
    }

    @Test
    public void testUpdateContactWithInvalidAddress() {
        Contact contact = new Contact("ID12345678", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("ID12345678", "John", "Doe", "1234567890", "456 Elm St 456 Elm St 456 Elm St");
        });
    }

}
