package libraryProject.service.impl;

import libraryProject.dao.Dao;
import libraryProject.model.Book;
import libraryProject.model.LibraryMember;
import libraryProject.service.LibraryService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LibraryServiceImpl implements LibraryService {
    private static final Scanner scannerN = new Scanner(System.in);
    private Dao dao;

    public LibraryServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<LibraryMember> getLibraryMembers() {
        return dao.getLibrary().getLibraryMembers();
    }

    @Override
    public void addLibraryMember(LibraryMember member) {
        getLibraryMembers().add(member);
    }


    public void findLibraryMemberById() {
        System.out.println("enter member ID : ");
        int id = scannerN.nextInt();
        dao.getLibrary().getLibraryMembers().stream().filter(x -> x.getMemberId() == id).findFirst().ifPresentOrElse(
                x->{System.out.println("ID member : "+x.getMemberId());
                    System.out.println("Name member :"+x.getName());
                    System.out.println("currentlt : "+x.getCurrentlyReading());
                    System.out.println("book : "+x.getFinishedBooks());},
                ()-> System.out.println("Мындай адам жок!")
        );
    }

    @Override
    public void deleteLibraryMemberByID() {
        System.out.println("enter id to remove member : ");
        int id = scannerN.nextInt();
        dao.getLibrary().getLibraryMembers().stream().filter(x -> x.getMemberId() == id).findFirst()
                .ifPresentOrElse(x->dao.getLibrary().getLibraryMembers().remove(x), () -> System.err.println("no person with this id") );

    }

    @Override
    public void addBookToLibrary(Book book) {
        dao.getLibrary().getBooks().add(book);
    }

    @Override
    public List<Book> getLibraryBooks() {
        return dao.getLibrary().getBooks();
    }

    @Override
    public void deleteLibraryBookByID() {
        System.out.println("enter id to remove book : ");
        int id = scannerN.nextInt();
        Optional<Book> memberOptional = dao.getLibrary().getBooks().stream().filter(x -> x.getBookId() == id).findFirst();

        if (memberOptional.isPresent()) {
            memberOptional.ifPresent(x -> dao.getLibrary().getBooks().remove(x));
        } else {
            System.err.println("no book with this id");
        }
    }

    @Override
    public void findLibraryBookById() {
        System.out.println("enter book ID : ");
        int id = scannerN.nextInt();
        dao.getLibrary().getBooks().stream().filter(x -> x.getBookId() == id).findFirst().ifPresent(x -> System.out.println("Book ID : " + x.getBookId() +
                "\ntitle : " + x.getTitle() + "\nCurrently Holding : " + x.getCurrentHolder()));
    }

    @Override
    public void addBookToMember() {
        System.out.println("Enter Reader ID : ");
        long idMember = scannerN.nextLong();
        System.out.println("Enter Book ID : ");
        long idBook = scannerN.nextLong();
        Optional<LibraryMember> libraryMemberOptional = dao.getLibrary().getLibraryMembers().stream()
                .filter(x -> x.getMemberId() == idMember && x.getCurrentlyReading() == null).findFirst();

        Optional<Book> bookOptional = dao.getLibrary().getBooks().stream()
                .filter(x-> x.getBookId() == idBook && x.getCurrentHolder() == null).findFirst();

        if (libraryMemberOptional.isPresent() && bookOptional.isPresent()){
            libraryMemberOptional.ifPresent(x -> x.setCurrentlyReading(bookOptional.get()));
            bookOptional.ifPresent(x -> x.setCurrentHolder(libraryMemberOptional.get()));
        }else {
            System.err.println("The reader is already reading the book or the book is busy !!! ");
        }
    }


    @Override
    public void removeBookFromReading() {
        System.out.println("Enter Reader ID : ");
        long number = scannerN.nextLong();
        System.out.println("Enter Book ID :");
        long number1 = scannerN.nextLong();
        Optional<LibraryMember> libraryMemberOptional = dao.getLibrary().
                getLibraryMembers().stream().filter(x -> x.getMemberId() == number && x.getCurrentlyReading() != null).findFirst();

        Optional<Book> bookOptional = dao.getLibrary().getBooks().stream().filter(x -> x.getBookId() == number1 && x.getCurrentHolder() != null).findFirst();
        if (libraryMemberOptional.isPresent() && bookOptional.isPresent()){
            libraryMemberOptional.ifPresent(x->x.setCurrentlyReading(null));
            libraryMemberOptional.ifPresent(x-> x.getFinishedBooks().add(bookOptional.get()));
            bookOptional.ifPresent(x->x.setCurrentHolder(null));
        }else {
            System.err.println("There is no such book or reader !!! ");
        }


    }


}
