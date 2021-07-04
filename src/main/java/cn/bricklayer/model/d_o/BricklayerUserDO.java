package cn.bricklayer.model.d_o;

import cn.bricklayer.model.dto.BricklayerUserDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;
import java.util.stream.Collectors;


@TableName("bricklayer_user")
public class BricklayerUserDO {

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private  String nickName;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private  String remark;

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
    public BricklayerUserDTO toBricklayerUserDTO() {
        BricklayerUserDTO bricklayerUserDTO = new BricklayerUserDTO();
        bricklayerUserDTO.setId(getId());
        bricklayerUserDTO.setNickName(getNickName());
        bricklayerUserDTO.setRemark(getRemark());
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

    public String getNickName(){
    return nickName;
    }

    public void setNickName(String nickName){
    this.nickName=nickName;
    }

    public String getRemark(){
    return remark;
    }

    public void setRemark(String remark){
    this.remark=remark;
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