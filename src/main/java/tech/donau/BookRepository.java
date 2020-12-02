package tech.donau;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;
import tech.donau.data.data.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/book")
public class BookRepository {

    private static Logger LOGGER = Logger.getLogger(BookRepository.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @CircuitBreaker(failOn = RuntimeException.class, requestVolumeThreshold = 10, delay = 20000L)
    public List<Book> getBooks() {

        if (true) {
            LOGGER.info("This exception is for test");
            throw new RuntimeException("Call fallback method");
        }
        return Arrays.asList(new Book("1", "Book 1", "Author 1"),
                            new Book("2", "Book 2", "Author 2"),
                            new Book("3", "Book 3", "Author 3")
        );
    }
}