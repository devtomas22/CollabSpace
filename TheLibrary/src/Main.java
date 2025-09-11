
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Tamas
    public static void addBook(ArrayList<String> titles, ArrayList<String> authors, ArrayList<String> isbn, ArrayList<Boolean> available,
            String title, String author, String isbnNumber) {
        titles.add(title);
        authors.add(author);
        isbn.add(isbnNumber);
        available.add(true);
        System.out.printf("%s av %s med ISBN nummer %s har lagts till%n", title, author, isbnNumber);
    }

    public static void displayAllBooks(ArrayList<String> titles, ArrayList<String> authors, ArrayList<String> isbn) {
        System.out.println("Samtliga böcker: ");
        for (int i = 0; i < titles.size(); i++) {
            System.out.printf("%s av %s med ISBN nummer %s med index %d%n", titles.get(i), authors.get(i), isbn.get(i), i);
        }
    }

    public static int searchBook(ArrayList<String> titles, ArrayList<String> authors, String searchTerm) {
        int index = -1;
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).equals(searchTerm) || authors.get(i).equals(searchTerm)) {
                index = i;
                return index;
            }
        }
        return index;
    }

    // Robine
    public static boolean borrowBook(ArrayList<Boolean> available, ArrayList<String> borrowers,
            ArrayList<String> borrowedBooks, int bookIndex, String borrowerName, ArrayList<String> bookISBN) {

        if (bookIndex < 0 || bookIndex >= available.size()) {
            return false;
        }
        if (available.get(bookIndex)) {
            available.set(bookIndex, false);

            borrowedBooks.add(bookISBN.get(bookIndex));
            borrowers.add(borrowerName);
            return true;
        } else {
            return false; // book not available
        }
    }

    public static boolean returnBook(ArrayList<Boolean> available, ArrayList<String> borrowers,
            ArrayList<String> borrowedBooks, String isbnNumber, ArrayList<String> bookISBN) {

        int bookIndex = bookISBN.indexOf(isbnNumber);
        int borrowedBooksIndex = borrowedBooks.indexOf(isbnNumber);

        if (bookIndex < 0 || bookIndex >= available.size()) {
            return false;
        }

        if (!available.get(bookIndex)) {
            available.set(bookIndex, true);
            // String borrowerName = borrowers.get(borrowedBooksIndex);

            borrowedBooks.remove(borrowedBooksIndex);
            borrowers.remove(borrowedBooksIndex);
            return true;
        }

        return false;

    }

    public static void displayBorrowedBooks(ArrayList<String> borrowers, ArrayList<String> borrowedBooks) {

        for (int i = 0; i < borrowers.size(); i++) {
            String borrower = borrowers.get(i);
            String book = borrowedBooks.get(i);

            System.out.printf("%s har lånat ISBN: %s%n", borrower, book);
        }
    }

    // Sasha
    public static void registerUser(ArrayList<String> userNames, ArrayList<String> phoneNumbers, String name,
            String phoneNumber) {
        userNames.add(name);
        phoneNumbers.add(phoneNumber);

        System.out.println(name + " tillagd som användare, med telefonnummer: " + phoneNumber);
        System.out.printf("Det finns %d registrerade användare i listan, den här användaren har index %d%n", userNames.size(), userNames.indexOf(name));

    }

    public static void displayAllUsers(ArrayList<String> userNames, ArrayList<String> phoneNumbers) {

        for (int i = 0; i < userNames.size(); i++) {
            System.out.printf("Användare %d | Namn: %s | Telefonnummer %s%n", i + 1, userNames.get(i), phoneNumbers.get(i));
        }
    }

    public static int searchUser(ArrayList<String> userNames, String name) {
        int indexOfUser = userNames.indexOf(name);

        if (indexOfUser == -1) {
            System.out.printf("Användare med namnet %s hittades inte.%n", name);
        }

        return indexOfUser;
    }

    // Jonathan
    public static int countAvailableBooks(ArrayList<Boolean> available) {
        int availableBooks = 0;
        for (Boolean book : available) {
            if (book) {
                availableBooks++;
            }
        }
        return availableBooks;
    }

    public static int countBorrowedBooks(ArrayList<Boolean> available) {
        int borrowedBooks = 0;
        for (Boolean book : available) {
            if (!book) {
                borrowedBooks++;
            }
        }
        return borrowedBooks;
    }

    public static void displayLibraryStatistics(ArrayList<String> titles, ArrayList<Boolean> available,
            ArrayList<String> userNames) {
        int numBooks = titles.size();
        int numBorrowedBooks = countBorrowedBooks(available);
        int numAvailableBooks = countAvailableBooks(available);
        int numUsers = userNames.size();

        System.out.printf("Det finns %d böcker i biblioteket.%n", numBooks);
        System.out.printf("Utav dessa är %d tillgängliga och %d utlånade.%n", numAvailableBooks, numBorrowedBooks);
        System.out.printf("D.v.s. att %.1f%% är utlånade.%n", ((double) numBorrowedBooks / numBooks) * 100);
        System.out.printf("Statistik per bok:%n");
        for (int i = 0; i < titles.size(); i++) {
            String status = available.get(i) ? "tillgänglig" : "lånad";
            System.out.printf("%s är %s.%n", titles.get(i), status);
        }
        System.out.printf("Biblioteket har %d aktiva användare.%n", numUsers);
        System.out.printf("Deras namn är:%n");
        for (String name : userNames) {
            System.out.printf("%s%n", name);
        }
    }

    // Tomas
    public static void displayMainMenu() {
        System.out.println("\n=== BIBLIOTEKSSYSTEM ===");
        displayBookMenu();
        displayLoanMenu();

        System.out.println("0. Avsluta");
    }

    public static void displayBookMenu() {
        System.out.println("1. Visa alla böcker");
        System.out.println("2. Lägg till bok");
        System.out.println("3. Låna bok");
        System.out.println("4. Återlämna bok");
        System.out.println("5. Visa statistik");
    }

    public static void displayLoanMenu() {
        System.out.println("6. Registrera användare.");
        System.out.println("7. Visa alla användare.");
        System.out.println("8. Sök användare.");
        System.out.println("9. Visa utlånade böcker");
    }

    public static void main(String[] args) {

        // Böcker (index motsvarar varandra)
        ArrayList<String> bookTitles = new ArrayList<>();
        ArrayList<String> bookAuthors = new ArrayList<>();
        ArrayList<String> bookISBN = new ArrayList<>();
        ArrayList<Boolean> bookAvailable = new ArrayList<>(); // true = tillgänglig
        // Lån (index motsvarar varandra)
        ArrayList<String> borrowerNames = new ArrayList<>();
        ArrayList<String> borrowedBooks = new ArrayList<>(); // ISBN för lånad bok
        // Användare (index motsvarar varandra)
        ArrayList<String> userNames = new ArrayList<>();
        ArrayList<String> phoneNumbers = new ArrayList<>();

        // Fördefinierade böcker
        bookTitles.add("Harry Potter");
        bookTitles.add("Sagan om ringen");
        bookTitles.add("1984");
        bookAuthors.add("J.K. Rowling");
        bookAuthors.add("Tolkien");
        bookAuthors.add("Orwell");
        bookISBN.add("111");
        bookISBN.add("222");
        bookISBN.add("333");
        bookAvailable.add(true);
        bookAvailable.add(true);
        bookAvailable.add(false); // 1984 är utlånad

        // Fördefinierade användare
        userNames.add("Anna");
        userNames.add("Erik");
        phoneNumbers.add("070-1234567");
        phoneNumbers.add("070-7654321");

        // Fördefinierat lån
        borrowerNames.add("Anna");
        borrowedBooks.add("333"); // Anna har lånat 1984
        //

        Scanner scanner = new Scanner(System.in);

        boolean quitMainLoop = false;
        while (!quitMainLoop) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Rensa newline

            switch (choice) {
                case 1:
                    displayAllBooks(bookTitles, bookAuthors, bookISBN);
                    break;
                case 2:
                    System.out.print("Titel: ");
                    String title = scanner.nextLine();
                    System.out.print("Författare: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    addBook(bookTitles, bookAuthors, bookISBN, bookAvailable, title, author, isbn);
                    break;

                case 3:
                    System.out.print("Låntagare: ");
                    String borrowerName = scanner.nextLine();
                    System.out.print("Bokindex: ");
                    int bookIndex = scanner.nextInt();
                    borrowBook(bookAvailable, borrowerNames, borrowedBooks, bookIndex, borrowerName, bookISBN);
                case 4:
                    System.out.print("ISBN: ");
                    String isbnNumber = scanner.nextLine();
                    returnBook(bookAvailable, borrowerNames, borrowedBooks, isbnNumber, bookISBN);
                    break;
                case 5:
                    displayLibraryStatistics(bookTitles, bookAvailable, userNames);
                    break;
                case 6:
                    System.out.print("Namn: ");
                    String name = scanner.nextLine();
                    System.out.print("Nummer: ");
                    String phoneNumber = scanner.nextLine();
                    // registerUser(ArrayList<String> userNames, ArrayList<String> phoneNumbers, String name, String phoneNumber) {
                    registerUser(userNames, phoneNumbers, name, phoneNumber);
                    break;
                case 7:
                    displayAllUsers(userNames, phoneNumbers);
                    break;
                case 8:
                    System.out.print("Namn: ");
                    String searchName = scanner.nextLine();
                    int indexOfUser = searchUser(userNames, searchName);
                    if (indexOfUser != -1) {
                        System.out.printf("Användare med namnet %s is hittades.%n", userNames.get(indexOfUser));
                    }
                    break;
                case 9:
                    displayBorrowedBooks(borrowerNames, borrowedBooks);
                    break;
                case 0:
                    quitMainLoop = true;
                    break;
            }
        }
    }
}
