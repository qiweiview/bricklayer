package cn.bricklayer.model.dto;

import cn.bricklayer.model.d_o.BricklayerUserDO;
import cn.bricklayer.model.vo.BricklayerUserVO;

import java.util.List;
import java.util.stream.Collectors;


public class BricklayerUserDTO {

    // page field
    private int current;

    private int size;

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


/*  ------------ data conversion ------------  */
public  BricklayerUserDO toBricklayerUserDO(){
        BricklayerUserDO bricklayerUserDO =new BricklayerUserDO();
        bricklayerUserDO.setId(getId());
        bricklayerUserDO.setNickName(getNickName());
        bricklayerUserDO.setRemark(getRemark());
        bricklayerUserDO.setUserName(getUserName());
        bricklayerUserDO.setUserPassword(getUserPassword());
        bricklayerUserDO.setUserRole(getUserRole());
        return bricklayerUserDO;

        }

    public BricklayerUserVO toBricklayerUserVO() {
        BricklayerUserVO bricklayerUserVO = new BricklayerUserVO();
        bricklayerUserVO.setId(getId());
        bricklayerUserVO.setNickName(getNickName());
        bricklayerUserVO.setRemark(getRemark());
        bricklayerUserVO.setUserName(getUserName());
        bricklayerUserVO.setUserPassword(getUserPassword());
        bricklayerUserVO.setUserRole(getUserRole());
        return bricklayerUserVO;

    }



    public static   List<BricklayerUserVO>  toBricklayerUserVOList(List<BricklayerUserDTO> bricklayerUserDTOList){
        List<BricklayerUserVO> collect= bricklayerUserDTOList.stream().map(x->x.toBricklayerUserVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerUserDO>  toBricklayerUserDOList(List<BricklayerUserDTO> bricklayerUserDtOList){
        List<BricklayerUserDO> collect= bricklayerUserDtOList.stream().map(x->x.toBricklayerUserDO()).collect(Collectors.toList());
        return collect;
    }

/*  ------------ getter setter ------------  */
    public int getCurrent(){
        return current;
    }

    public void setCurrent(int current){
        this.current=current;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size=size;
    }


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