package com.videorental;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    void regularUnderThreeDays() {
        Customer customer = new Customer("name");
        customer.addRental(new Rental(new Movie("Regular Movie", Movie.REGULAR), 2));
        String result = customer.statement();

        String expected = "Rental Record for name\n" +
                "\t2.0(Regular Movie)\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter pointers";
        assertEquals(expected, result);
    }

    @Test
    void regularOverTwoDays() {
        Customer customer = new Customer("name");
        customer.addRental(new Rental(new Movie("Regular Movie", Movie.REGULAR), 5));
        String result = customer.statement();

        String expected = "Rental Record for name\n" +
                "\t6.5(Regular Movie)\n" +
                "Amount owed is 6.5\n" +
                "You earned 1 frequent renter pointers";
        assertEquals(expected, result);
    }

    @Test
    void newReleaseOneDay() {
        Customer customer = new Customer("name");
        customer.addRental(new Rental(new Movie("New Release Movie", Movie.NEW_RELEASE), 1));
        String result = customer.statement();

        String expected = "Rental Record for name\n" +
                "\t3.0(New Release Movie)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers";
        assertEquals(expected, result);
    }

    @Test
    void newReleaseMultiDays() {
        Customer customer = new Customer("name");
        customer.addRental(new Rental(new Movie("New Release Movie", Movie.NEW_RELEASE), 3));
        String result = customer.statement();

        String expected = "Rental Record for name\n" +
                "\t9.0(New Release Movie)\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter pointers";
        assertEquals(expected, result);
    }

    @Test
    void childrenUnderFourDays() {
        Customer customer = new Customer("name");
        customer.addRental(new Rental(new Movie("Children Movie", Movie.CHILDRENS), 3));
        String result = customer.statement();

        String expected = "Rental Record for name\n" +
                "\t1.5(Children Movie)\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter pointers";
        assertEquals(expected, result);
    }

    @Test
    void childrenOverThreeDays() {
        Customer customer = new Customer("name");
        customer.addRental(new Rental(new Movie("영화명", Movie.CHILDRENS), 4));
        String result = customer.statement();

        String expected = "Rental Record for name\n" +
                "\t3.0(영화명)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers";
        assertEquals(expected, result);
    }

    @Test
    void multipleRentalsMixed() {
        Customer customer = new Customer("name");
        customer.addRental(new Rental(new Movie("Regular Movie", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("New Release Movie", Movie.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie("Children Movie", Movie.CHILDRENS), 5));
        String result = customer.statement();

        String expected = "Rental Record for name\n" +
                "\t3.5(Regular Movie)\n" +
                "\t6.0(New Release Movie)\n" +
                "\t4.5(Children Movie)\n" +
                "Amount owed is 14.0\n" +
                "You earned 4 frequent renter pointers";
        assertEquals(expected, result);
    }

    @Test
    void noRentals() {
        Customer customer = new Customer("name");
        String result = customer.statement();

        String expected = "Rental Record for name\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter pointers";
        assertEquals(expected, result);
    }
}
