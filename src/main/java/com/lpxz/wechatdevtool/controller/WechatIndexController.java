package com.lpxz.wechatdevtool.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lpxz.wechatdevtool.entity.People;
import com.lpxz.wechatdevtool.mapper.PeopleMapper;
import com.lpxz.wechatdevtool.service.MessageService;
import com.lpxz.wechatdevtool.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * WechatPublicAccount
 *
 * @author LPxz
 * @since 2021-10-17 21:52
 */
@RestController
@RequestMapping("/wechat")
public class WechatIndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatIndexController.class);
    @Autowired
    private MessageService messageService;

    private final PeopleMapper peopleMapper;

    public WechatIndexController(PeopleMapper peopleMapper) {
        this.peopleMapper = peopleMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        try (PrintWriter out = response.getWriter()) {
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
//        String respMessage = weixinPost(request);
        String respMessage = messageService.newMessageRequest(request);

        // 响应消息
        try (PrintWriter out = response.getWriter()) {
            out.print(respMessage);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    @RequestMapping("/test")
    public String test() {
        // test
        QueryWrapper<People> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);
        People people1 = peopleMapper.selectOne(wrapper);
        String name = people1.getNickName();
        System.out.println(name);
        System.out.println(people1);
        return name;
    }
}
