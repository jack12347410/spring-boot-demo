package com.jack.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jack.model.Book;

//@Controller
@RestController
@RequestMapping("/helloworld")
public class SpringBootHelloWorld {

	// @RequestMapping(value = "/hello", method = RequestMethod.POST)
	@PostMapping("/hello")
	public String hello() {
		return "Hey, Spring Boot 的 Hello World ! ";
	}

	@RequestMapping("/test") // 不指定方法request方法，則全適用
	public String Test() {
		return "Test";
	}

	@GetMapping("/books")
//	@ResponseBody
	public Object GetAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
		Map<String, Object> map = new HashMap<>();
		map.put("Name", "Jack");
		map.put("age", 28);
		map.put("Page", page);
		map.put("Size", size);
		return map;
	}

	/**
	 * 
	 * @param id
	 * @param userName
	 * @return
	 */
	@GetMapping("/books/{id}/{userName:[a-z]+}")
	public Object GetOne(@PathVariable int id, @PathVariable String userName) {

		return null;
	}

	@PostMapping("/books")
	public Object Post(@RequestParam String name, @RequestParam String author, @RequestParam String isbn) {
		Map<String, Object> book = new HashMap<>();
		book.put("Name", name);
		book.put("Author", author);
		book.put("ISBN", isbn);

		return book;

	}
}
