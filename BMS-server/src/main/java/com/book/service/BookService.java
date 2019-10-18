package com.book.service;

import com.book.dao.BookDao;
import com.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    //查询符合条件书籍
    public ArrayList<Book> queryBook(String searchWord){
        return  bookDao.queryBook(searchWord);
    }
    //查询所有书籍
    public ArrayList<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }
    //删除书籍
    public int deleteBook(long bookId){
        return bookDao.deleteBook(bookId);
    }
    //查询符合条件的书籍数量
    public boolean matchBook(String searchWord){
        return bookDao.matchBook(searchWord)>0;
    }
    //添加书籍
    public boolean addBook(Book book){
        return bookDao.addBook(book)>0;
    }
    //根据ID查询书籍信息
    public Book getBook(long bookId){
        Book book=bookDao.getBook(bookId);
        return book;
    }

    public boolean editBook(Book book){
        return bookDao.editBook(book)>0;
    }

}
