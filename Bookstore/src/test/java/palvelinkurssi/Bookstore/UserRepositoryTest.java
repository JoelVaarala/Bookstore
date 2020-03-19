package palvelinkurssi.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import palvelinkurssi.Bookstore.Domain.User;
import palvelinkurssi.Bookstore.Domain.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
	@Test
	public void AddUser() {
		User user = new User ("DUDE", "DUDE", "DUDE@dd", "DUDE");
		repository.save(user);
    	assertThat(user.getUsername()).isNotNull();
	}
	
	@Test
	public void DeleteUsers() {
		repository.deleteAll();
    	assertThat(repository.count()).isEqualTo(0);
	}
}



