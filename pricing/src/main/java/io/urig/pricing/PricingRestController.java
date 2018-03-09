package io.urig.pricing;

import java.math.BigDecimal;
import java.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pricing/")
public class PricingRestController {

	@GetMapping("/customer/{customerId}")
	public BookPrice[] GetBookPricesForCustomer(@PathVariable Long customerId, @RequestParam Long[] bookIds) {
		if (customerId == null) throw new IllegalArgumentException("customerId cannot be null");
		if (bookIds == null) throw new IllegalArgumentException("bookIds cannot be null");
		
		BookPrice[] bookPrices = Arrays.stream(bookIds)
			.map(bookId -> new BookPrice(bookId, new BigDecimal(customerId*2)))
			.toArray(BookPrice[]::new);
		
		return bookPrices;
	}
}
