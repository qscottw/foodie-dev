package com.qw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class BasicController {
   public static final Integer COMMENT_PAGE_SIZE = 10;
   public static final Integer PAGE_SIZE = 20;


}
