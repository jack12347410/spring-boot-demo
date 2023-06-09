package com.jack.controller;

import com.jack.model.Book;
import com.jack.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookApiController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<Book> Get(@PageableDefault(size = 5, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable){
//        return bookService.FindAll();
//        return bookService.FindAllByPage(PageRequest.of(page,size, Sort.Direction.ASC, "name"));
        return  bookService.FindAllByPage(pageable);
    }

    @GetMapping("/{id}")
    public Book Get(@PathVariable long id){
        return bookService.FindOneById(id);
    }

    @PostMapping("/by")
    public int Post(@RequestParam long id){
//        return bookService.FindAll(author);
//        return bookService.FindJPQL(len);
//        return bookService.UpdateByJPQL(status, id);
        return bookService.DeleteByJPQL(id);
    }


    @PostMapping

    public Book Post(@RequestBody Book book){
        return bookService.InsertOrUpdate(book);
    }

    @PutMapping
    public Book Update(@RequestBody Book book){
        return bookService.InsertOrUpdate(book);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable long id){
        bookService.Delete(id);
    }
}
