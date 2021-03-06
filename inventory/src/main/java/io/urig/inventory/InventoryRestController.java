package io.urig.inventory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class InventoryRestController {

	private BookRepository bookRepository;

	@Autowired
	public InventoryRestController(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@GetMapping()
	public List<Book> getBooks() {
		List<Book> books = bookRepository.findAll();

//		String bookIds = books.stream().map(Book::getId).map(id -> id.toString()).collect(Collectors.joining(","));
//		RestTemplate restTemplate = new RestTemplate();
//		BookPrice[] bookPrices = restTemplate.getForObject(
//				"http://localhost:8090/pricing/customer/" + customerId + "?bookIds=" + bookIds, BookPrice[].class);

		return books;
	}
	
	@GetMapping(path="/{bookId}")
	public Book getBookById(@PathVariable Long bookId) {
		return bookRepository.findById(bookId).get();
	}

}
