package com.ds.user.domain.dto;

import com.ds.user.domain.po.User;
import lombok.Data;

import java.util.List;

/**
 * @author writiger
 * @description 用户列表VO
 * @create_at 2024-03-17 16:38
 */
@Data
public class UserListVDTO {
    List<User> userList;
    Integer count;
}
