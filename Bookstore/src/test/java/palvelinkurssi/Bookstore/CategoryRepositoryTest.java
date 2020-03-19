package palvelinkurssi.Bookstore;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import palvelinkurssi.Bookstore.Domain.CategoryRepository;
import palvelinkurssi.Bookstore.Domain.Category;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void FindCategory() {
		List<Category> category = repository.findByName("Poetry");
		assertThat(category).hasSize(1);
        assertThat(category.get(0).getName()).isEqualTo("Poetry");
	}
	
	@Test
	public void AddCategory() {
		Category category = new Category ("Comedy");
		repository.save(category);
    	assertThat(category.getName()).isNotNull();
	}
	
	@Test
	public void DeleteCategory() {
		repository.deleteAll();
    	assertThat(repository.count()).isEqualTo(0);
	}

}


