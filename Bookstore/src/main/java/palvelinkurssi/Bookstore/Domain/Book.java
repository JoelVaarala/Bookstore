package palvelinkurssi.Bookstore.Domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
	public class Book {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long isbn;
	private String title;
	private String author;
	private int year;
	private double price;
	
	public Book() {
		super();
		this.isbn = null;
		this.title = null;
		this.author = null;
		this.year = 0;
		this.price = 0;
	}

	public Book( Long isbn, String title, String author, int year, double price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
	}

	public Book(Long id, Long isbn, String title, String author, int year, double price) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", year=" + year
				+ ", price=" + price + "]";
	}
	
	
	
	

}
