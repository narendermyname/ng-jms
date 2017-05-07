package com.naren.service;

import java.util.List;
/**
 * 
 */

/**
 * @author ntanwa
 *
 */
public interface Service<T,Id> {
	public T findById(Id id);
	public T findByName(String name);
    
	public void save(T t);
     
	public void update(T t);
     
	public void deleteById(Id id);
 
	public List<T> findAll(); 
     
	public void deleteAll();
     
	public boolean isExist(T t);
}
