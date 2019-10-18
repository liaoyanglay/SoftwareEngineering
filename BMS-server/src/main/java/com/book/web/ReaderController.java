package com.book.web;

import com.book.domain.ReaderInfo;
import com.book.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReaderController {

    private ReaderInfoService readerInfoService;

    @Autowired
    public void setReaderInfoService(ReaderInfoService readerInfoService) {
        this.readerInfoService = readerInfoService;
    }

    //查询所有读者信息
    @RequestMapping(value = "/allreaders", produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public ArrayList<ReaderInfo> allReaders() {
        ArrayList<ReaderInfo> allreaders = readerInfoService.readerInfos();
        return allreaders;
    }


    @RequestMapping(value = "/editreaderinfo", method = RequestMethod.POST,
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public String readerInfoEditDo(@ModelAttribute("readerId") String readerId, @ModelAttribute("name") String name,
                                         @ModelAttribute("sex") String sex, @ModelAttribute("birth") String birth,
                                         @ModelAttribute("address") String address, @ModelAttribute("telcode") String telcode) {
        int readerid = Integer.parseInt(readerId);
        ReaderInfo readerInfo = new ReaderInfo();
        readerInfo.setReaderId(readerid);
        readerInfo.setName(name);
        readerInfo.setSex(sex);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date nbirth = new Date();
        try {
            java.util.Date date = sdf.parse(birth);
            nbirth = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        readerInfo.setBirth(nbirth);
        readerInfo.setAddress(address);
        readerInfo.setTelcode(telcode);
        boolean editsucc = readerInfoService.editReaderInfo(readerInfo);
        if (editsucc) {
            return "{\"state\":0}";
        } else {
            return "{\"state\":1}";
        }
    }

    //增加读者
    @RequestMapping("/addreader")
    public String readerInfoAdd() {
        boolean succ = false;
        if (succ) {
            return "{\"state\":0}";
        } else {
            return "{\"state\":1}";
        }
    }

    //用户功能--修改密码
    @RequestMapping("/reader_repasswd_do")
    public String readerRePasswdDo(String oldPasswd, String newPasswd, String reNewPasswd) {
        boolean succ = false;
        if (succ) {
            return "{\"state\":0}";
        } else {
            return "{\"state\":1}";
        }
    }
}
