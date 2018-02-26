/******************************************************************************
\ * Copyright (C) 2010-2016 Yantai HaiYi Software Co.,Ltd
 * All Rights Reserved.
 * 本软件为烟台海颐软件开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package cn.rfatx.atc.util.gdev;


/**
 * 系统常量类
 * Constant.java
 * 
 */  
public class GDEVConstant {

	private GDEVConstant() {
		
	}

	public final static String OperatorID = "operatorID";
	public final static String Data = "data";
	public final static String TimeStamp = "timeStamp";
	public final static String Seq = "seq";
	public final static String Sig = "sig";

	public final static String STATION_ID = "stationID";
	public final static String START_TIME="startTime";
	public final static String END_TIME="endTime";

	public final static String Ret="ret";
	public final static String Status="Status";
	public final static String Msg="msg";

	/**
	 * 请求结果编码
	 */
	public static final String RET_SUCCESS = "0";//成功
	public static final String RET_BUSY = "1";//此时系统方稍后重试
	public static final String RET_SIG_ERROR = "4001";//签名错误
	public static final String RET_TOKEN_ERROR = "4002";//TOKEN错误
	public static final String RET_POST_ERROR = "4003";// POST参数不法，缺少必须的示例
	public static final String RET_REQUEST_ERROR = "4004";//请求的业务参数不合法，各接口定义自己的必须参数
	public static final String RET_SYS_ERROR = "500";//系统错误
}
