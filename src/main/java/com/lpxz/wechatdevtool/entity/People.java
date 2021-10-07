package com.lpxz.wechatdevtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author LPxz
 * @since 2021/10/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {
    @TableId(type = IdType.ASSIGN_ID)
    private int id;
    // 微信用户 open_id
    private String openId;
    // 参与者的序号
    private String peopleIndex;
    // 参与者的微信号
    private String wechatNumber;
    // 参与者的昵称
    private String nickName;
}
