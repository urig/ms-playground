package io.urig.inventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Optional;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InventoryApplication.class)
@WebAppConfiguration
public class InventoryRestControllerTests {
	
	@Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
	// Spring boot test 
    @Test
	public void getBooks_getBooks_returnsMobyDick() throws Exception {
	     mockMvc.perform(get("/books"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
	        .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].title", is("Moby Dick")))
            .andExpect(jsonPath("$[0].author", is("Herman Melville")));	
    }
    
    
    @Test 
    public void getBooks_getBooks_returnsMobyDick2() throws Exception {
    	// Arrange
    	Long bookId = 1L;
    	Book book = new Book();
    	BookRepository bookRepository = mock(BookRepository.class);
    	when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
    	InventoryRestController target = new InventoryRestController(bookRepository);
    	
    	// Act
    	Book actual = target.getBookById(bookId);
    	
    	// Assert
		assertThat(actual).isEqualTo(book);
    }

}
