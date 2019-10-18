package com.book.domain;

import java.io.Serializable;
import java.util.Date;

public class Lend implements Serializable {

    private long sernum;        //流水号
    private long bookId;        //借出书籍ID
    private int readerId;       //借书人ID
    private Date lendDate;      //借书日期
    private Date backDate;      //归还日期

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public void setSernum(long sernum) {
        this.sernum = sernum;
    }

    public int getReaderId() {
        return readerId;
    }

    public long getBookId() {
        return bookId;
    }

    public Date getBackDate() {
        return backDate;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public long getSernum() {
        return sernum;
    }
}
