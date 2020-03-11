package palvelinkurssi.Bookstore.Domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idCategory")
	private Category category;
	
	public Book() {
		super();
		this.isbn = null;
		this.title = null;
		this.author = null;
		this.year = 0;
		this.price = 0;
	}

	public Book( Long isbn, String title, String author, int year, double price, Category category) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
		this.category = category;
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
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null)
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", year=" + year
				+ ", price=" + price + "category ="+ this.getCategory() + "]";
		else
			return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", year=" + year
					+ ", price=" + price + "]";
	}

}
