package by.malinka.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {
	Collection<T> findAll();
	
	Optional<T> findById(Long id);
	
	T saveOrUpdate(T t);
	
	String deleteById(Long id);

	Page<T> findAll(Pageable pageable);
}
