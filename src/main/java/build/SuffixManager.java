package build;

public class SuffixManager {
    private String before = "com";
    private String dOSuffix = "DO";
    private String dTOSuffix = "DTO";
    private String vOSuffix = "VO";
    private String serviceISuffix = "ServiceI";
    private String serviceImplSuffix = "ServiceImpl";
    private String daoSuffix = "Dao";
    private String controllerSuffix = "Controller";


    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getdOSuffix() {
        return dOSuffix;
    }

    public void setdOSuffix(String dOSuffix) {
        this.dOSuffix = dOSuffix;
    }

    public String getdTOSuffix() {
        return dTOSuffix;
    }

    public void setdTOSuffix(String dTOSuffix) {
        this.dTOSuffix = dTOSuffix;
    }

    public String getvOSuffix() {
        return vOSuffix;
    }

    public void setvOSuffix(String vOSuffix) {
        this.vOSuffix = vOSuffix;
    }

    public String getServiceISuffix() {
        return serviceISuffix;
    }

    public void setServiceISuffix(String serviceISuffix) {
        this.serviceISuffix = serviceISuffix;
    }

    public String getServiceImplSuffix() {
        return serviceImplSuffix;
    }

    public void setServiceImplSuffix(String serviceImplSuffix) {
        this.serviceImplSuffix = serviceImplSuffix;
    }

    public String getDaoSuffix() {
        return daoSuffix;
    }

    public void setDaoSuffix(String daoSuffix) {
        this.daoSuffix = daoSuffix;
    }

    public String getControllerSuffix() {
        return controllerSuffix;
    }

    public void setControllerSuffix(String controllerSuffix) {
        this.controllerSuffix = controllerSuffix;
    }
}
