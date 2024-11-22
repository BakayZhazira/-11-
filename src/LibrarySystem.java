import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean status;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return status;
    }

    public void changeStatus(boolean newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return title + " автор: " + author + " (ISBN: " + isbn + ")";
    }
}

class Reader {
    private String name;
    private List<Book> rentedBooks = new ArrayList<>();

    public Reader(String name) {
        this.name = name;
    }

    public void rentBook(Book book) {
        if (book.isAvailable()) {
            rentedBooks.add(book);
            book.changeStatus(false);
            System.out.println(name + " арендовал книгу: " + book);
        } else {
            System.out.println("Книга недоступна: " + book);
        }
    }

    public void returnBook(Book book) {
        if (rentedBooks.remove(book)) {
            book.changeStatus(true);
            System.out.println(name + " вернул книгу: " + book);
        } else {
            System.out.println(name + " не арендовал эту книгу.");
        }
    }
}

// Класс Библиотекарь
class Librarian {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    public void addBook(Book book, Library library) {
        library.addBook(book);
        System.out.println(name + " добавил книгу: " + book);
    }

    public void removeBook(Book book, Library library) {
        if (library.removeBook(book)) {
            System.out.println(name + " удалил книгу: " + book);
        } else {
            System.out.println("Книга не найдена в библиотеке.");
        }
    }
}

// Класс Библиотека
class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public void listBooks() {
        System.out.println("Книги в библиотеке:");
        for (Book book : books) {
            String status = book.isAvailable() ? "Доступна" : "Арендована";
            System.out.println("- " + book + " [" + status + "]");
        }
    }
}

// Тестовая программа
public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("Админстратор");
        Reader reader = new Reader("Дариға");

        Book book1 = new Book("Норвежский лес", "Харуки Мураками", "183 779");
        Book book2 = new Book("Абай жолы", "Мухтар Ауезов", "121 347");

        librarian.addBook(book1, library);
        librarian.addBook(book2, library);

        library.listBooks();

        reader.rentBook(book1);

        library.listBooks();

        reader.returnBook(book1);

        library.listBooks();
    }
}
