 package cn.rfatx.api;
 
 import java.io.Serializable;

 /**
  *传递扩展插件
  */
 public class Setting implements Serializable {
   public static final String INVALID_CLIENT_DESCRIPTION = "xSimple";
   public static final String LINE_SEPEAC = "#¤∮ΘΨ".toUpperCase() + "";
   
   public static final String PAGE_SIZE = "10";
   
   private static final long serialVersionUID = 8014468696683553229L;
   
   public static final String DELETE = "del";
   
   public static final String UPDATE = "update";
   
   public static final String ADD = "add";
   
   public static final String SUCCESSSTAT = "200";
   
   public static final String FAIURESTAT = "500";
   
   public static final String K1 = "K1";
   
   public static final String K2 = "K2";
   
   public static final String RESULTCODE = "resCode";
   
   public static final String MESSAGE = "msg";
   
   public static final String UPPERANDROIDPHONE = "ANDROID";
   
   public static final String IOSPHONE = "IOS";
   
   public static final String IPHONE = "IPHONE";
   
   public static final String INWORK = "在职";
   
   public static final String NOTWORK = "离职";
   
   public static final String BASEADDRESS = "/uf";
   
   public static final String BIIMGMESSAGE = "/uf/bi";
   
   public static final String appViewsUrl = "/jd";
   
   public static final String appDownLoadUrl = "/WEB-INF/views/jd";
   
   public static final String qrcodeimg = "/uf/qrcodeimg";
   
   public static final String downloadExcel = "/download";
   public static final String excelAddress = "/excel";
   public static final String CLEARCLIENT = "clearClient";
   public static final String CLEARDEVICE = "clearDevice";
   public static final String UPGRADE = "upgrade_";
   public static final String PHONEOFFLINE = "OFFLINE";
   public static final String ANDROIDOFFLINE = "dwline";
   public static final String USERIMAGE = "/uf/employee/photo/";
   public static final int HASH_INTERATIONS = 1024;
   public static final int SALT_SIZE = 8;
   public static final String PASSWORD = "123456";
   public static final String MEMBER = "member";
   public static final String cardTaskFileDir = "/var/temp";

   public static final String DEFAULT_DOMAIN = "51xch";

   // 超级运营商
   public static final String SUPER_DOMAIN = "super";

   public static enum OnlineType
   {
     online("1"),
     off("0");
     
     private OnlineType(String value) {
       this.value = value;
     }
     
     public String getValue() { return this.value; }
     
     private String value;
   }
   
   public static enum WorkStatusType
   {
     usable("可用"),  purchased("已购买"),  prototype("原型"),  introduce("介绍"),  common("公用"),  unusable("不可用"),  publicPackage("公共包"),  test("测试"),  company("企业"),  proterotype("原生态");
     
     private WorkStatusType(String s_type) {
       this.s_type = s_type;
     }
     
     private final String s_type;
     public String getTypeName()
     {
       return this.s_type;
     }
   }
   
 
   public static enum DeleteStatusType
   {
     device("设备擦除"),  account("账号擦除"),  nodelete("正常");
     
     private DeleteStatusType(String s_type) {
       this.s_type = s_type;
     }
     
     private final String s_type;
     public String getTypeName()
     {
       return this.s_type;
     }
   }
   
 
   public static enum AccountType
   {
     QQ("QQ"),  EMAIL("邮箱"),  MOBILE("手机");
     
     private AccountType(String stype) {
       this.stype = stype;
     }
     
     private final String stype;
     public String getTypeName()
     {
       return this.stype;
     }
   }
   
 
   public static enum ApplicationJumpType
   {
     TOAPP("应用"),  TOTYPE("类别");
     
     private ApplicationJumpType(String sype) {
       this.sype = sype;
     }
     
     private final String sype;
     public String getTypeName()
     {
       return this.sype;
     }
   }
   
 
 
   public static enum CategoryNameType
   {
     移动事业部("A"),  盈诺德内部("B"),  外部("C");
     
     private CategoryNameType(String s_type) {
       this.type = s_type;
     }
     
     private final String type;
     public String getTypeName()
     {
       return this.type;
     }
   }
   
 
   public static enum CommonStatusType
   {
     YES("yes"),  NO("no");
     
     private CommonStatusType(String sype) {
       this.sype = sype;
     }
     
     private final String sype;
     public String getTypeName()
     {
       return this.sype;
     }
   }
   
 
   public static enum ActionStatusType
   {
     RATING("打分"),  COMMENT("评论"),  ALL("评论和打分");
     
     private ActionStatusType(String sype) {
       this.sype = sype;
     }
     
     private final String sype;
     public String getTypeName()
     {
       return this.sype;
     }
   }
   
 
   public static enum PlatformType
   {
     IPHONE("苹果"),  ANDROID("安卓");
     
     private PlatformType(String s_type) {
       this.s_type = s_type;
     }
     
     private final String s_type;
     public String getTypeName()
     {
       return this.s_type;
     }
   }
   
 
   public static enum SendStatus
   {
     send("已发送"),  receive("已接受"),  outline("不在线"),  fail("发送失败");
     
     private SendStatus(String s_type) {
       this.s_type = s_type;
     }
     
     private final String s_type;
     public String getTypeName()
     {
       return this.s_type;
     }
   }
   
 
   public static enum SendType
   {
     CONTENT("文字"),  IMAGE("图片"),  FILE("文件"),  AVI("录音"),  PLACE("位置"),  ADDPERSON("增加人员"),  REMOVEPERSON("移除人员"),  EXITPERSON("自动退出"),  LOOKEXP("表情");
     
     private SendType(String s_type) {
       this.s_type = s_type;
     }
     
 
     private final String s_type;
     
     public String getTypeName() { return this.s_type; }
   }
   
   public static enum TopicType {
     CUSTOMER("意见反馈跟客服的聊天话题"),  COMMONTOPIC("正常聊天的话题");
     
     private TopicType(String s_type) {
       this.s_type = s_type;
     }
     
     private final String s_type;
     public String getTypeName()
     {
       return this.s_type;
     }
   }
   
 
   public static enum TreeLeafEnum
   {
     LEAFNODE("叶子节点"),  NONLEAFNODE("非叶子节点");
     
     private TreeLeafEnum(String s_type) {
       this.s_type = s_type;
     }
     
     private final String s_type;
     public String getTypeName()
     {
       return this.s_type;
     }
   }
   
 
   public static enum UserPerview
   {
     SUPERADMIN("超级管理员"),  COMMONADMIN("普通管理员");
     
     private UserPerview(String s_type) {
       this.s_type = s_type;
     }
     
     private final String s_type;
     public String getTypeName()
     {
       return this.s_type;
     }
   }
   
 
   public static enum ChannelStatusType
   {
     AVAILABLE("可用"),  DISABLE("禁用");
     
     private ChannelStatusType(String sype) {
       this.sype = sype;
     }
     
     private final String sype;
     public String getTypeName()
     {
       return this.sype;
     }
   }
   
 
   public static enum ChannelType
   {
     BATTERY("电池"),  AC("交流电");
     
     private ChannelType(String sype) {
       this.sype = sype;
     }
     
     private final String sype;
     public String getTypeName()
     {
       return this.sype;
     }
   }
   
 
   public static enum ChanStatusType
   {
     others("其他"),  appstore("APPSTORE"),  qrcodeImage("二维码"),  weixin("微信");
     
     private ChanStatusType(String u_type) {
       this.u_type = u_type;
     }
     
     private final String u_type;
     public String getTypeName()
     {
       return this.u_type;
     }
   }
   
 
   public static enum LikeStatusType
   {
     interesting("感兴趣"),  good("赞一下"),  nothing("未进行操作"),  download("已下载"),  codeNum("验证码");
     
     private LikeStatusType(String u_type) {
       this.u_type = u_type;
     }
     
 
     public String getTypeName()
     {
       return this.u_type;
     }
     
 
     private final String u_type;
   }
   
   public static enum PlateformType
   {
     IOS("IOS"),  ANDROID("ANDROID");
     
     private PlateformType(String m_type) {
       this.m_type = m_type;
     }
     
     private final String m_type;
     public String getM_type()
     {
       return this.m_type; }
   }
   
   public static enum ThirdSystemType {
     assistSystem("辅系统"),  mainSystem("主系统");
     
     private ThirdSystemType(String u_type) {
       this.type = u_type;
     }
     
     private final String type;
     public String getTypeName()
     {
       return this.type; }
   }
   
   public static enum VersionType {
     ANDROID("Android_手机版"),  ANDROID_PAD("Android_平板"),  IPHONE("IPHONE"),  IPAD("IPAD");
     
     private VersionType(String m_type) {
       this.m_type = m_type;
     }
     
     private final String m_type;
     public String getM_type()
     {
       return this.m_type;
     }
   }
   
 
   public static enum SexType
   {
     man("男性"),  woman("女性"),  unkown("未知");
     
     private final String s_type;
     
     private SexType(String s_type) { this.s_type = s_type; }
     
 
 
 
 
     public String getTypeName() { return this.s_type; }
   }
   
   public static enum CommentAuthType {
     anyone,  authorization,  none;
     
     private CommentAuthType() {}
   }
   
   public static enum OptType {
     NEWS,  APPROVAL,  NOTICE;
     
     private OptType() {}
   }
   
   public static enum MessageType {
     systemmes("系统消息"),  intermes("接口推送消息"),  clearuser("清除用户"),  cleardevice("清除设备"),  onlineupdate("在线更新"),  pilefault("桩故障消息推送");
     
     private final String m_type;
     private MessageType(String m_type) { this.m_type = m_type; }
     
     public String getM_type() {
       return this.m_type;
     }
   }
   
 
   public static enum MsgState
   {
     nosend("未发送"),  send("已发送"),  received("已接收");
     
     private final String m_state;
     private MsgState(String m_state) { this.m_state = m_state; }
     
     public String getM_state() {
       return this.m_state;
     }
   }
   
 
 
   public static enum FeedProblenType
   {
     problem("问题"),  answer("答案");
     
     private final String m_state;
     private FeedProblenType(String m_state) { this.m_state = m_state; }
     
     public String getM_state() {
       return this.m_state;
     }
   }
   
 
   public static enum ActiveType
   {
     ENABLE("启用"),  DISABLE("禁用");
     
     private final String s_type;
     private ActiveType(String s_type) { this.s_type = s_type; }
     
 
     public String getTypeName() { return this.s_type; }
   }
   
   public static enum StationType {
     ENABLE("在职"),  DISABLE("离职");
     
     private final String s_type;
     private StationType(String s_type) { this.s_type = s_type; }
     
     public String getTypeName() {
       return this.s_type;
     }
   }
   
 
   public static enum Xtype
   {
     UPGRADE("版本更新"),  EBS("EBS推送信息"),  OFFLINE("强制下线");
     
     private final String xtype;
     private Xtype(String xtype) { this.xtype = xtype; }
     
     public String getTypeName() {
       return this.xtype;
     }
   }

   public static enum EquRunningStatus
   {
     // 停用, 使用中, 故障
     STOP("0"),  RUNNING("1"),  BREAKDOWN("2");

     private final String oquRunningStatus;
     private EquRunningStatus(String oquRunningStatus) { this.oquRunningStatus = oquRunningStatus; }

     public String getTypeName() {
       return this.oquRunningStatus;
     }
   }

   public static enum OnlineStatus
   {
     // 离线, 在线
     OFFLINE("0"),  ONLINE("1");

     private final String onlineStatus;
     private OnlineStatus(String onlineStatus) { this.onlineStatus = onlineStatus; }

     public String getTypeName() {
       return this.onlineStatus;
     }
   }

   public static enum ChargingStatus
   {
     // 空闲, 在充电
     VACANT("0"),  CHARGING("1");

     private final String chargingStatus;
     private ChargingStatus(String chargingStatus) { this.chargingStatus = chargingStatus; }

     public String getTypeName() {
       return this.chargingStatus;
     }
   }


 }
