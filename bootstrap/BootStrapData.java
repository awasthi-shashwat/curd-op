package com.example.first.bootstrap;

import com.example.first.domain.Author;
import com.example.first.domain.Book;
import com.example.first.domain.Publisher;
import com.example.first.repositories.AuthorRepository;
import com.example.first.repositories.BookRepository;
import com.example.first.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());
        Author eric = new Author("Eric", "Evan");
        Book b1 = new Book("Domain Driven Design", "121212");
        eric.getBooks().add(b1);
        b1.getAuthors().add(eric);

        b1.setPublisher(publisher);
        publisher.getBooks().add(b1);

        authorRepository.save(eric);
        bookRepository.save(b1);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book b2 = new Book("NoEJBS", "232323");
        rod.getBooks().add(b2);
        b2.getAuthors().add(rod);

        b2.setPublisher(publisher);
        publisher.getBooks().add(b2);

        authorRepository.save(rod);
        bookRepository.save(b2);
        publisherRepository.save(publisher);

        System.out.println("Number of books: "+ bookRepository.count());
        System.out.println("Number of books from that publisher: "+ publisher.getBooks().size());
    }
}
