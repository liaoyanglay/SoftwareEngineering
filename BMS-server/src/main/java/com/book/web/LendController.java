package com.book.web;

import com.book.domain.Lend;
import com.book.service.BookService;
import com.book.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class LendController {

    private LendService lendService;
    @Autowired
    public void setLendService(LendService lendService) {
        this.lendService = lendService;
    }
    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    //借书处理
    @RequestMapping(value = "/lendbook",method = RequestMethod.POST,
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public String bookLendDo(@ModelAttribute("bookId") String bookId,
                             @ModelAttribute("readerId") String readerId){
        boolean exist = lendService.hasBook(Long.parseLong(bookId))
                && lendService.hasReader(Integer.parseInt(readerId));
        boolean lendsucc = false;
        if(exist)
            lendsucc=lendService.bookLend(Long.parseLong(bookId),Integer.parseInt(readerId));
        if (lendsucc) {
            return "{\"state\":0}";
        } else {
            return "{\"state\":1}";
        }
    }

    //还书处理
    @RequestMapping(value = "/returnbook",method = RequestMethod.POST,
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public String bookReturn(@ModelAttribute("bookId") String bookId){
        boolean exist = lendService.hasBook(Long.parseLong(bookId));
        boolean lendsucc = false;
        if(exist)
            lendsucc=lendService.bookReturn(Long.parseLong(bookId));
        if (lendsucc) {
            return "{\"state\":0}";
        } else {
            return "{\"state\":1}";
        }
    }

    //查询所有的流水
    @RequestMapping(value = "/lendlist",produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ArrayList<Lend> allBook() {
        ArrayList<Lend> lendlist = lendService.lendList();
        return lendlist;
    }

    //查询某个读者的流水
    @RequestMapping(value = "/readerlendlist",produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ArrayList<Lend> myallBook(@RequestParam("readerId") String readerId) {
        ArrayList<Lend> myLendList = lendService.myLendList(Integer.parseInt(readerId));
        return myLendList;
    }
}
