package com.lpxz.wechatdevtool.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LPxz
 * @since 2021-10-11 15:23
 */
@Setter
@Getter
public class ViewButton extends BasicButton {
    private String type;
    private String name;
    private String url;

}
