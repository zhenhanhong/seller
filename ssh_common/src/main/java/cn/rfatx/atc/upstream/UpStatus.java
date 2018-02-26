package cn.rfatx.atc.upstream;

import java.io.Serializable;

/**
 * 上行状态
 * @author huanganding@coracle.com (AnDing Huang)
 */
public class UpStatus implements Serializable{
    private Boolean status;
    private Integer errorCode;
    private String message;
    private Object info;
    private boolean isOldPile;

    public Boolean getStatus(){
        return status;
    }
    public void setStatus(Boolean status){
        this.status=status;
    }
    public Integer getErrorCode(){
        return errorCode;
    }
    public void setErrorCode(Integer errorCode){
        this.errorCode=errorCode;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public Object getInfo(){
        return info;
    }
    public void setInfo(Object info){
        this.info=info;
    }
    public UpStatus(){}
    public UpStatus(String message){
        this.errorCode=999;
        this.status=false;
        this.message=message;
    }

    public boolean isOldPile() {
        return isOldPile;
    }

    public void setOldPile(boolean oldPile) {
        isOldPile = oldPile;
    }
}
