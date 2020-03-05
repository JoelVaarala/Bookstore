package palvelinkurssi.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import palvelinkurssi.Bookstore.Domain.BookRepository;
import palvelinkurssi.Bookstore.Domain.Book;
import palvelinkurssi.Bookstore.Domain.CategoryRepository;

@Controller
public class BooktstoreController {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
		// kirjalistaus
		@RequestMapping(value = "/books", method = RequestMethod.GET)
		public String getBooks(Model model) {
				List<Book> books =  (List<Book>) bookRepository.findAll(); // haeta tietokannasta autot
				model.addAttribute("books", books); // välitetään kirjalista templatelle model-olion avulla
				return "listBooks"; // DispatherServlet saa tämän template-nimen ja kutsuu seuraavaksi carlist.html-templatea,
									// joka prosessoidaan palvelimella
		}

		// tyhjän kirjalomakkeen muodostaminen
		@RequestMapping(value = "/newbook", method = RequestMethod.GET)
		public String getNewBookForm(Model model) {
			model.addAttribute("book", new Book()); // "tyhjä" kirja-olio
			model.addAttribute("category", categoryRepository.findAll());
			return "addBook"; // palauttaa thymeleaf templaten addBook.html
		}

		// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String saveBook(@ModelAttribute Book book) {
			// talletetaan yhden kirjan tiedot tietokantaan
			bookRepository.save(book);	// save osaa SQL insert tai update.
			return "redirect:/books"; // ohjaa "uuteen" endpointtiin-selaimella
		}
		
		// kirjan muokkaus
		@RequestMapping(value = "/edit/{id}") // edit endpointin lisäksi url sisältää kyseisen kirjan id:n
		public String editBook(@PathVariable("id") Long bookId, Model model) {
			// tallennetaan kyseisen id:n kirja-olio modelille, jotta editBook saa oikeat tiedot käsiteltäväksi
			model.addAttribute("book", bookRepository.findById(bookId));
			model.addAttribute("category", categoryRepository.findAll());
			return "editBook";
		}

		// kirjan poisto
		@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long bookId) {
			bookRepository.deleteById(bookId);
			return "redirect:../books"; // .. päästään end-pointin polussa ylöspäin
		}
		

}
