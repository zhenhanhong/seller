package cn.rfatx.app.util.card.exception;
/**
 *
 * errorcode  -1:  loss some information
 * errorcode  -2:  data length is not even
 * errocrode  -3:  connect time out
 * Created by T5-SK on 2017/3/9.
 */
public class EncryptorException extends Exception {
    public int errorCode;
    public String Msg;

    public EncryptorException(int errorCode, String Msg){
        this.errorCode=errorCode;
        this.Msg=Msg;
    }
    public EncryptorException(int errorCode){
        this.errorCode=errorCode;
        switch (errorCode) {
            case ErrorCode.LossSomeImportantInfomation:
                Msg="生成制卡文件时缺少一些重要数据";
                break;
            case ErrorCode.dataLengthIsNotEven:
                Msg="16进制数据长度为奇数，不满足要求";
                break;
            case ErrorCode.ConnectTimeOut:
                Msg="加密机链接超时";
                break;
            case ErrorCode.DataIsTooLong:
                Msg="输入的数据太长，超过加密机限制";
                break;
            case ErrorCode.RSAKeyLengthIllegal:
                Msg="RSA密钥长度不合法";
                break;
            case ErrorCode.RSAKeyIndexIllegal:
                Msg="RSA密钥索引不合法";
                break;
            default:
                Msg="发生了一些预料之外的错误";
                break;
        }
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage(){
        return Msg;
    }
}
