package org.example;

import java.util.Scanner;
// list books into array
// use while to create screens menu and make methods for each menu
// make
public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Book[] inventory = new Book[20];

        inventory[0] = new Book("Alice Johnson", true, "The Lost Kingdom", "978-0-123456-00-1", 1);
        inventory[1] = new Book(null, false, "Ocean Depths", "978-0-123456-00-2", 2);
        inventory[2] = new Book("Brian Lee", true, "Shadow and Flame", "978-0-123456-00-3", 3);
        inventory[3] = new Book(null, false, "The Clockmaker's War", "978-0-123456-00-4", 4);
        inventory[4] = new Book("Carla Mendez", true, "Beneath Crimson Skies", "978-0-123456-00-5", 5);
        inventory[5] = new Book(null, false, "Echoes of the Void", "978-0-123456-00-6", 6);
        inventory[6] = new Book("Daniel Ng", true, "Mechanical Dreams", "978-0-123456-00-7", 7);
        inventory[7] = new Book(null, false, "The Forgotten Archive", "978-0-123456-00-8", 8);
        inventory[8] = new Book("Eva Winters", true, "Riftwalker", "978-0-123456-00-9", 9);
        inventory[9] = new Book(null, false, "Twilight Keepers", "978-0-123456-01-0", 10);
        inventory[10] = new Book("Frank Delgado", true, "The Ember Blade", "978-0-123456-01-1", 11);
        inventory[11] = new Book(null, false, "Steel Horizon", "978-0-123456-01-2", 12);
        inventory[12] = new Book("Grace Yu", true, "Whispers in the Fog", "978-0-123456-01-3", 13);
        inventory[13] = new Book(null, false, "Crimson Tide Rising", "978-0-123456-01-4", 14);
        inventory[14] = new Book("Hassan Malik", true, "Legacy of Ash", "978-0-123456-01-5", 15);
        inventory[15] = new Book(null, false, "The Infinity Gate", "978-0-123456-01-6", 16);
        inventory[16] = new Book("Isabelle Tran", true, "Stormborn", "978-0-123456-01-7", 17);
        inventory[17] = new Book(null, false, "Beyond the Veil", "978-0-123456-01-8", 18);
        inventory[18] = new Book("Jonas Park", true, "Chronicles of Theros", "978-0-123456-01-9", 19);
        inventory[19] = new Book(null, false, "The Glass Empire", "978-0-123456-02-0", 20);

        while (true) {
            takeToHomeScreen(inventory);
        }
    }

    public static void takeToHomeScreen(Book[] inventory) {
        System.out.println("Welcome to the Neighbourhood Library Home Page!");
        System.out.println("1) Show Available Books");
        System.out.println("2) Show Checked Out Books");
        System.out.println("3) Check in a Book");
        System.out.println("4) Search for a Book by ID");
        System.out.println("5) Exit");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showBooksAvailable(inventory);
                break;
            case 2:
                showCheckedOutBooks(inventory);
                break;
            case 3:
                checkInBook(inventory);
                break;
            case 4:
                searchForBook(inventory);
                break;
            case 5:
                System.out.println("Have a nice day!");
                System.exit(0);
            default:
                System.out.println("Invalid choice, try again.");
        }
    }

    public static void showBooksAvailable(Book[] inventory) {
        while (true) {
            boolean anyAvailable = false;

            System.out.println(" Available Books");
            for (Book book : inventory) {
                if (!book.getIsCheckedOut()) {
                    System.out.println("Title: " + book.getTitle() + ", ID: " + book.getId() + ", ISBN: " + book.getIsbn());
                    anyAvailable = true;
                }
            }

            if (!anyAvailable) {
                System.out.println("No books available at the moment.");
            }

            System.out.println("Options:");
            System.out.println("1) Check out a book");
            System.out.println("2) Return to Home Screen");
            int option = scanner.nextInt();

            if (option == 1 && anyAvailable) {
                System.out.print("Enter your name: ");
                String name = scanner.next();
                System.out.print("Enter the ID of the book you'd like to check out: ");
                int bookId = scanner.nextInt();
                boolean found = false;

                for (Book book : inventory) {
                    if (book.getId() == bookId && !book.getIsCheckedOut()) {
                        book.setCheckedOutTo(name);
                        book.setIsCheckedOut(true);
                        System.out.println("Book checked out successfully to " + name + "!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Book not found or already checked out.");
                }
            } else if (option == 2) {
                return;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void showCheckedOutBooks(Book[] inventory) {
        while (true) {
            boolean anyCheckedOut = false;

            System.out.println(" Checked Out Books ");
            for (Book book : inventory) {
                if (book.getIsCheckedOut()) {
                    System.out.println("Title: " + book.getTitle() + ", ID: " + book.getId());
                    anyCheckedOut = true;
                }
            }

            if (!anyCheckedOut) {
                System.out.println("No books are currently checked out.");
            }

            System.out.println("Options:");
            System.out.println("1) Return a Book");
            System.out.println("2) Return to Home Screen");

            int option = scanner.nextInt();

            if (option == 1 && anyCheckedOut) {
                checkInBook(inventory);
            } else if (option == 2) {
                return;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }


    public static void checkInBook(Book[] inventory) {
        while (true) {
            System.out.print("Enter the ID of the book to check in (or 0 to return): ");
            int bookId = scanner.nextInt();
            if (bookId == 0) return;

            boolean found = false;

            for (Book book : inventory) {
                if (book.getId() == bookId) {
                    if (book.getIsCheckedOut()) {
                        book.setCheckedOutTo(null);
                        book.setIsCheckedOut(false);
                        System.out.println("Book successfully checked in.");
                    } else {
                        System.out.println("This book is already checked in.");
                    }
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Book not found in the inventory.");
            }
        }


    }

    public static void searchForBook(Book[] inventory) {
        while (true) {
            System.out.print("Enter the ID of the book to search for (or 0 to return to Home): ");
            int id = scanner.nextInt();
            if (id == 0) return;

            boolean found = false;
            for (Book book : inventory) {
                if (book.getId() == id) {
                    displayBook(book);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Book not found.");
            }
        }
    }


    public static void displayBook(Book book) {
        System.out.println("  Book Info  ");
        System.out.println("Title: " + book.getTitle());
        System.out.println("ID   : " + book.getId());
        System.out.println("ISBN : " + book.getIsbn());

        if (book.getIsCheckedOut()) {
            System.out.println("Status: is Checked out");
        } else {
            System.out.println("Status: Available");
        }

    }
}



