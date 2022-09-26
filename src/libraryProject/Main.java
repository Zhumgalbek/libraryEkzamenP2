package libraryProject;

import libraryProject.dao.Dao;
import libraryProject.model.Book;
import libraryProject.model.Library;
import libraryProject.model.LibraryMember;
import libraryProject.service.impl.LibraryServiceImpl;

import java.util.Scanner;

public class Main {
    static Scanner scannerN = new Scanner(System.in);
    static Scanner scannerS = new Scanner(System.in);
    static Scanner scannerK = new Scanner(System.in);

    public static void main(String[] args) {
        Dao dao = new Dao(new Library());
        LibraryServiceImpl libraryService = new LibraryServiceImpl(dao);

        String swichcase = " ";

        while (!swichcase.equals("x")) {
            buttons();
            System.out.println("select number : ");
            swichcase = scannerK.nextLine();

            switch (swichcase) {
                case "1" ->
                        libraryService.addLibraryMember(new LibraryMember(scannerS.nextLong(), scannerN.nextLine()));
                case "2" -> libraryService.getLibraryMembers().forEach(System.out::println);
                case "3" -> libraryService.findLibraryMemberById();
                case "4" -> libraryService.deleteLibraryMemberByID();
                case "5" -> {
                    Book book = new Book();
                    System.out.println("Write Book ID : ");
                    book.setBookId(scannerS.nextLong());
                    System.out.println("Write the title of the book :");
                    book.setTitle(scannerK.nextLine());
                    libraryService.addBookToLibrary(book);
                }
                case "6" -> libraryService.getLibraryBooks().forEach(System.out::println);
                case "7" -> libraryService.findLibraryBookById();
                case "8" -> libraryService.deleteLibraryBookByID();
                case "9" -> libraryService.addBookToMember();
                case "10" -> libraryService.removeBookFromReading();
                default -> System.out.println("select a number from 1 to 10 !!! ");

            }
        }
    }

    public static void buttons() {
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("Нажмите 1, чтобы добавить нового участника в библиотеку.");
        System.out.println("Нажмите 2, чтобы увидеть всех участников библиотеки.");
        System.out.println("Нажмите 3, чтобы найти участника по ID и увидеть данные участника, читаемая книга и прочитанные.");
        System.out.println("Нажмите 4, чтобы удалить участника по ID.");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("Нажмите 5, чтобы добавить книгу в библиотеку.");
        System.out.println("Нажмите 6, чтобы увидеть все книги в библиотеке.");
        System.out.println("Нажмите 7, чтобы найти книгу по ID.");
        System.out.println("Нажмите 8, чтобы удалить книгу по ID.");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("Нажмите 9, чтобы ввести memberId участника и bookId книги, добавить в читаемые");
        System.out.println("Нажмите 10, чтобы ввести memberId участника и bookId книги, добавить в прочитанные");
        System.out.println("Нажмите x, чтобы завершить программу.");
    }
}
