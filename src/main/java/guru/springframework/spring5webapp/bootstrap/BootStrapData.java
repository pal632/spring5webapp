package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        final Publisher publisher = new Publisher("STYX", "123 Street");

        publisherRepository.save(publisher);

        System.out.println("Number of publisher: " + publisherRepository.count());

        final Author eric = new Author("Eric", "Evans");
        final Book book = new Book("Domain Drivin Design", "asdsdas");
        book.getAuthors().add(eric);

        final Author rod = new Author("Rod", "Johnson");
        final Book ddd = new Book("J2EE Development without EJB", "sadasd");
        rod.getBooks().add(ddd);
        ddd.getAuthors().add(rod);
        authorRepository.saveAll(Arrays.asList(eric, rod));
        bookRepository.saveAll(Arrays.asList(book, ddd));


        eric.getBooks().add(book);
        publisher.getBooks().add(ddd);
        publisher.getBooks().add(book);
        publisherRepository.save(publisher);


        System.out.println("Number Of Books" + bookRepository.count());
        System.out.println("Publishers book" + publisher.getBooks().size());
    }
}
