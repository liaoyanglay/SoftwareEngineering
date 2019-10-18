package com.book.service;

import com.book.dao.LendDao;
import com.book.domain.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LendService {
    private LendDao lendDao;

    @Autowired
    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }

    //借书
    public boolean bookLend(long bookId, int readerId) {
        return lendDao.bookLendOne(bookId, readerId) > 0 && lendDao.bookLendTwo(bookId) > 0;
    }

    //还书
    public boolean bookReturn(long bookId) {
        return lendDao.bookReturnOne(bookId) > 0 && lendDao.bookReturnTwo(bookId) > 0;
    }

    //查询流水记录
    public ArrayList<Lend> lendList() {
        return lendDao.lendList();
    }
    //查询改读者流水记录
    public ArrayList<Lend> myLendList(int readerId) {
        return lendDao.myLendList(readerId);
    }

}
