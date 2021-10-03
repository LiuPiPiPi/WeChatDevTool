package com.lpxz.wechatdevtool.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * 父按钮
 *
 * @author LPxz
 * @since 2021-10-11 15:24
 */
@Setter
@Getter
public class ComplexButton extends BasicButton {
    private BasicButton[] sub_button;

}
