package cn.rfatx.atc.util.gdev;

public class GDEVConfig {


	public final static int PageNo = 1;//默认从第一页开始
	public final static int PageSize = 10;//默认每页查询10
	public final static Long tokenAvailableTime = 7200L;//token默认有效时间 2小时
	public final static String SIGN_KEY = "1234567890abcdef";//MD5密钥
	public final static String OPERATOR_SECRET = "1234567890abcdef"; //
	public final static String AES_KEY = "1234567890abcdef"; //AES密钥
	public final static String AES_IV = "1234567890abcdef";//AES向量
	public final static String OPERATOR_ID = "123456789"; //9位组织机构代码
	// open平台地址
	public final static String URL = "http://haiyisoft/evcs/v1/";
	 
}
