package cn.rfatx.app.util.card;

import java.io.Serializable;

public class HsmBean implements Serializable{
	
	/**
	 * 加密机类
	 */
	private static final long serialVersionUID = -7002185668251279711L;
	
	private String messgeType;
	private String tac;
	private String mac1;
	private String mac2;
	private String termNo;//终端编号
	private String cityCode;//城市代码
	private String transNo;//交易码
	private String csn;
	private String version;//版本
	private String rand;//随机数
	private String keyindex;//主键
	private String cardNo;//卡号
	private String time;//分散次数
	private String initValue;//初始向量
	private String transAmt;//交易金额
	private String transType;//交易类型
	private String transNum;//交易序号
	private String transDate;//交易日期
	private String transTime;//交易时间
	private String transData;//交易数据
	private String keyType;//分散导出的密钥类型
	private String ikVer;//分散导出的密钥版本号
	private String datagram;//EB指令算mac的数据     交通部中hasn的数据使用此字段

//<----------------------------以下为为了适应交通部加密机新家字段--------------------------------->

	private String rsaKeyIndex;//交通部加密机专用，密钥索引（0-20）
	private String keyLength;//交通部加密机专用，产生的RSA密钥长度(1024-2048)

	private String iv_Mac;//计算mac的时候初始向量

	private String pubKeyIndex;//CA的公钥

	private String ARQC;//用作校验ARQC
	private String ATC;//应用交易计数器
//<----------------------------以下为为了适应金融加密机新家字段--------------------------------->
	private String KMCId;//个人化主密钥标识符
	private String CSN;//芯片序号
	private String SCP02;//卡序列计数器

	public String getKMCId() {
		return KMCId;
	}

	public void setKMCId(String KMCId) {
		this.KMCId = KMCId;
	}

	public String getCSN() {
		return CSN;
	}

	public void setCSN(String CSN) {
		this.CSN = CSN;
	}

	public String getSCP02() {
		return SCP02;
	}

	public void setSCP02(String SCP02) {
		this.SCP02 = SCP02;
	}

	public String getATC() {
		return ATC;
	}
	public void setATC(String ATC) {
		this.ATC = ATC;
	}
	public String getARQC() {
		return ARQC;
	}
	public void setARQC(String ARQC) {
		this.ARQC = ARQC;
	}
	public String getPubKeyIndex() {
		return pubKeyIndex;
	}
	public void setPubKeyIndex(String pubKeyIndex) {
		this.pubKeyIndex = pubKeyIndex;
	}
	public String getIv_Mac() {
		return iv_Mac;
	}
	public void setIv_Mac(String iv_Mac) {
		this.iv_Mac = iv_Mac;
	}
	public String getMessgeType() {
		return messgeType;
	}
	public void setMessgeType(String messgeType) {
		this.messgeType = messgeType;
	}
	public String getTransData() {
		return transData;
	}
	public void setTransData(String transData) {
		this.transData = transData;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransNum() {
		return transNum;
	}
	public void setTransNum(String transNum) {
		this.transNum = transNum;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getInitValue() {
		return initValue;
	}
	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}
	public String getMac1() {
		return mac1;
	}
	public void setMac1(String mac1) {
		this.mac1 = mac1;
	}
	public String getMac2() {
		return mac2;
	}
	public void setMac2(String mac2) {
		this.mac2 = mac2;
	}
	public String getTermNo() {
		return termNo;
	}
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getCsn() {
		return csn;
	}
	public void setCsn(String csn) {
		this.csn = csn;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
	public String getKeyindex() {
		return keyindex;
	}
	public void setKeyindex(String keyindex) {
		this.keyindex = keyindex;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getIkVer() {
		return ikVer;
	}
	public void setIkVer(String ikVer) {
		this.ikVer = ikVer;
	}
	public String getDatagram() {
		return datagram;
	}
	public void setDatagram(String datagram) {
		this.datagram = datagram;
	}
	public String getRsaKeyIndex() {
		return rsaKeyIndex;
	}
	public void setRsaKeyIndex(String rsaKeyIndex) {
		this.rsaKeyIndex = rsaKeyIndex;
	}
	public String getKeyLength() {
		return keyLength;
	}
	public void setKeyLength(String keyLength) {
		this.keyLength = keyLength;
	}
}
