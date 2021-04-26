package com.anicert.model.d_o;

import com.anicert.model.dto.BricklayerUserDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;



@TableName("bricklayer_user")
public class BricklayerUserDO {

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private  String userName;

    /**
     * 密码
     */
    @TableField(value = "user_password")
    private  String userPassword;

    /**
     * 用户角色
     */
    @TableField(value = "user_role")
    private  String userRole;

/*  ------------ init value ------------  */
    public void doInit() {
        //setDelFlag(false);
        //setCreateTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        }

    public void doUpdate() {
        //setUpdateTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        }

    public void doDelete() {
        //setDelFlag(true);
        //doUpdate();
        }


/*  ------------ data conversion by model ------------  */
    public  BricklayerUserDTO toBricklayerUserDTO(){
    BricklayerUserDTO bricklayerUserDTO =new BricklayerUserDTO();
    bricklayerUserDTO.setId(getId());
    bricklayerUserDTO.setUserName(getUserName());
    bricklayerUserDTO.setUserPassword(getUserPassword());
    bricklayerUserDTO.setUserRole(getUserRole());
        return bricklayerUserDTO;

}


    public static   List<BricklayerUserDTO>  toBricklayerUserDTOList(List<BricklayerUserDO> bricklayerUserDOList){
        List<BricklayerUserDTO> collect = bricklayerUserDOList.stream().map(x -> x.toBricklayerUserDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */

    public Integer getId(){
    return id;
    }

    public void setId(Integer id){
    this.id=id;
    }

    public String getUserName(){
    return userName;
    }

    public void setUserName(String userName){
    this.userName=userName;
    }

    public String getUserPassword(){
    return userPassword;
    }

    public void setUserPassword(String userPassword){
    this.userPassword=userPassword;
    }

    public String getUserRole(){
    return userRole;
    }

    public void setUserRole(String userRole){
    this.userRole=userRole;
    }
}