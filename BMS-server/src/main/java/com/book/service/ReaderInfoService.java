package com.book.service;

import com.book.dao.ReaderInfoDao;
import com.book.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReaderInfoService {

    private ReaderInfoDao readerInfoDao;
    @Autowired
    public void setReaderInfoDao(ReaderInfoDao readerInfoDao) {
        this.readerInfoDao = readerInfoDao;
    }

    //查询所有读者信息
    public ArrayList<ReaderInfo> readerInfos(){
        return readerInfoDao.getAllReaderInfo();
    }
    //根据ID删除读者
    public boolean deleteReaderInfo(int readerId){
        return readerInfoDao.deleteReaderInfo(readerId)>0;
    }
    //根据ID查找读者
    public ReaderInfo getReaderInfo(int readerId){
        return readerInfoDao.findReaderInfoByReaderId(readerId);
    }
    //编辑读者信息
    public boolean editReaderInfo(ReaderInfo readerInfo){
        return readerInfoDao.editReaderInfo(readerInfo)>0;
    }
    //添加读者
    public boolean addReaderInfo(ReaderInfo readerInfo){
        return  readerInfoDao.addReaderInfo(readerInfo)>0;
    }
}
