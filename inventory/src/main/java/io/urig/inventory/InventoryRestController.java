package io.urig.inventory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/books")
public class InventoryRestController {

	private BookRepository bookRepository;

	@Autowired
	public InventoryRestController(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Book> GetBooks() {
		int customerId = 1;

		List<Book> books = bookRepository.findAll();

		String bookIds = books.stream().map(Book::getId).map(id -> id.toString()).collect(Collectors.joining(","));

		RestTemplate restTemplate = new RestTemplate();
		BookPrice[] bookPrices = restTemplate.getForObject(
				"http://localhost:8090/pricing/customer/" + customerId + "?bookIds=" + bookIds, BookPrice[].class);
		
		return books;
	}

}
