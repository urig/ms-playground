package io.urig.checkout;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout/{customerId}")
public class CheckoutRestController {

	private InventoryClient inventoryClient;
	
	public CheckoutRestController(InventoryClient inventoryClient) {
		this.inventoryClient = inventoryClient;
	}
	
	@GetMapping("")
	public void customerBuysBook(@PathVariable Long customerId, @RequestParam Long bookId) {
		Book book = inventoryClient.getBookById(1L).get();
		Book book2 = book;
	}
}
