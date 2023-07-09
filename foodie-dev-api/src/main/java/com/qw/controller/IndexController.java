package com.qw.controller;

import com.qw.enums.YesOrNo;
import com.qw.pojo.Carousel;
import com.qw.pojo.Category;
import com.qw.pojo.vo.CategoryVO;
import com.qw.pojo.vo.NewItemsVO;
import com.qw.service.CarouselService;
import com.qw.service.CategoryService;
import com.qw.utils.QWJSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/carousel")
    public QWJSONResult carousel(){
        List<Carousel> cList = carouselService.queryAll(YesOrNo.YES.type);
        return QWJSONResult.ok(cList);
    }


    @GetMapping("/setSession")
    public Object setSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", "new user");
        session.setMaxInactiveInterval(3600);
        session.getAttribute("userInfo");
//        session.removeAttribute("userInfo");
        return "ok";
    }

    @GetMapping("/cats")
    public QWJSONResult cat(){
        List<Category> result = categoryService.queryAllRootCat();
        return QWJSONResult.ok(result);
    }


    @GetMapping("/subCat/{rootCatId}")
    public QWJSONResult subCat(@PathVariable Integer rootCatId){
        if (rootCatId==null){
            return QWJSONResult.errorMsg("category doesn't exist");
        }
        List<CategoryVO> result = categoryService.getSubCatList(rootCatId);
        return QWJSONResult.ok(result);
    }

    @GetMapping("/sixNewItems/{rootCatId}")
    public QWJSONResult sixNewItems(@PathVariable Integer rootCatId){
        if (rootCatId==null){
            return QWJSONResult.errorMsg("category doesn't exist");
        }
        List<NewItemsVO> result = categoryService.getSixNewItemsLazy(rootCatId);
        return QWJSONResult.ok(result);
    }
}
