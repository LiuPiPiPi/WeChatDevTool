package com.lpxz.wechatdevtool.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * 凭证 access_token
 *
 * @author LPxz
 * @since 2021-10-3
 */
@Setter
@Getter
public class AccessToken {
    /**
     * 获取到的凭证
     */
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒
     */
    private int expiresIn;
}
