package com.lpxz.wechatdevtool.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LPxz
 * @since 2021-10-11 15:33
 */
public interface MessageService {
    String newMessageRequest(HttpServletRequest request);
}
