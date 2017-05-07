/**
 * 
 */
package com.naren.service.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naren.dto.user.User;

/**
 * @author ntanwa
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static{
		users= populateDummyUsers();
	}
	/**
	 * 
	 */
	public UserServiceImpl() {

	}

	/* (non-Javadoc)
	 * @see com.naren.service.Service#findById(java.lang.Object)
	 */
	@Override
	public User findById(Long id) {
		for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
	}

	/* (non-Javadoc)
	 * @see com.naren.service.Service#findByName(java.lang.String)
	 */
	@Override
	public User findByName(String name) {
		 for(User user : users){
	            if(user.getUsername().equalsIgnoreCase(name)){
	                return user;
	            }
	        }
	        return null;
	}

	/* (non-Javadoc)
	 * @see com.naren.service.Service#save(java.lang.Object)
	 */
	@Override
	public void save(User user) {
		user.setId(counter.incrementAndGet());
        users.add(user);

	}

	/* (non-Javadoc)
	 * @see com.naren.service.Service#update(java.lang.Object)
	 */
	@Override
	public void update(User user) {
		int index = users.indexOf(user);
        users.set(index, user);

	}

	/* (non-Javadoc)
	 * @see com.naren.service.Service#deleteById(java.lang.Object)
	 */
	@Override
	public void deleteById(Long id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }

	}

	/* (non-Javadoc)
	 * @see com.naren.service.Service#findAll()
	 */
	@Override
	public List<User> findAll() {
		return users;
	}

	/* (non-Javadoc)
	 * @see com.naren.service.Service#deleteAll()
	 */
	@Override
	public void deleteAll() {
		users.clear();

	}

	/* (non-Javadoc)
	 * @see com.naren.service.Service#isExist(java.lang.Object)
	 */
	@Override
	public boolean isExist(User user) {
		 return findByName(user.getUsername())!=null;
	}
	
	/**
	 * Create Some dummy Users
	 * @return
	 */
	private static List<User> populateDummyUsers(){
        List<User> users = new ArrayList<User>();
        users.add(new User(counter.incrementAndGet(),"Sam", "NY", "sam@abc.com"));
        users.add(new User(counter.incrementAndGet(),"Tomy", "ALBAMA", "tomy@abc.com"));
        users.add(new User(counter.incrementAndGet(),"Kelly", "NEBRASKA", "kelly@abc.com"));
        return users;
    }

}
