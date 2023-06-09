package com.jack.service;

import com.jack.exception.BookNotFoundException;
import com.jack.model.Book;
import com.jack.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * 查詢書單列表
     *
     * @return
     */
    public List<Book> FindAll() {
        return bookRepository.findAllByOrderByName();

    }

    /**
     * 分頁查詢書單列表
     * @return
     */
    public Page<Book> FindAllByPage(Pageable pageable){
//        List<String> list = new ArrayList<>(Arrays.asList("name"));
//        Sort sort = Sort.by("name");
//        Pageable pageable = new PageRequest(1, 5, sort);
//        return  bookRepository.findAll(pageable);

//        return  bookRepository.findAll(PageRequest.of(0,5, Sort.Direction.ASC, "name"));
        return bookRepository.findAll(pageable);
    }

    /**
     * 查詢書單 by author
     *
     * @param author
     * @return
     */
    public List<Book> FindAll(String author) {
        return bookRepository.findByAuthor(author);
    }

    /**
     * 查詢自訂義JPQL
     *
     * @param len
     * @return
     */
    public List<Book> FindJPQL(int len) {
        return bookRepository.findByJPQL(len);
    }

    /**
     * 取得一條書單
     *
     * @param id
     * @return
     */
    public Book FindOneById(long id) {
        Book result = bookRepository.findById(id);
        if(result == null){
            throw new BookNotFoundException("找不到該書單");
        }
        return result;
//        return result == null ? new Book() : result;
    }

    /**
     * 新增/更新一筆書單
     *
     * @param book
     * @return
     */
    public Book InsertOrUpdate(Book book) {
        return bookRepository.save(book);
    }

    /**
     * 新增自訂義JPQL
     *
     * @param status
     * @param id
     * @return
     */
    @Transactional
    public int UpdateByJPQL(int status, long id) {
        return bookRepository.updateByJPQL(status, id);
    }

    /**
     * 刪除一筆
     *
     * @param id
     */
    public void Delete(long id) {
        bookRepository.deleteById(id);
    }

    /**
     * 刪除自訂義JPQL
     *
     * @param id
     * @return
     */
    @Transactional
    public int DeleteByJPQL(long id) {
        return bookRepository.deleteByJPQL(id);
    }
}
