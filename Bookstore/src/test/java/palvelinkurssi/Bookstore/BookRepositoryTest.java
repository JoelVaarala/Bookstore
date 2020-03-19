package palvelinkurssi.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import palvelinkurssi.Bookstore.Domain.Book;
import palvelinkurssi.Bookstore.Domain.BookRepository;
import palvelinkurssi.Bookstore.Domain.Category;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	
	@Test
    public void findByIsbnReturnBook() {
        List<Book> books = repository.findByIsbn((long) 9999); 
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Antoine");
    }
	
	@Test
    public void createNewBook() {
    	Book book = new Book((long) 8888, "Mickey", "Mouse", 2020, 20.0, new Category("Fiction"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
	
	@Test
	public void deleteBook() {
    	repository.deleteAll();
    	assertThat(repository.count()).isEqualTo(0);
	}
}
