package cn.rfatx.app.util.card;


public class HsmEngine{
    private static String HSM_IP = "10.7.253.243";  // 单长度DES密钥
    private static int HSM_PROT = 8018;
       
    private int MAX_INDEX = 2048;
    private int KEYLEN_SINGLE = 8;
    
    private String DES_SCHEME		= "Z";  // 单长度DES密钥
    private String DES2_SCHEME		= "X"; 	// 双长度DES密钥
    private String DES2V_SCHEME		= "U";  // 双长度变种加密DES密钥
    private String DES3_SCHEME		= "Y";  // 三长度长度DES密钥
    private String DES3V_SCHEME		= "T";  // 三长度长度变种加密DES密钥
    private String SM1_SCHEME		= "P"; 	// SM1密钥
    private String SM4_SCHEME		= "R"; 	// SM4密钥
    private String AES_SCHEME		= "L"; 	// AES密钥

//    HsmSocket hsmSock = null;
//
//	public HsmEngine(String ip, int port) {
//		hsmSock = new HsmSocket(ip, port);
//	}
	
	/**
	 * 在密码机中产生随机对称密钥，保存的指定位置，并使用密码机种LMK加密导出。
	 * 
	 * @param intTMKIndex
	 *         	  密钥索引，用于存储产生的随机密钥。范围【1-2048】
	 * @param intWorkKeyLen
	 *            密钥长度，产生对称密钥的密钥长度。范围【8 16 or 24】
	 * @param keytype
	 *            密钥类型，对称密钥的密钥类型。取值如下：
	 *            【000 – ZMK/KEK001 – ZPK
	 *				002 – PVK/TPK/TMK   402 – CVK
	 *				003 – TAK           008 – ZAK
	 *				009 – BDK           109 – MK-AC/MDK
	 *				209 – MK-SMI        309 – MK-SMC
	 *				409 – MK-DAK        509 – MK-DN
	 *				00A – ZEK/DEK       00B – TEK
	 *				10C – HMAC          011 – KMC】
	 * @param keyScheme 
	 * 			  密钥方案，取值如下：
	 * 			  【Z – 8字节DES密钥
	 *			    X/U – 16字节3DES密钥
	 *				Y/T – 24字节3DES密钥
	 *				P – 16字节SM1密钥
	 *				R – 16字节SMS4密钥
	 *				L – 16字节AES密钥】
	 * @param lable
	 * 			  密钥标签，保存对称密钥到密码机种使用，范围【1-9】
	 *
	 * @return LMK加密的对称密钥及密钥校验值。【KS+2N错误码+16H/1A+32H/1A+48H密钥密文+16H/32H密钥校验值】
	 * 		   当密钥方案为Z/X/U/T/Y时密钥校验值为16H，当密钥方案为R/P/L时密钥校验值为16H。
	 * @throws Exception
	 */
//    public String createWorkKey(int intTMKIndex, int intWorkKeyLen, String keytype, String keyScheme, String lable) throws Exception {
//        StringBuffer sb = new StringBuffer();
//
//        if ( keyScheme == null || lable == null || keytype == null || keytype.length() != 3 )
//           	throw new Exception("Paramter is error.");
//
//        if ( lable.length() > 16 )
//        	throw new Exception("Key label length invalid. it is must be 0-16.");
//
//        if ( MAX_INDEX < intTMKIndex || 1 > intTMKIndex )
//        	throw new Exception("Work index invalid. It is must be 1-2048");
//
//        if ( keyScheme.equals(DES_SCHEME) ) { // 单长度DES密钥方案，密钥长度必须为8.
//        	if ( intWorkKeyLen != KEYLEN_SINGLE )
//            	throw new Exception("Key length invalid. if keytype is Z it is must be 8.");
//        } else if ( keyScheme.equals(DES2_SCHEME) || keyScheme.equals(DES2V_SCHEME) ||
//        		keyScheme.equals(SM1_SCHEME) || keyScheme.equals(SM4_SCHEME) ||
//        		keyScheme.equals(AES_SCHEME) ) { // 双长度DES密钥方案， SM4、SM1、AESM密钥方案，密钥长度必须为16.
//        	if ( intWorkKeyLen != 2*KEYLEN_SINGLE )
//            	throw new Exception("Key length invalid. if keytype is X U R P L & it is must be 16.");
//
//        } else if ( keyScheme.equals(DES3_SCHEME) || keyScheme.equals(DES3V_SCHEME) ) {  // 三长度DES密钥方案， 密钥长度必须为24
//        	throw new Exception("Key length invalid. if keytype is Y T & it is must be 24.");
//        } else {
//        	throw new Exception("Key keytype invalid. it must be Z X U Y T R P L.");
//        }
//
//        // 封装发送报文
//        sb.append("KR");  						// [2A] 指令码
//        sb.append(keytype); 					// [3H] 密钥类型
//        sb.append(keyScheme); 					// [1A] 密钥方案
//        sb.append("K");							// [1A] 密钥索引标识
//        sb.append(format(intTMKIndex, 4)); 		// [4N] 索引
//        sb.append(format(lable.length(), 2)); 	// [2N] 标签长度
//        sb.append(lable);						// [0-16A] 密钥标签
//
//        byte[] cmdbuf = sb.toString().getBytes();
//
//        // 发送数据到密码机并接收返回结果
//        byte[] rspbuf = hsmSock.CommunicateHsm(cmdbuf);
//
//        return new String(rspbuf);
//    }

	/**
	 * 获取密码机版本信息。
	 * 
	 *
	 * @return 密码机版本信息。【ND+2N错误码+16H密码机主密钥LMK密钥校验值+24A的密码机版本号】
	 * 		   
	 * @throws Exception
	 */  
//    public String getHsm_version() throws Exception {
//        StringBuffer sb = new StringBuffer();
//
//        sb.append("5800204111223344556677880BBCF3FB02800000000000000000000000106000000010647001F20160825183728");	// 2N 指令码
//
//        byte[] cmdbuf = sb.toString().getBytes();
//
//        // 发送数据到密码机并接收
//        byte[] rspbuf = hsmSock.CommunicateHsm(cmdbuf);
//
//        return new String(rspbuf);
//    }

    private String format(int msglen, int formatLen) {
        String sFormatlen = "" + msglen;
        int iFormatlen = sFormatlen.length();
        for (int i = 0; i < formatLen - iFormatlen; i++) {
            sFormatlen = "0" + sFormatlen;
        }
        return sFormatlen;
    }

	public static void main(String[] args) {
		
//		HsmEngine hsm = new HsmEngine(HSM_IP, HSM_PROT);
		try {
			//String keyinfo = hsm.createWorkKey(1, 16, "000", "X", "1234");
			//System.out.println(keyinfo);
//			String version = hsm.getHsm_version();
//			System.out.println(version);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
