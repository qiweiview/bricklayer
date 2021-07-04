package cn.bricklayer.model.vo;


import cn.bricklayer.utils.AESUtils;
import lombok.Data;

import java.util.Base64;

@Data
public class BricklayerUserVO {
    /**
     * 主键
     */
    private  Integer id;
    /**
     * 昵称
     */
    private  String nickName;
    /**
     * 备注
     */
    private  String remark;
    /**
     * 用户名
     */
    private  String userName;
    /**
     * 密码
     */
    private  String userPassword;
    /**
     * 用户角色
     */
    private  String userRole;

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

}