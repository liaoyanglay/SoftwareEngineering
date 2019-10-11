package com.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.book.domain.Lend;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@Repository
public class LendDao {

    private JdbcTemplate jdbcTemplate;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String BOOK_RETURN_SQL_ONE="UPDATE lend_list SET lend_listcol = ? WHERE book_id = ? ";

    private final static String BOOK_RETURN_SQL_TWO="UPDATE book_info SET isRent = 0 WHERE id = ? ";

    private final static String BOOK_LEND_SQL_ONE="INSERT INTO lend_list (book_id,reader_id,lend_date) VALUES ( ? , ? , ? )";

    private final static String BOOK_LEND_SQL_TWO="UPDATE book_info SET isRent = 1 WHERE id = ? ";

    private final static String LEND_LIST_SQL="SELECT * FROM lend_list";

    private final static String MY_LEND_LIST_SQL="SELECT * FROM lend_list WHERE reader_id = ? ";

    //根据书号查询图书是否存在
    private final static String HAS_BOOK_SQL = "SELECT count(*) FROM book_info WHERE id = ? ";

    //根据读者ID确定读者是否存在
    private final static String HAS_READER_SQL = "SELECT count(*) FROM reader_info WHERE reader_id = ? ";

    public int bookReturnOne(long bookId){
        return  jdbcTemplate.update(BOOK_RETURN_SQL_ONE,new Object[]{df.format(new Date()),bookId});
    }
    public int bookReturnTwo(long bookId){
        return jdbcTemplate.update(BOOK_RETURN_SQL_TWO,new Object[]{bookId});
    }
    public int bookLendOne(long bookId,int readerId){
        return  jdbcTemplate.update(BOOK_LEND_SQL_ONE,new Object[]{bookId,readerId,df.format(new Date())});
    }
    public int bookLendTwo(long bookId){
        return  jdbcTemplate.update(BOOK_LEND_SQL_TWO,new Object[]{bookId});
    }

    //查询图书是否存在
    public int hasBook(long bookId){
        return jdbcTemplate.queryForObject(HAS_BOOK_SQL, new Object[]{bookId}, Integer.class);
    }

    //查询读者是否存在
    public int hasReader(int readerId ){
        return jdbcTemplate.queryForObject(HAS_READER_SQL, new Object[]{readerId}, Integer.class);
    }

    public ArrayList<Lend> lendList(){
        final ArrayList<Lend> list=new ArrayList<Lend>();

        jdbcTemplate.query(LEND_LIST_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Lend lend=new Lend();
                    lend.setBackDate(resultSet.getDate("lend_listcol"));
                    lend.setBookId(resultSet.getLong("book_id"));
                    lend.setLendDate(resultSet.getDate("lend_date"));
                    lend.setReaderId(resultSet.getInt("reader_id"));
                    lend.setSernum(resultSet.getLong("sernum"));
                    list.add(lend);
                }
            }
        });
        return list;
    }

    public ArrayList<Lend> myLendList(int readerId){
        final ArrayList<Lend> list=new ArrayList<Lend>();

        jdbcTemplate.query(MY_LEND_LIST_SQL, new Object[]{readerId},new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Lend lend=new Lend();
                    lend.setBackDate(resultSet.getDate("lend_listcol"));
                    lend.setBookId(resultSet.getLong("book_id"));
                    lend.setLendDate(resultSet.getDate("lend_date"));
                    lend.setReaderId(resultSet.getInt("reader_id"));
                    lend.setSernum(resultSet.getLong("sernum"));
                    list.add(lend);
                }
            }
        });
        return list;

    }
}
