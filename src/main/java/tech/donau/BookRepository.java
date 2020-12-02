package tech.donau;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.jboss.logging.Logger;
import tech.donau.data.data.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Path("/book")
public class BookRepository {

    private static Logger LOGGER = Logger.getLogger(BookRepository.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        return getFailBooks();
    }

    @Retry(maxRetries = 4, delay = 1000L)
    public List<Book> getFailBooks() {
        final boolean fail = new Random().nextBoolean();
        if (fail) {
            LOGGER.info("This exception is for test");
            throw new RuntimeException("Test");
        }
        return Arrays.asList(new Book("1", "Book 1", "Author 1"),
                new Book("2", "Book 2", "Author 2"),
                new Book("3", "Book 3", "Author 3")
        );
    }
}