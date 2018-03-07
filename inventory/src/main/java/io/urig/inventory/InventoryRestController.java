package io.urig.inventory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
		
		String bookIds = books.stream()
				.map(Book::getId)
				.map(id->id.toString())
				.collect(Collectors.joining(","));
		
		RestTemplate restTemplate = new RestTemplate();
        BookPrice[] bookPrices = restTemplate.getForObject("http://localhost:8090/pricing/customer/1?bookIds=" + bookIds, BookPrice[].class);

		
		/*
		ResponseEntity<List<BookPrice>> rateResponse =
				restTemplate.exchange(
						"http://localhost:8090/pricing/customer/1?bookIds="+bookIds, 
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<List<BookPrice>>() {});
		List<BookPrice> bookPrices = rateResponse.getBody();
        */
        //List<BookPrice> bookPrices = restTemplate.getForObject("http://localhost:8090/pricing/customer/1?bookIds=" + bookIds, List<BookPrice>.class);
        //log.info(quote.toString());
		return books;
	}
	
}
