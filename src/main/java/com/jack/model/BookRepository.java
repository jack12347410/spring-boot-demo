package com.jack.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    public Page<Book> findAll(Pageable pageable);

    public List<Book> findAllByOrderByName();

    public List<Book> findByAuthor(String author);

    public List<Book> findByDescriptionContains(String content);

    public Book findById(long id);

    @Query(value = "select * from Book where LENGTH(name) > ?1", nativeQuery = true)
    public List<Book> findByJPQL(int len);

    @Modifying
    @Query(value = "update Book set status = ?1 where id = ?2", nativeQuery = true)
    public int updateByJPQL(int status, long id);

    @Modifying
    @Query(value = "delete from Book where id = ?1", nativeQuery = true)
    public int deleteByJPQL(long id);
}
