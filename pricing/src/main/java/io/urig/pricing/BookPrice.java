package io.urig.pricing;

import java.math.BigDecimal;

public class BookPrice {
	private long bookId;
	private BigDecimal price;
	
	public BookPrice(long bookId, BigDecimal price) {
		super();
		this.bookId = bookId;
		this.price = price;
	}

	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
