package palvelinkurssi.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import palvelinkurssi.Bookstore.Domain.BookRepository;
import palvelinkurssi.Bookstore.Domain.Book;
import palvelinkurssi.Bookstore.Domain.CategoryRepository;

@Controller
public class BooktstoreController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
		// sisäänkirjautuminen
		@RequestMapping(value="/login")
		public String login() {	
			return "loginPage";
		}
	
		// kirjalistaus
		@RequestMapping(value = "/booklist", method = RequestMethod.GET)
		public String getBooks(Model model) {
				List<Book> books =  (List<Book>) bookRepository.findAll(); // haeta tietokannasta kirjat
				model.addAttribute("books", books); // välitetään kirjalista templatelle model-olion avulla
				return "listBooks"; // DispatherServlet saa tämän template-nimen ja kutsuu seuraavaksi html-templatea,
									// ...joka prosessoidaan palvelimella
		}
		
		// Rest-service for getting all book (JSON)
		@RequestMapping(value="/books", method = RequestMethod.GET)
		public @ResponseBody List<Book> bookListRest() {
			return (List<Book>) bookRepository.findAll();
		}
		
		// Rest-service for getting Book by Id
		@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
			return bookRepository.findById(id);
		}

		// tyhjän kirjalomakkeen muodostaminen
		@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
		@RequestMapping(value = "/newbook", method = RequestMethod.GET)
		public String getNewBookForm(Model model) {
			model.addAttribute("book", new Book()); // "tyhjä" kirja-olio
			model.addAttribute("category", categoryRepository.findAll());
			return "addBook"; // palauttaa thymeleaf templaten addBook.html
		}

		// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
		@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String saveBook(@ModelAttribute Book book) {
			// talletetaan yhden kirjan tiedot tietokantaan
			bookRepository.save(book);	// save osaa SQL insert tai update.
			return "redirect:/booklist"; // ohjaa "uuteen" endpointtiin-selaimella
		}
		
		// kirjan muokkaus
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/edit/{id}") // edit endpointin lisäksi url sisältää kyseisen kirjan id:n
		public String editBook(@PathVariable("id") Long bookId, Model model) {
			// tallennetaan kyseisen id:n kirja-olio modelille, jotta editBook saa oikeat tiedot käsiteltäväksi
			model.addAttribute("book", bookRepository.findById(bookId));
			model.addAttribute("category", categoryRepository.findAll());
			return "editBook";
		}

		// kirjan poisto
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long bookId) {
			bookRepository.deleteById(bookId);
			return "redirect:../booklist"; // .. päästään end-pointin polussa ylöspäin
		}
		

}
