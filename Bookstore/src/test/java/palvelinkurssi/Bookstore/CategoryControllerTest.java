package palvelinkurssi.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import palvelinkurssi.Bookstore.web.CategoryController;


@SpringBootTest
public class CategoryControllerTest {
	
	@Autowired
	private CategoryController categorycontroller;
	
	
	@Test
	public void ccSmokeTest() throws Exception{
		assertThat(categorycontroller).isNotNull();
	}

}
