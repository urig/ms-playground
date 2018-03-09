package io.urig.checkout;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.urig.checkout.Book;

@FeignClient(name="inventory", url="http://localhost:8080/")
public interface InventoryClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "books/{bookId}")
	public Optional<Book> getBookById(@PathVariable(value="bookId") Long bookId);

}
