package com.book.web;

import com.book.domain.Book;
import com.book.service.BookService;
import com.sun.tracing.dtrace.Attributes;
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

    @RequestMapping(value = "/querybook", produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ArrayList<Book> queryBookDo(HttpServletRequest request,@RequestParam("bookname") String searchWord) {
        boolean exist = bookService.matchBook(searchWord);
        if (exist) {
            ArrayList<Book> books = bookService.queryBook(searchWord);
            return books;
        } else {
            return null;
        }
    }
/*
    @RequestMapping("/reader_querybook.html")
    public ModelAndView readerQueryBook() {
        return new ModelAndView("reader_book_query");

    }

    @RequestMapping("/reader_querybook_do.html")
    public String readerQueryBookDo(HttpServletRequest request, String searchWord, RedirectAttributes redirectAttributes) {
        boolean exist = bookService.matchBook(searchWord);
        if (exist) {
            ArrayList<Book> books = bookService.queryBook(searchWord);
            redirectAttributes.addFlashAttribute("books", books);
            return "redirect:/reader_querybook.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "没有匹配的图书！");
            return "redirect:/reader_querybook.html";
        }

    }
*/
    @RequestMapping(value = "/allbooks",produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ArrayList<Book> allBook() {
        ArrayList<Book> books = bookService.getAllBooks();
        return books;
    }

    @RequestMapping(value = "/deletebook",method = RequestMethod.POST,
            consumes = {"application/JSON;charset=UTF-8"},
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ModelAndView deleteBook(HttpServletRequest request,@RequestBody int bookId) {
        int res = bookService.deleteBook(bookId);
        Map<String, Integer> map = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        if (res == 1) {
            map.put("state",0);
        } else {
            map.put("state",1);
        }
        modelAndView.addObject(map);
        return modelAndView;
    }

    /*
    @RequestMapping("/book_add.html")
    public ModelAndView addBook(HttpServletRequest request) {

        return new ModelAndView("admin_book_add");

    }
    */
    @RequestMapping(value = "/addbook",method = RequestMethod.POST,
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ModelAndView addBookDo(@ModelAttribute BookAddCommand bookAddCommand) {
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
        book.setISBN(bookAddCommand.getIsbn());

        boolean succ = bookService.addBook(book);
        Map<String, Integer> map = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        if (succ) {
            map.put("state",0);
        } else {
            map.put("state",1);
        }
        modelAndView.addObject(map);
        return modelAndView;
    }
/*
    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request) {
        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }
*/
    @RequestMapping(value = "/editbook",method = RequestMethod.POST,
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ModelAndView bookEditDo(@ModelAttribute Book book) {

        boolean succ = bookService.editBook(book);
        Map<String, Integer> map = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        if (succ) {
            map.put("state",0);
        } else {
            map.put("state",1);
        }
        modelAndView.addObject(map);
        return modelAndView;
    }

/*
    @RequestMapping("/bookdetail.html")
    public ModelAndView bookDetail(HttpServletRequest request) {
        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }


    @RequestMapping("/readerbookdetail.html")
    public ModelAndView readerBookDetail(HttpServletRequest request) {
        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("reader_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }
*/

}
