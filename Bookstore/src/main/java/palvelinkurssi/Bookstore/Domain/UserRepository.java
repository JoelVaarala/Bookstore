package palvelinkurssi.Bookstore.Domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username); // Pakollinen!!
}
