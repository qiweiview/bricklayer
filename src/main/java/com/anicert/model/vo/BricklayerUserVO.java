package com.anicert.model.vo;


import com.anicert.utils.AESUtils;

import java.util.Base64;

public class BricklayerUserVO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 用户角色
     */
    private String userRole;

    private String token;


    public BricklayerUserVO secrete() {
        setId(null);
        setUserPassword(null);
        String s = getUserName() + ":" + getUserRole();
        byte[] encrypt = AESUtils.encrypt(s.getBytes());
        String s1 = Base64.getEncoder().encodeToString(encrypt);
        setToken(s1);
        return this;
    }

    public static BricklayerUserVO fromToken(String token) {
        byte[] decode = Base64.getDecoder().decode(token);
        byte[] decrypt = AESUtils.decrypt(decode);
        String s = new String(decrypt);
        BricklayerUserVO bricklayerUserVO = new BricklayerUserVO();
        String[] split = s.split(":");
        bricklayerUserVO.setUserName(split[0]);
        bricklayerUserVO.setUserRole(split[1]);

        return bricklayerUserVO;

    }

    /*  ------------ data conversion ------------  */


    /*  ------------ getter setter ------------  */

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}