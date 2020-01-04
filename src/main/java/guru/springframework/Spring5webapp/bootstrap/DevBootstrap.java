package guru.springframework.Spring5webapp.bootstrap;

import guru.springframework.Spring5webapp.model.Author;
import guru.springframework.Spring5webapp.model.Book;
import guru.springframework.Spring5webapp.model.Publisher;
import guru.springframework.Spring5webapp.repositories.AuthorRepository;
import guru.springframework.Spring5webapp.repositories.BookRepository;
import guru.springframework.Spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher harper = new Publisher("Harper Collins", "New Hampshire");
        publisherRepository.save(harper);
        Book ddd = new Book("Domain Driven Design", "1234", harper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher works = new Publisher("Works", "Rotterdam Avenue");
        publisherRepository.save(works);
        Book noEJB = new Book("J2EE Development without EJB", "23444", works);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
