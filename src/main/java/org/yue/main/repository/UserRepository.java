package org.yue.main.repository;

import java.util.List;

 
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.yue.main.pojo.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByName(String name);
	List<User> findByNameAndAge(String name,Integer age);
	List<User> findByNameLike(String name);
	List<User> findById(int i);
	Object findAllById(int i);
	@Query(value = "update User set name=? where id=? ", nativeQuery = true)  
	@Modifying  
	public void updateOne(String name,int id); 
	@Query(value = "delete from User where age=?1 and name=?2 ", nativeQuery = true)  
    @Modifying  
    public void deleteUserByWhere(String age,String name);  
	 
	 
 
 
	 
 
}
