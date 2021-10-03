package com.lpxz.wechatdevtool.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * 普通按钮
 *
 * @author LPxz
 * @since 2021-10-12 9:56
 */
@Setter
@Getter
public class CommonButton extends BasicButton {
    private String type;

    private String key;
}
