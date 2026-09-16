package com.videorental;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> iterator = rentals.iterator();
		String result = "Rental Record for " + getName() + "\n";

		while ( iterator.hasNext() ) {
			Rental each = (Rental) iterator.next();
			double thisAmount = each.getCharge();

			frequentRenterPoints += each.getFrequentRenterPoints();
			result += "\t" + String.valueOf(thisAmount) + "(" + each.getMovie().getTitle() + ")" + "\n";

			totalAmount += thisAmount;
		}

		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter pointers";

		return result;
	}
}