package com.book.dao;

import com.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class BookDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ADD_BOOK_SQL = "INSERT INTO book_info VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String DELETE_BOOK_SQL = "delete from book_info where id = ?  ";
    private final static String EDIT_BOOK_SQL = "update book_info set bookname= ? ,cover= ? ,clazz= ? ,author= ? ,publisher= ? ,publishDate= ? ,price= ? ,rate= ? ,description= ? ,ISBN= ? ,isRent= ?  where id= ? ;";
    private final static String QUERY_ALL_BOOKS_SQL = "SELECT * FROM book_info ";
    private final static String QUERY_BOOK_SQL = "SELECT * FROM book_info WHERE id like  ?  or bookname like ?   ";
    //查询匹配图书的个数
    private final static String MATCH_BOOK_SQL = "SELECT count(*) FROM book_info WHERE id like ?  or bookname like ?  ";
    //根据书号查询图书
    private final static String GET_BOOK_SQL = "SELECT * FROM book_info where id = ? ";

    public int matchBook(String searchWord) {
        String swcx = "%" + searchWord + "%";
        return jdbcTemplate.queryForObject(MATCH_BOOK_SQL, new Object[]{swcx, swcx}, Integer.class);
    }

    public ArrayList<Book> queryBook(String sw) {
        String swcx = "%" + sw + "%";
        final ArrayList<Book> books = new ArrayList<Book>();
        jdbcTemplate.query(QUERY_BOOK_SQL, new Object[]{swcx, swcx}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setName(resultSet.getString("bookname"));
                    book.setCover(resultSet.getString("cover"));
                    book.setClazz(resultSet.getString("clazz"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPublisher(resultSet.getString("publisher"));
                    book.setPublishDate(resultSet.getString("publishDate"));
                    book.setPrice(resultSet.getString("price"));
                    book.setRate(resultSet.getFloat("rate"));
                    book.setDescription(resultSet.getString("description"));
                    book.setISBN(resultSet.getString("ISBN"));
                    book.setRent(resultSet.getBoolean("isRent"));
                    books.add(book);
                }

            }
        });
        return books;
    }

    public ArrayList<Book> getAllBooks() {
        final ArrayList<Book> books = new ArrayList<Book>();

        jdbcTemplate.query(QUERY_ALL_BOOKS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setName(resultSet.getString("bookname"));
                    book.setCover(resultSet.getString("cover"));
                    book.setClazz(resultSet.getString("clazz"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPublisher(resultSet.getString("publisher"));
                    book.setPublishDate(resultSet.getString("publishDate"));
                    book.setPrice(resultSet.getString("price"));
                    book.setRate(resultSet.getFloat("rate"));
                    book.setDescription(resultSet.getString("description"));
                    book.setISBN(resultSet.getString("ISBN"));
                    book.setRent(resultSet.getBoolean("isRent"));
                    books.add(book);
                }
            }
        });
        return books;

    }

    public int deleteBook(long bookId) {

        return jdbcTemplate.update(DELETE_BOOK_SQL, bookId);
    }

    public int addBook(Book book) {

        int id = book.getId();
        String name = book.getName();
        String cover = book.getCover();
        String author = book.getAuthor();
        String clazz = book.getClazz();
        boolean isRent = book.isRent();
        String publisher = book.getPublisher();
        String publishDate = book.getPublishDate();
        String price = book.getPrice();
        float rate = book.getRate();
        String ISBN = book.getISBN();
        String description = book.getDescription();

        return jdbcTemplate.update(ADD_BOOK_SQL, new Object[]{id, name, cover, clazz, author, publisher, publishDate, price, rate, description, ISBN, isRent});
    }

    public Book getBook(Long bookId) {
        final Book book = new Book();
        jdbcTemplate.query(GET_BOOK_SQL, new Object[]{bookId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("bookname"));
                book.setCover(resultSet.getString("cover"));
                book.setClazz(resultSet.getString("clazz"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setPublishDate(resultSet.getString("publishDate"));
                book.setPrice(resultSet.getString("price"));
                book.setRate(resultSet.getFloat("rate"));
                book.setDescription(resultSet.getString("description"));
                book.setISBN(resultSet.getString("ISBN"));
                book.setRent(resultSet.getBoolean("isRent"));
            }

        });
        return book;
    }

    public int editBook(Book book) {
        int id = book.getId();
        String name = book.getName();
        String cover = book.getCover();
        String author = book.getAuthor();
        String clazz = book.getClazz();
        boolean isRent = book.isRent();
        String publisher = book.getPublisher();
        String publishDate = book.getPublishDate();
        String price = book.getPrice();
        float rate = book.getRate();
        String ISBN = book.getISBN();
        String description = book.getDescription();

        return jdbcTemplate.update(EDIT_BOOK_SQL, new Object[]{id, name, cover, clazz, author, publisher, publishDate, price, rate, description, ISBN, isRent});
    }
}
