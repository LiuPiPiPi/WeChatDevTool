package com.lpxz.wechatdevtool.message;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LPxz
 * @since 2021-10-11 10:19
 */
@Setter
@Getter
public class MusicMessage extends BaseMessage {
    /**
     * 音乐
     */
    private Music Music;

}
