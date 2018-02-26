package cn.rfatx.app.util.card;

import cn.rfatx.app.util.card.exception.EncryptorException;
import cn.rfatx.app.util.card.exception.ErrorCode;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by T5-SK on 2017/9/15.
 */
public class FinanceHsmEngine {
    private static String HSM_IP = "119.23.236.5";  // 单长度DES密钥
    private static int HSM_PROT = 8000;
    private static boolean calcLengthMod =true;//计算长度模式，长度按字节计算
    private static boolean padZeroMod=false;
    private static String zero="000000000000000000000";
    private static String TPKIndex="K0999";

    HsmSocket hsmSock = null;
    public FinanceHsmEngine(String ip, int port) {
        hsmSock = new HsmSocket(ip, port);
    }

    private static String leftPadZero(int length, String data,Boolean Mod) throws EncryptorException {
        if (Mod==calcLengthMod) {
            int realLength = (data.length() + "").length();
            if (length < realLength) throw (new EncryptorException(ErrorCode.DataIsTooLong));
            if ((data.length() & 0x1) == 1) throw (new EncryptorException(ErrorCode.dataLengthIsNotEven));
            else return (zero.substring(0, length - realLength) + data.length() / 2);
        }
        else {//padZeroMod
            if (length<data.length()) throw (new EncryptorException(ErrorCode.DataIsTooLong));
            return zero.substring(0,length-data.length())+data;
        }
    }


    /**
     * 为了支持16进制的输入，格式化输入的命令字符串
     * @param command  组装好的命令
     * @return
     */
    public byte[] formateCommand(String command) throws EncryptorException {
        command=command.toUpperCase();
        System.out.println(command);
        String[] commandParts=command.split("\\&|\\!");
        int commandLength=0;
        for (int i=0;i<commandParts.length;i++){
            if ((i&0x1)==1) {
                if ((commandParts[i].length()&0x1)==1) throw (new EncryptorException(ErrorCode.dataLengthIsNotEven));
                else commandLength+=commandParts[i].length()/2;
            }
            else commandLength+=commandParts[i].length();
        }
        byte[] cmdbuf=new byte[commandLength];
        int indicator=0;
        for (int i=0;i<commandParts.length;i++){
            String commandPart=commandParts[i];
            if ((i&0x1)==1){
                for(int j=0;j<commandPart.length()/2;j++){
                    cmdbuf[indicator++]= (byte) Integer.parseInt(commandPart.substring(j<<1,(j<<1)+2),16);
                }
            }
            else {
                for(byte ls:commandPart.getBytes()){
                    cmdbuf[indicator++]=ls;
                }
            }
        }
//        for (int i=0;i<cmdbuf.length;i++){
//            if ((cmdbuf[i]&0xff)<0xa) System.out.print("0");
//            System.out.print(Integer.toHexString(cmdbuf[i]&0xff)+" ");
//        }
        byte[] rspbuf = hsmSock.CommunicateHsm(cmdbuf);
        if (rspbuf == null ) {
            throw new EncryptorException(ErrorCode.ConnectTimeOut,"加密机[IP：" + HSM_IP + "， PORT：" + HSM_PROT + "]连接失败！");
        }
        if (rspbuf[2]!=0x30||rspbuf[3]!=0x30) throw (new EncryptorException(ErrorCode.EncryptorError,"加密机错误，错误码为"+new String(rspbuf)));
        return rspbuf;
    }

    /**
     * 产生制卡用密钥
     * @param bean
     * @return
     * @throws EncryptorException
     */
    public String getICCKey(HsmBean bean) throws EncryptorException {
        StringBuffer sb = new StringBuffer();
        sb.append("G2");
        sb.append("00");
        sb.append(TPKIndex);
        sb.append(bean.getKMCId().substring(8,12));
        sb.append(bean.getCSN());
        sb.append(bean.getSCP02());
        sb.append("00");
        sb.append("0"+bean.getTime());//分散次数
        //如果分散两次,先用城市代码做一次分散
        if(bean.getTime().equals("2")){
            sb.append(bean.getCityCode()+"FF0000000000");sb.append(CommonMethod.xor(bean.getCityCode()+"FF0000000000","FFFFFFFFFFFFFFFF"));
        }
        sb.append(bean.getCardNo());
        sb.append(CommonMethod.xor(bean.getCardNo(),"FFFFFFFFFFFFFFFF"));
        String[] keys=bean.getKeyType().split(",");
        int num=keys.length;
        sb.append(StringUtils.leftPad(num+"",2,'0'));
        for (String key:keys){
            sb.append("109");
            sb.append(key);
        }
        sb.append(";");
        System.out.println(sb.toString());
//        return("test");
        byte[] response=this.formateCommand(sb.toString());
        return new String(response).substring(8,8+32*num);
    }


    public static Object socketclien(String HSM_IP, int HSM_PROT, HsmBean hsmBean) throws EncryptorException {
        FinanceHsmEngine chsm = new FinanceHsmEngine(HSM_IP, HSM_PROT);
        String message = "";
        if ("G2".equals(hsmBean.getMessgeType())) {//生成制卡用密钥
            message = chsm.getICCKey(hsmBean);
        }
        return message;
    }


    public static Map creatCard(String cardId, String host, int port, String tPKIndex, String KMCId,String CSN,String SCP02) throws EncryptorException {
        HSM_IP = host;  // 单长度DES密钥
        HSM_PROT = port;
        TPKIndex = tPKIndex;
        HsmBean hsmBean = new HsmBean();
        hsmBean.setMessgeType("G2");
        hsmBean.setCardNo(cardId);
        hsmBean.setKMCId(KMCId);//个人化主密钥标识符
        hsmBean.setCSN(CSN);//芯片序号
        hsmBean.setSCP02(SCP02);//卡序列计数器
        hsmBean.setCityCode("0755");
        hsmBean.setTime("1");
        hsmBean.setKeyType("K0001,K0002,K0003,K0004,K0005");
        //组装制卡文件
        String keys1_5=(String)socketclien(HSM_IP, HSM_PROT, hsmBean);
        hsmBean.setKeyType("K0007,K0008,K0009");
        String keys7_9=(String)socketclien(HSM_IP, HSM_PROT, hsmBean);
        hsmBean.setKeyType("K0012,k0013,k0014");
        String keys12_14=(String)socketclien(HSM_IP, HSM_PROT, hsmBean);

        hsmBean.setTime("2");
        hsmBean.setKeyType("K0006,K0010,K0011");
        String keysTwice=(String)socketclien(HSM_IP, HSM_PROT, hsmBean);

        String keys=keys1_5+keysTwice.substring(0,32)+keys7_9+keysTwice.substring(32)+keys12_14;

        Map creatCardMap=new HashMap();
        creatCardMap.put("CardNo",hsmBean.getCardNo());
        creatCardMap.put("Keys",keys);
        creatCardMap.put("CSN",CSN);
//        JSONObject jsonObject = JSONObject.fromObject(creatCardMap);
        return(creatCardMap);
    }

//    public static void main(String... args) throws EncryptorException {
//        String cardId="66660755000000";
//        String KMCId="112233445566";
//        String LeftCSN="00000000";
//        String SCP02="0001";
//        int total=20;
//        Map map=new HashMap();
////        map.put("CSN",CSN);
//        map.put("SCP02",SCP02);
//        map.put("KMCId",KMCId);
//        map.put("Total",total);
//        for (int i=0;i<20;i++){
//            int length=(i+"").length();
//            String CSN=LeftCSN.substring(length)+i;
//            map.put("card"+(i+1),creatCard(cardId+ StringUtils.leftPad((i+1)+"",2,'0'),KMCId,CSN,SCP02));
//        }
//        JSONObject jsonObject = JSONObject.fromObject(map);
//        System.out.println(jsonObject.toString());
//    }
}
