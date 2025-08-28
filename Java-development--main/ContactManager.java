import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
    // Contact class to store contact information
    static class Contact {
        String name;
        String phoneNumber;
        String email;

        public Contact(String name, String phoneNumber, String email) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
        }
    }

    // List to store contacts
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Simple Contact Manager!");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice (1-5): ");
            
            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using Simple Contact Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add a new contact");
        System.out.println("2. View all contacts");
        System.out.println("3. Update a contact");
        System.out.println("4. Delete a contact");
        System.out.println("5. Exit");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        scanner.nextLine(); // Clear the buffer
        return scanner.nextLine();
    }

    private static void addContact() {
        System.out.println("\nAdd New Contact");
        String name = getStringInput("Enter name: ");
        String phone = getStringInput("Enter phone number: ");
        String email = getStringInput("Enter email address: ");
        
        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("\nNo contacts found.");
            return;
        }
        
        System.out.println("\nAll Contacts:");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }

    private static void updateContact() {
        if (contacts.isEmpty()) {
            System.out.println("\nNo contacts to update.");
            return;
        }
        
        viewContacts();
        int contactIndex = getIntInput("Enter the number of contact to update: ") - 1;
        
        if (contactIndex < 0 || contactIndex >= contacts.size()) {
            System.out.println("Invalid contact number.");
            return;
        }
        
        Contact contact = contacts.get(contactIndex);
        System.out.println("\nUpdating contact: " + contact);
        
        String name = getStringInput("Enter new name (leave blank to keep current): ");
        String phone = getStringInput("Enter new phone number (leave blank to keep current): ");
        String email = getStringInput("Enter new email (leave blank to keep current): ");
        
        if (!name.isEmpty()) {
            contact.name = name;
        }
        if (!phone.isEmpty()) {
            contact.phoneNumber = phone;
        }
        if (!email.isEmpty()) {
            contact.email = email;
        }
        
        System.out.println("Contact updated successfully!");
    }

    private static void deleteContact() {
        if (contacts.isEmpty()) {
            System.out.println("\nNo contacts to delete.");
            return;
        }
        
        viewContacts();
        int contactIndex = getIntInput("Enter the number of contact to delete: ") - 1;
        
        if (contactIndex < 0 || contactIndex >= contacts.size()) {
            System.out.println("Invalid contact number.");
            return;
        }
        
        Contact removedContact = contacts.remove(contactIndex);
        System.out.println("Deleted contact: " + removedContact);
    }
}