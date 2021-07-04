package cn.bricklayer.model.dto;

import cn.bricklayer.model.d_o.BricklayerLogDO;
import cn.bricklayer.model.vo.BricklayerLogVO;
import lombok.Data;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BricklayerLogDTO {

    // page field
    private int current;

    private int size;

    private List<Integer> ids;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 创建日期
     */
    private LocalDateTime createDate;
    /**
     * 修改日期
     */
    private LocalDateTime updateDate;
    /**
     * ip
     */
    private String ipAddress;
    /**
     * 访问设备
     */
    private String userAgent;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 访问地址
     */
    private String requestUri;

    private LocalDate startDate;


    private LocalDate endDate;

    private String startDateStr;


    private String endDateStr;


    private Long countValue;


    public void str2Date() {
        if (null == getStartDateStr() || "".equals(getStartDateStr())) {
            setStartDate(LocalDate.now());
        } else {
            LocalDate start = LocalDate.parse(getStartDateStr());
            setStartDate(start);
        }

        if (null == getEndDateStr() || "".equals(getEndDateStr())) {
            setEndDate(LocalDate.now().plusDays(1));
        } else {
            LocalDate end = LocalDate.parse(getEndDateStr());
            setEndDate(end);
        }

        if (getEndDate().isEqual(getStartDate())) {
            LocalDate localDate = getEndDate().plusDays(1);
            setEndDate(localDate);
        }
    }


    /*  ------------ data conversion ------------  */

    public static BricklayerLogDTO of(HttpServletRequest request) {
        BricklayerLogDTO bricklayerLogDTO = new BricklayerLogDTO();


        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        bricklayerLogDTO.setIpAddress(ipAddress);

        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        bricklayerLogDTO.setUserAgent(userAgent);


        return bricklayerLogDTO;

    }


    public BricklayerLogDO toBricklayerLogDO() {
        BricklayerLogDO bricklayerLogDO = new BricklayerLogDO();
        bricklayerLogDO.setId(getId());
        bricklayerLogDO.setCreateBy(getCreateBy());
        bricklayerLogDO.setUpdateBy(getUpdateBy());
        bricklayerLogDO.setCreateDate(getCreateDate());
        bricklayerLogDO.setUpdateDate(getUpdateDate());
        bricklayerLogDO.setCountValue(getCountValue());
        bricklayerLogDO.setIpAddress(getIpAddress());
        bricklayerLogDO.setUserAgent(getUserAgent());
        bricklayerLogDO.setUserName(getUserName());
        bricklayerLogDO.setRequestUri(getRequestUri());
        return bricklayerLogDO;

    }

    public BricklayerLogVO toBricklayerLogVO() {
        BricklayerLogVO bricklayerLogVO = new BricklayerLogVO();
        bricklayerLogVO.setId(getId());
        bricklayerLogVO.setCreateBy(getCreateBy());
        bricklayerLogVO.setUpdateBy(getUpdateBy());
        bricklayerLogVO.setCreateDate(getCreateDate());
        bricklayerLogVO.setCountValue(getCountValue());
        bricklayerLogVO.setUpdateDate(getUpdateDate());
        bricklayerLogVO.setIpAddress(getIpAddress());
        bricklayerLogVO.setUserAgent(getUserAgent());
        bricklayerLogVO.setUserName(getUserName());
        bricklayerLogVO.setRequestUri(getRequestUri());
        return bricklayerLogVO;

    }


    public static List<BricklayerLogVO> toBricklayerLogVOList(List<BricklayerLogDTO> bricklayerLogDTOList) {
        List<BricklayerLogVO> collect = bricklayerLogDTOList.stream().map(x -> x.toBricklayerLogVO()).collect(Collectors.toList());
        return collect;
    }

    public static List<BricklayerLogDO> toBricklayerLogDOList(List<BricklayerLogDTO> bricklayerLogDtOList) {
        List<BricklayerLogDO> collect = bricklayerLogDtOList.stream().map(x -> x.toBricklayerLogDO()).collect(Collectors.toList());
        return collect;
    }

}