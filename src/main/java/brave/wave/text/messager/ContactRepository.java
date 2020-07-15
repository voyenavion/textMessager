package brave.wave.text.messager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

	List<Contact> findBySubscribed(boolean subscribed);
	
	@org.springframework.transaction.annotation.Transactional
	@Modifying(clearAutomatically=true)
	@Query("update Contact c set c.subscribed = ?1 where c.phonenumber = ?2")
	int setSubscribedForContact(boolean subscribed, String phonenumber);

	boolean existsByPhonenumber(String phonenumber);

	List<Contact> findByPhonenumber(String phonenumber);

}
