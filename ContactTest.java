/* 
 * ContactTest Class
 * Chris Marrs
 * 01/24/20204
 * Description: The contact TEST class
 */

package com.snhu.CS320.Contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void testContactConstructorWithValidData() {
        Contact contact = new Contact("IDABC123", "John", "Doe", "1234567890", "123 Main St");
        assertAll("Valid Contact",
            () -> assertEquals("IDABC123", contact.getContactID()),
            () -> assertEquals("John", contact.getFirstName()),
            () -> assertEquals("Doe", contact.getLastName()),
            () -> assertEquals("1234567890", contact.getPhone()),
            () -> assertEquals("123 Main St", contact.getAddress())
        );
    }

    @Test
    public void testContactConstructorWithInvalidID() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("IDABC1238901", "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testContactConstructorWithInvalidFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("IDABC123", null, "Doe", "1234567890", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("IDABC123", "Johnathannn", "Doe", "1234567890", "123 Main St"); // more than 10 chars
        });
    }

    // Similar tests for other invalid inputs for lastName, phone, and address

    @Test
    public void testSetFirstNameValid() {
        Contact contact = new Contact("IDABC123", "John", "Doe", "1234567890", "123 Main St");
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    public void testSetFirstNameInvalid() {
        Contact contact = new Contact("IDABC123", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("Johnathannn")); // more than 10 chars
    }

    @Test
    public void testSetLastNameValid() {
        Contact contact = new Contact("IDABC123", "John", "Doe", "1234567890", "123 Main St");
        contact.setLastName("Buck");
        assertEquals("Buck", contact.getLastName());
    }

    @Test
    public void testSetLastNameInvalid() {
        Contact contact = new Contact("IDABC123", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("BuckyBuckyBuck")); // more than 10 chars
    }

    @Test
    public void testSetPhoneValid() {
        Contact contact = new Contact("IDABC123", "John", "Doe", "1234567890", "123 Main St");
        contact.setPhone("0987654321");
        assertEquals("0987654321", contact.getPhone());
    }

    @Test
    public void testSetPhoneInvalid() {
        Contact contact = new Contact("IDABC123", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("1")); // less than 10 chars
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("09876543211")); // more than 10 chars
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123456A789")); // non-digit
    }

    @Test
    public void testSetAddressValid() {
        Contact contact = new Contact("IDABC123", "John", "Doe", "1234567890", "123 Main St");
        contact.setAddress("123321 Main St");
        assertEquals("123321 Main St", contact.getAddress());
    }

    @Test
    public void testSetAddressInvalid() {
        Contact contact = new Contact("IDABC123", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("123 Main St 123 Main St 123 Main St")); // more than 30 chars
    }

}
