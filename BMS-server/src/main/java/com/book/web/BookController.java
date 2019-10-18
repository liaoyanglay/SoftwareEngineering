package com.book.web;

import com.book.domain.Book;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    //查询符合条件的书籍信息，返回json数据
    @RequestMapping(value = "/querybook", produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ArrayList<Book> queryBookDo(@RequestParam("name") String searchWord) {
        boolean exist = bookService.matchBook(searchWord);
        if (exist) {
            ArrayList<Book> books = bookService.queryBook(searchWord);
            return books;
        } else {
            return null;
        }
    }


    //查询所有书籍信息，返回json数据
    @RequestMapping(value = "/allbooks",produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ArrayList<Book> allBook() {
        ArrayList<Book> books = bookService.getAllBooks();
        return books;
    }

    @RequestMapping(value = "/deletebook",method = RequestMethod.POST,
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public String deleteBook(@ModelAttribute("id") String id) {
        int res = bookService.deleteBook(Long.parseLong(id));
        if (res == 1) {
            return "{\"state\":0}";
        } else {
            return "{\"state\":1}";
        }
    }

    //接收书籍信息,调用addBook向数据库添加书籍信息
    @RequestMapping(value = "/addbook",method = RequestMethod.POST,
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public String addBookDo(@ModelAttribute BookAddCommand bookAddCommand) {
        Book book = new Book();
        book.setName(bookAddCommand.getName());
        book.setCover(bookAddCommand.getCover());
        book.setClazz(bookAddCommand.getClazz());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setPublisher(bookAddCommand.getPublisher());
        book.setPublishDate(bookAddCommand.getPublishDate());
        book.setPrice(bookAddCommand.getPrice());
        book.setRate(bookAddCommand.getRate());
        book.setDescription(bookAddCommand.getDescription());
        book.setIsbn(bookAddCommand.getIsbn());

        boolean succ = bookService.addBook(book);
        if (succ) {
            return "{\"state\":0}";
        } else {
            return "{\"state\":1}";
        }
    }

    //接收书籍信息,调用editBook更新数据库信息
    @RequestMapping(value = "/editbook",method = RequestMethod.POST,
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public String bookEditDo(@ModelAttribute Book book) {

        boolean succ = bookService.editBook(book);
        if (succ) {
            return "{\"state\":0}";
        } else {
            return "{\"state\":1}";
        }
    }
}
