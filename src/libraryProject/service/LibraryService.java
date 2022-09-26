package libraryProject.service;

import libraryProject.model.Book;
import libraryProject.model.LibraryMember;

import java.util.List;

public interface LibraryService {
    List<LibraryMember> getLibraryMembers();
    void addLibraryMember(LibraryMember member);

    void findLibraryMemberById();
    void deleteLibraryMemberByID();

    void addBookToLibrary(Book book);

    List<Book> getLibraryBooks();

    void findLibraryBookById();

    void deleteLibraryBookByID();

    void addBookToMember();

    void removeBookFromReading();
}
