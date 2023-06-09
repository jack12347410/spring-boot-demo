package com.jack.controller;

import com.jack.exception.BookNotFoundException;
import com.jack.model.Book;
import com.jack.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService _bookService;

    /**
     * 取得全部書單
     *
     * @param model
     * @return
     */
    @GetMapping("/books")
    public String Get(@PageableDefault(size = 3, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable,
                      Model model) {
//        List<Book> books = _bookService.FindAll();
//        model.addAttribute("books", books);
//        page = page < 0 ? 0 : page;
//        Page<Book> books = _bookService.FindAllByPage(PageRequest.of(page, size, Sort.Direction.ASC, "name"));
        Page<Book> page =  _bookService.FindAllByPage(pageable);
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i <page.getTotalPages();i++){
            pageNumbers.add(i);
        }
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("page", page);

        return "books";
    }

    /**
     * 取得書單詳情
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/books/{id}")
    public String Detail(@PathVariable long id, Model model) {
        Book book = _bookService.FindOneById(id);

        model.addAttribute("book", book);

        return "book";
    }

    /**
     * 跳轉至新增頁面
     *
     * @return
     */
    @GetMapping("/books/input")
    public String InputPage(Model model) {
        model.addAttribute("book", new Book());
        return "input";
    }

    /**
     * 跳轉至編輯頁面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/books/{id}/input")
    public String EditPage(@PathVariable long id, Model model) {
        Book book = _bookService.FindOneById(id);
        model.addAttribute("book", book);
        return "input";
    }

    /**
     * 提交一筆書單
     *
     * @param book
     * @return
     */
    @PostMapping("/books")
    public String Post(Book book, final RedirectAttributes attributes) {
        Book result = _bookService.InsertOrUpdate(book);
        if (result != null) {
            attributes.addFlashAttribute("message", "《" + book.getName() + "》" + "提交成功");
        }

        return "redirect:/books";
    }

    /**
     * 刪除一筆
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/books/{id}/delete")
    public String Delete(@PathVariable long id, final RedirectAttributes attributes){
        Book book = _bookService.FindOneById(id);
        _bookService.Delete(id);
        attributes.addFlashAttribute("message", "《" + book.getName() + "》" + "刪除成功");
        return "redirect:/books";
    }



}
