package com.videorental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private static final String CUSTOMER_NAME = "name";
    private static final String MOVIE_TITLE = "영화명";

    private static final double REGULAR_BASE_PRICE = 2.0;
    private static final int REGULAR_FREE_DAYS = 2;

    private static final double NEW_RELEASE_DAILY_PRICE = 3.0;

    private static final double CHILDRENS_BASE_PRICE = 1.5;
    private static final int CHILDRENS_FREE_DAYS = 3;

    private static final double EXTRA_DAY_PRICE = 1.5;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(CUSTOMER_NAME);
    }

    @Test
    void regularUnderThreeDays() {
        int daysRented = 2;
        customer.addRental(new Rental(new Movie(MOVIE_TITLE, Movie.REGULAR), daysRented));

        double expectedAmount = REGULAR_BASE_PRICE;

        assertEquals(
                expectedStatement(expectedAmount, 1, line(expectedAmount)),
                customer.statement());
    }

    @Test
    void regularOverTwoDays() {
        int daysRented = 5;
        customer.addRental(new Rental(new Movie(MOVIE_TITLE, Movie.REGULAR), daysRented));

        double expectedAmount = REGULAR_BASE_PRICE + (daysRented - REGULAR_FREE_DAYS) * EXTRA_DAY_PRICE;

        assertEquals(
                expectedStatement(expectedAmount, 1, line(expectedAmount)),
                customer.statement());
    }

    @Test
    void newReleaseOneDay() {
        int daysRented = 1;
        customer.addRental(new Rental(new Movie(MOVIE_TITLE, Movie.NEW_RELEASE), daysRented));

        double expectedAmount = daysRented * NEW_RELEASE_DAILY_PRICE;

        assertEquals(
                expectedStatement(expectedAmount, 1, line(expectedAmount)),
                customer.statement());
    }

    @Test
    void newReleaseMultiDays() {
        int daysRented = 3;
        customer.addRental(new Rental(new Movie(MOVIE_TITLE, Movie.NEW_RELEASE), daysRented));

        double expectedAmount = daysRented * NEW_RELEASE_DAILY_PRICE;

        assertEquals(
                expectedStatement(expectedAmount, 2, line(expectedAmount)),
                customer.statement());
    }

    @Test
    void childrenUnderFourDays() {
        int daysRented = 3;
        customer.addRental(new Rental(new Movie(MOVIE_TITLE, Movie.CHILDRENS), daysRented));

        double expectedAmount = CHILDRENS_BASE_PRICE;

        assertEquals(
                expectedStatement(expectedAmount, 1, line(expectedAmount)),
                customer.statement());
    }

    @Test
    void childrenOverThreeDays() {
        int daysRented = 4;
        customer.addRental(new Rental(new Movie(MOVIE_TITLE, Movie.CHILDRENS), daysRented));

        double expectedAmount = CHILDRENS_BASE_PRICE + (daysRented - CHILDRENS_FREE_DAYS) * EXTRA_DAY_PRICE;

        assertEquals(
                expectedStatement(expectedAmount, 1, line(expectedAmount)),
                customer.statement());
    }

    @Test
    void multipleRentalsMixed() {
        int regularDays = 3;
        int newReleaseDays = 2;
        int childrensDays = 5;

        customer.addRental(new Rental(new Movie(MOVIE_TITLE, Movie.REGULAR), regularDays));
        customer.addRental(new Rental(new Movie(MOVIE_TITLE, Movie.NEW_RELEASE), newReleaseDays));
        customer.addRental(new Rental(new Movie(MOVIE_TITLE, Movie.CHILDRENS), childrensDays));

        double regularAmount = REGULAR_BASE_PRICE + (regularDays - REGULAR_FREE_DAYS) * EXTRA_DAY_PRICE;
        double newReleaseAmount = newReleaseDays * NEW_RELEASE_DAILY_PRICE;
        double childrensAmount = CHILDRENS_BASE_PRICE + (childrensDays - CHILDRENS_FREE_DAYS) * EXTRA_DAY_PRICE;
        double totalAmount = regularAmount + newReleaseAmount + childrensAmount;
        int totalPoints = 1 + 2 + 1;

        assertEquals(
                expectedStatement(totalAmount, totalPoints,
                        line(regularAmount),
                        line(newReleaseAmount),
                        line(childrensAmount)),
                customer.statement());
    }

    @Test
    void noRentals() {
        assertEquals(expectedStatement(0.0, 0), customer.statement());
    }

    private String line(double amount) {
        return "\t" + amount + "(" + MOVIE_TITLE + ")";
    }

    private String expectedStatement(double totalAmount, int totalPoints, String... lines) {
        StringBuilder statement = new StringBuilder("Rental Record for " + CUSTOMER_NAME + "\n");
        for (String line : lines) {
            statement.append(line).append("\n");
        }
        statement.append("Amount owed is ").append(totalAmount).append("\n");
        statement.append("You earned ").append(totalPoints).append(" frequent renter pointers");
        return statement.toString();
    }
}
