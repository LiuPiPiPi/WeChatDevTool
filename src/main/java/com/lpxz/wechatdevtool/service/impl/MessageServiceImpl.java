package com.lpxz.wechatdevtool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lpxz.wechatdevtool.entity.People;
import com.lpxz.wechatdevtool.mapper.PeopleMapper;
import com.lpxz.wechatdevtool.message.Article;
import com.lpxz.wechatdevtool.message.NewsMessage;
import com.lpxz.wechatdevtool.message.TextMessage;
import com.lpxz.wechatdevtool.service.MessageService;
import com.lpxz.wechatdevtool.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author LPxz
 * @since 2021-10-11 15:33
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final PeopleMapper peopleMapper;

    public MessageServiceImpl(PeopleMapper peopleMapper) {
        this.peopleMapper = peopleMapper;
    }

    @Override
    public String newMessageRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.xmlToMap(request);
            assert requestMap != null;
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众账号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 消息内容
            String content = requestMap.get("Content");
            LOGGER.info("FromUserName is:" + fromUserName + ", ToUserName is:" + toUserName + ", MsgType is:" + msgType);
            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                // 这里根据关键字执行相应的逻辑，只有你想不到的，没有做不到的
                /*if(content.equals("xxx")){

                }*/
                // 自动回复
                TextMessage text = new TextMessage();

                // test
                QueryWrapper<People> wrapper = new QueryWrapper<>();
                wrapper.eq("id", 1);
                People people1 = peopleMapper.selectOne(wrapper);
                String name = people1.getNickName();

                text.setContent("您的微信号是：" + fromUserName + "  " + name + "\n您发送的内容是：" + content);
                text.setToUserName(fromUserName);
                text.setFromUserName(toUserName);
                text.setCreateTime(new Date().getTime());
                text.setMsgType(msgType);
                respMessage = MessageUtil.textMessageToXml(text);
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                String eventType = requestMap.get("Event"); // 事件类型
                switch (eventType) {
                    // 订阅
                    case MessageUtil.EVENT_TYPE_SUBSCRIBE:
                        // 文本消息
//                        TextMessage text = new TextMessage();
//                        text.setContent("我不管，我最美！！");
//                        text.setToUserName(fromUserName);
//                        text.setFromUserName(toUserName);
//                        text.setCreateTime(new Date().getTime());
//                        text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//                        respMessage = MessageUtil.textMessageToXml(text);

                        // 图文消息
                        NewsMessage newsMessage = new NewsMessage();
                        newsMessage.setToUserName(fromUserName);
                        newsMessage.setFromUserName(toUserName);
                        newsMessage.setCreateTime(new Date().getTime());
                        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                        newsMessage.setFuncFlag(0);
                        List<Article> articleList = new ArrayList<>();

                        Article article = new Article();
                        article.setTitle("我是一个图文");
                        article.setDescription("我是描述信息");
                        article.setPicUrl("https://lpxz.oss-cn-zhangjiakou.aliyuncs.com/XuSong_avatar.jpg");
                        article.setUrl("https://segmentfault.com/u/lpxz");
                        articleList.add(article);
                        // 设置图文消息个数
                        newsMessage.setArticleCount(articleList.size());
                        // 设置图文消息包含的图文集合
                        newsMessage.setArticles(articleList);
                        // 将图文消息对象转换成xml字符串
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);

                        break;
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                    case MessageUtil.EVENT_TYPE_UNSUBSCRIBE: // 取消订阅
                        break;
                    // 自定义菜单点击事件
                    case MessageUtil.EVENT_TYPE_CLICK:
                        String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应

                        if (eventKey.equals("return_content")) {
                            TextMessage text = new TextMessage();
                            text.setContent("赞赞赞");
                            text.setToUserName(fromUserName);
                            text.setFromUserName(toUserName);
                            text.setCreateTime(new Date().getTime());
                            text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                            respMessage = MessageUtil.textMessageToXml(text);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.error("error......");
        }
        return respMessage;
    }
}
