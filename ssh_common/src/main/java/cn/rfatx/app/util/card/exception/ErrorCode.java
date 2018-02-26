package cn.rfatx.app.util.card.exception;

/**
 * Created by T5-SK on 2017/3/10.
 */
public class ErrorCode {
    /**
     * 拼装dgi时丢失一些重要信息
     */
    public final static int LossSomeImportantInfomation=-1;
    /**
     * 数据长度为奇数
     */
    public final static int dataLengthIsNotEven=-2;
    /**
     * 连接加密机时超时
     */
    public final static int ConnectTimeOut=-3;
    /**
     * 输入的数据超长
     */
    public final static int DataIsTooLong=-4;
    /**
     * RSA密钥长度不合法
     */
    public final static int RSAKeyLengthIllegal=-5;
    /**
     * RSA密钥索引不合法
     */
    public static final int RSAKeyIndexIllegal=-6;

    /**
     * 缺少该索引的交通部公钥
     */
    public static final int NotHaveCAPubKey=-7;
    /**
     * 加密机报错
     */
    public static final int EncryptorError=-50 ;
}
