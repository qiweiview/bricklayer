package cn.anicert.utils;


import cn.anicert.model.dto.BricklayerLogDTO;
import cn.anicert.serviceI.BricklayerLogServiceI;

public class LogCenter {
    private ThreadQueue threadQueue = new ThreadQueue();


    /**
     * 保存请求日志
     *
     * @param bricklayerLogDTO
     */
    public void requestLog(BricklayerLogDTO bricklayerLogDTO) {
        BricklayerLogServiceI bricklayerLogServiceI = SpringContextHolder.getBean(BricklayerLogServiceI.class);
        threadQueue.submit(() -> {
            bricklayerLogServiceI.saveBricklayerLog(bricklayerLogDTO);
        });
    }
}
