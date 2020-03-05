package palvelinkurssi.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import palvelinkurssi.Bookstore.Domain.Book;
import palvelinkurssi.Bookstore.Domain.BookRepository;
import palvelinkurssi.Bookstore.Domain.Category;
import palvelinkurssi.Bookstore.Domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
		@Bean
		public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) { 
			return (args) -> {
				log.info("save a couple of books");
				categoryRepository.save(new Category("Fantasy"));
				
				// Vaihtoehtoinen tyyli (siistimpi)
				// Category category1 = new Category("Sci-fi");
				// categoryRepository.save(category1)
				// ja viittaus sama ennen loppua  bookRepository ... 20,00, category1));
				
				categoryRepository.save(new Category("Biography"));
				categoryRepository.save(new Category("Poetry"));
				
				bookRepository.save(new Book((long) 1234, "title", "Author", 1998, 20.00, categoryRepository.findByName("Fantasy").get(0)));
				bookRepository.save(new Book((long) 4321, "The Rock", "Ramon", 1876, 10.00, categoryRepository.findByName("Biography").get(0)));
				bookRepository.save(new Book((long) 9999, "Little Prince", "Antoine", 2000, 33.00, categoryRepository.findByName("Poetry").get(0)));
				
				log.info("fetch all categories");
				for (Category category : categoryRepository.findAll()) {
					log.info(category.toString());
				}
				
				log.info("fetch all books");
				for (Book book : bookRepository.findAll()) {
					log.info(book.toString());
				}

			};
	}

}
