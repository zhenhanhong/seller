package cn.rfatx.atc.util;
/**
 * 数据字典的 item
 * Created by Young Lee
 */
public class DDSetting {
    //会员支付方式
    public static final String MEMBER_PAY_METHOD="MEMBER_PAY_METHOD";
    public static final String MEMBER_PAY_METHOD_ONLINE="MEMBER_PAY_METHOD_ONLINE";//在线(手机)
    public static final String MEMBER_PAY_METHOD_MAGCARD="MEMBER_PAY_METHOD_MAGCARD";//联机卡
    public static final String MEMBER_PAY_METHOD_EWALLET="MEMBER_PAY_METHOD_EWALLET";//电子钱包(离线卡)
    public static final String MEMBER_PAY_METHOD_GROUP="MEMBER_PAY_METHOD_GROUP";//集团支付
    public static final String MEMBER_PAY_METHOD_WECHAT="MEMBER_PAY_METHOD_WECHAT";//微信支付
    public static final String MEMBER_PAY_METHOD_XCHCARD_ONLINE="MEMBER_PAY_METHOD_XCHCARD_ONLINE";//自发卡-在线(手机)
    public static final String MEMBER_PAY_METHOD_XCHCARD_MAGCARD="MEMBER_PAY_METHOD_XCHCARD_MAGCARD";//自发卡-联机卡

    //会员账户类型
    public static final String MEMBER_PAY_TYPE="MEMBER_PAY_TYPE";
    public static final String SLSY="SLSY";//商联商用
    public static final String CHANGAN_CARD="CHANGAN_CARD";//联机卡：长安通
    public static final String XUNCHONG_CARD="XUNCHONG_CARD";//自发卡

    //事务处理编号
    public static final String MEMBER_ACCOUNT_PREFIX="ATC_";//会员前缀
    public static final String CHARGE_TRX_PREFIX="DGE_";//充电事务编码前缀
    public static final String PARKING_TRX_PREFIX="PRK_";//泊车事务编码前缀
    public static final String RESERVE_TRX_PREFIX="RSR_";//预约事务编码前缀
    public static final String BOOK_TRX_PREFIX="BOK_";//登记事务编码前缀
    //泊车位状态
    public static final String PARKING_STATUS="PARKING_STATUS";
    public static final String PARKING_PLANNING="PARKING_PLANNING";//规划中
    public static final String PARKING_BUILDING="PARKING_BUILDING";//在建
    public static final String PARKING_USING="PARKING_USING";//使用
    public static final String PARKING_CLOSE="PARKING_CLOSE";//关闭
    public static final String PARKING_CANCEL="PARKING_CANCEL";//撤销
    //付款条件
    public static final String PAYMENT_TERMS="PAYMENT_TERMS";
    public static final String SETTLEMENT_30="SETTLEMENT_30";//月结
    public static final String SETTLEMENT_45="SETTLEMENT_45";//45天结算
    public static final String SETTLEMENT_60="SETTLEMENT_60";//60天结算
    //班次类型
    public static final String SHIFT_TYPE="SHIFT_TYPE";
    public static final String MORNING="MORNING";//早班
    public static final String NOON="NOON";//中班
    public static final String AFTERNOON="AFTERNOON";//晚班
    //预约状态
    public static final String RESERVE_STATUS="RESERVE_STATUS";
    public static final String RESERVE_SUCCESS="RESERVE_SUCCESS";//预约成功
    public static final String RESERVE_FAILURE="RESERVE_FAILURE";//预约失败
    public static final String RESERVE_CANCELAUTO="RESERVE_CANCELAUTO";//自动取消
    public static final String RESERVE_CANCEL="RESERVE_CANCEL";//手动取消
    public static final String RESERVE_HANGUP="RESERVE_HANGUP";//挂起
    public static final String RESERVE_CHARGED="RESERVE_CHARGED";//已充电
    //登记预约状态
    public static final String BOOK_STATUS="BOOK_STATUS";
    public static final String BOOK_SUCCESS="BOOK_SUCCESS";//登记成功
    public static final String BOOK_FAILURE="BOOK_FAILURE";//登记失败
    public static final String BOOK_MSG_SEND="BOOK_MSG_SEND";//已发送预约消息
    public static final String BOOK_GIVE_UP="BOOK_GIVE_UP";//已放弃登记
    public static final String BOOK_HANGUP="BOOK_HANGUP";//挂起
    //登记渠道
    public static final String BOOK_FROM="BOOK_FROM";
    public static final String APP="APP";//手机APP
    public static final String PC="PC";//桌面PC
    //账单状态
    public static final String BILL_STATUS="BILL_STATUS";
    public static final String BILL_CLOSE="BILL_CLOSE";//已结算
    public static final String BILL_OPEN="BILL_OPEN";//未结束
    public static final String BILL_AFTER_PAY="BILL_AFTER_PAY";//补交
    //发票类型
    public static final String INVOICE_TYPE="INVOICE_TYPE";
    public static final String OF_THE_PUBLIC="OF_THE_PUBLIC";//对公
    public static final String PERSONAL="PERSONAL";//个人
    //消费类型
    public static final String BUSINESS_TYPE="BUSINESS_TYPE";
    public static final String BUSINESS_RESERVE="BUSINESS_RESERVE";//预约服务
    public static final String BUSINESS_CHARGE="BUSINESS_CHARGE";//充电服务
    public static final String BUSINESS_PARKING="BUSINESS_PARKING";//泊车服务
    //支付交易类型
    public static final String PAY_TRX_TYPE="PAY_TRX_TYPE";
    public static final String PRE_AUTHOR="PRE_AUTHOR";//预授权
    public static final String PRE_AUTHOR_DONE="PRE_AUTHOR_DONE";//预授权完成
    public static final String PRE_AUTHOR_CUT="PRE_AUTHOR_CUT";//预授权扣款
    //交易账户类型
    public static final String PAY_ACCOUNT_TYPE="PAY_ACCOUNT_TYPE";
    public static final String PAY_ACCOUNT_TYPE_ONLINE="PAY_ACCOUNT_TYPE_ONLINE";//联机
    public static final String PAY_ACCOUNT_TYPE_OFFLINE="PAY_ACCOUNT_TYPE_OFFLINE";//离线
    //咨讯内容分类
    public static final String INFORMATION_TYPE="INFORMATION_TYPE";
    public static final String AD="AD";//广告
    public static final String INTRODUCTION="INTRODUCTION";//业务介绍
    public static final String NEWS="NEWS";//新闻
    //常见问题分类
    public static final String QUESTION_TYPE="QUESTION_TYPE";
    public static final String QUESTION_PARKING="QUESTION_PARKING";//泊车服务
    public static final String QUESTION_RESERVE="QUESTION_RESERVE";//预约服务
    public static final String QUESTION_CHARGE="QUESTION_CHARGE";//充电服务
    //日历选项
    public static final String CALENDAR_OPTION="CALENDAR_OPTION";
    public static final String WORK_DAY="WORK_DAY";//工作日
    public static final String WEEK_END="WEEK_END";//周末
    public static final String SPECIAL="SPECIAL";//特殊假日
    //用户反馈分类
    public static final String FEEDBACK_TYPE="FEEDBACK_TYPE";
    public static final String FEEDBACK_PARKING="FEEDBACK_PARKING";//泊车服务
    public static final String FEEDBACK_RESERVE="FEEDBACK_RESERVE";//预约服务
    public static final String FEEDBACK_CHARGE="FEEDBACK_CHARGE";//充电服务
    //会员类型
    public static final String MEMBER_TYPE="MEMBER_TYPE";
    public static final String NORMAL="NORMAL";//普通会员
    public static final String GROUP="GROUP";//集团会员
    //充电模式
    public static final String CHARGE_MODEL="CHARGE_MODEL";
    public static final String CHARGE_MODEL_AUTO="CHARGE_MODEL_AUTO";//自动充满
    public static final String CHARGE_MODEL_COST="CHARGE_MODEL_COST";//按金额
    public static final String CHARGE_MODEL_KWH="CHARGE_MODEL_KWH";//按电量
    public static final String CHARGE_MODEL_TIME="CHARGE_MODEL_TIME";//按时长
    //充电状态
    public static final String CHARGE_STATUS="CHARGE_STATUS";
    public static final String CHARGE_PREPARE="CHARGE_PREPARE";//充电准备
    public static final String CHARGE_STARTING="CHARGE_STARTING";//充电启动指令已下发
    public static final String CHARGE_RUNNING="CHARGE_RUNNING";//运行中
    public static final String CHARGE_FULL="CHARGE_FULL";//充满
    public static final String CHARGE_FAULT="CHARGE_FAULT";//故障
    public static final String CHARGE_HANGUP="CHARGE_HANGUP";//挂起
    public static final String CHARGE_STOP="CHARGE_STOP";//停止
    public static final String CHARGE_CANCEL="CHARGE_CANCEL";//取消
    //充电控制
    public static final String CHARGE_ACTION="CHARGE_ACTION";
    public static final String CHARGE_ACTION_PREPARE="CHARGE_ACTION_PREPARE";//准备充电
    public static final String CHARGE_ACTION_SELECT="CHARGE_ACTION_SELECT";//选择交直流
    public static final String CHARGE_ACTION_START="CHARGE_ACTION_START";//充电启动
    public static final String CHARGE_ACTION_START_CONFIRM="CHARGE_ACTION_START_CONFIRM";//充电启动确认
    public static final String CHARGE_ACTION_STOP="CHARGE_ACTION_STOP";//充电停止
    //充电桩预约控制
    public static final String RESERVE_STATE="RESERVE_STATE";
    public static final String RESERVE_STATE_LOCK="RESERVE_STATE_LOCK";    //预约锁定
    public static final String RESERVE_STATE_UNLOCK="RESERVE_STATE_UNLOCK";  //预约解锁
    //设备启停控制
    public static final String PILES_ACTION="PILES_ACTION";
    public static final String PILES_ACTION_START="PILES_ACTION_START";//启动
    public static final String PILES_ACTION_STOP="PILES_ACTION_STOP";//停止
    //	车位锁锁住
    public static final String PARKING_ACTION="PARKING_ACTION";
    public static final String PARKING_ACTION_LOCK="PARKING_ACTION_LOCK";//锁定
    public static final String PARKING_ACTION_UNLOCK="PARKING_ACTION_UNLOCK";//解锁
    //计费模式收费因子
    public static final String TOLL_FACTOR="TOLL_FACTOR";
    public static final String TOLL_FACTOR_PROGRAM="TOLL_FACTOR_PROGRAM";//时段费用
    public static final String TOLL_FACTOR_SERVICE="TOLL_FACTOR_SERVICE";//服务费用
    public static final String TOLL_FACTOR_APPEND="TOLL_FACTOR_APPEND";//附加费用
    //计费因子类型
    public static final String TOLL_FACTOR_TYPE="TOLL_FACTOR_TYPE";
    public static final String TOLL_FACTOR_TYPE_PERIOD="TOLL_FACTOR_TYPE_PERIOD";//时段
    public static final String TOLL_FACTOR_TYPE_NORMAL="TOLL_FACTOR_TYPE_NORMAL";//普通
    //时段类型
    public static final String PROGRAM_FACTOR_TYPE="PROGRAM_FACTOR_TYPE";
    public static final String PROGRAM_FACTOR_TYPE_J="PROGRAM_FACTOR_TYPE_J";//尖时
    public static final String PROGRAM_FACTOR_TYPE_G="PROGRAM_FACTOR_TYPE_G";//谷时
    public static final String PROGRAM_FACTOR_TYPE_F="PROGRAM_FACTOR_TYPE_F";//峰时
    public static final String PROGRAM_FACTOR_TYPE_P="PROGRAM_FACTOR_TYPE_P";//平时
    //站点类型
    public static final String SITE_TYPE="SITE_TYPE";
//    public static final String SITE_TYPE_GENERAL="SITE_TYPE_GENERAL";//普通站点
//    public static final String SITE_TYPE_CENTRAL="SITE_TYPE_CENTRAL";//中心站点
//    public static final String SITE_TYPE_SUPER="SITE_TYPE_SUPER";//超级站点
//    public static final String SITE_TYPE_JOIN="SITE_TYPE_JOIN";//加盟站点
    public static final String SITE_TYPE_SPECIAL="SITE_TYPE_SPECIAL";//专用站
    public static final String SITE_TYPE_OPEN="SITE_TYPE_OPEN";//开放站
    public static final String SITE_TYPE_JOIN="SITE_TYPE_JOIN";//合作站
    //设备类型/电流类型
    public static final String PILES_TYPE="PILES_TYPE";
    public static final String PILES_TYPE_DAC="PILES_TYPE_DAC";//双交流
    public static final String PILES_TYPE_DDC="PILES_TYPE_DDC";//双直流
    public static final String PILES_TYPE_AC="PILES_TYPE_AC";//交流
    public static final String PILES_TYPE_DC="PILES_TYPE_DC";//直流
    public static final String PILES_TYPE_ADC="PILES_TYPE_ADC";//交直流
    //站点状态
    public static final String SITE_STATUS="SITE_STATUS";
    public static final String SITE_PLANNING="SITE_PLANNING";//规划中
    public static final String SITE_BUILDING="SITE_BUILDING";//在建
    public static final String SITE_USING="SITE_USING";//使用
    public static final String SITE_CLOSE="SITE_CLOSE";//关闭
    public static final String SITE_CANCEL="SITE_CANCEL";//撤销
    //充电堆状态
    public static final String STACK_STATUS="STACK_STATUS";
    public static final String STACK_PLANNING="STACK_PLANNING";//规划中
    public static final String STACK_BUILDING="STACK_BUILDING";//在建
    public static final String STACK_USING="STACK_USING";//使用
    public static final String STACK_CLOSE="STACK_CLOSE";//关闭
    public static final String STACK_CANCEL="STACK_CANCEL";//撤销
    //设备状态
    public static final String PILES_STATUS="PILES_STATUS";
    public static final String PILES_PLANNING="PILES_PLANNING";//规划中
    public static final String PILES_BUILDING="PILES_BUILDING";//在建
    public static final String PILES_USING="PILES_USING";//使用
    public static final String PILES_CLOSE="PILES_CLOSE";//关闭
    public static final String PILES_CANCEL="PILES_CANCEL";//撤销
    //充电模块
    public static final String PILES_CHARGE_PORTS="PILES_CHARGE_PORTS";
    public static final String ONE_PORT="ONE_PORT";//1个充电枪
    public static final String TWO_PORT="TWO_PORT";//2个充电枪
    //设备工作状态
    public static final String PILES_WORK_STATE="PILES_WORK_STATE";
    public static final String PILES_WORK_STATE_INACTIVE="PILES_WORK_STATE_INACTIVE"; //非活动的，指充电桩与服务长时间没有报文通信
//    public static final String PILES_WORK_STATE_MANAGE="10"; //管理  10
//    public static final String PILES_WORK_STATE_FREE="20";     //空闲  20
//    public static final String PILES_WORK_STATE_BREAK="30";   //故障  30
//    public static final String PILES_WORK_STATE_INIT="40";     //初始化 40
//    public static final String PILES_WORK_STATE_ORDER="80";   //预约中 80
//    public static final String PILES_WORK_STATE_DISABLED="90";   //不可用或占用 90
//    public static final String PILES_WORK_STATE_ONE_CHARGING="50"; //1号充电位充电中  50
//    public static final String PILES_WORK_STATE_TWO_CHARGING="51"; //2号充电位充电中  51
//    public static final String PILES_WORK_STATE_ALL_CHARGING="53"; //2个充电位充电中  53
//    public static final String PILES_WORK_STATE_STOP="60";        //设备停用  60
//    public static final String PILES_WORK_STATE_CHARGE_STOP="70";   //充电停止 70

    public static final String PILES_WORK_STATE_MANAGE="PILES_WORK_STATE_MANAGE"; //管理  10
    public static final String PILES_WORK_STATE_FREE="PILES_WORK_STATE_FREE";     //空闲  20
    public static final String PILES_WORK_STATE_BREAK="PILES_WORK_STATE_BREAK";   //故障  30
    public static final String PILES_WORK_STATE_INIT="PILES_WORK_STATE_INIT";     //初始化 40
    public static final String PILES_WORK_STATE_ORDER="PILES_WORK_STATE_ORDER";   //预约中 80
    public static final String PILES_WORK_STATE_DISABLED="PILES_WORK_STATE_DISABLED";   //不可用或占用 90
    public static final String PILES_WORK_STATE_ONE_CHARGING="PILES_WORK_STATE_ONE_CHARGING"; //1号充电位充电中  50
    public static final String PILES_WORK_STATE_ONE_DISCHARGING="PILES_WORK_STATE_ONE_DISCHARGING";
    public static final String PILES_WORK_STATE_TWO_CHARGING="PILES_WORK_STATE_TWO_CHARGING"; //2号充电位充电中
    public static final String PILES_WORK_STATE_TWO_DISCHARGING="PILES_WORK_STATE_TWO_DISCHARGING";
    public static final String PILES_WORK_STATE_ALL_CHARGING="PILES_WORK_STATE_ALL_CHARGING"; //2个充电位充电中  53
    public static final String PILES_WORK_STATE_ALL_DISCHARGING="PILES_WORK_STATE_ALL_DISCHARGING";

    public static final String PILES_WORK_STATE_STOP="PILES_WORK_STATE_STOP";        //设备停用  60
    public static final String PILES_WORK_STATE_CHARGE_STOP="PILES_WORK_STATE_CHARGE_STOP";   //充电停止 70
    public static final String PILES_POS_WORK_STATE_CHARGING="PILES_POS_WORK_STATE_CHARGING";//充电位充电中
    public static final String PILES_POS_WORK_STATE_DISCHARGING="PILES_POS_WORK_STATE_DISCHARGING";//充电位放电中
    public static final String PILES_WORK_STATE_CHARGING_PREPARE="PILES_WORK_STATE_CHARGING_PREPARE";//充电准备锁定中

    //计费方案适应类型
    public static final String TOLL_PROGRAM_TYPE="TOLL_PROGRAM_TYPE";
    public static final String TOLL_PROGRAM_TYPE_CHARGE="TOLL_PROGRAM_TYPE_CHARGE";//充电
    public static final String TOLL_PROGRAM_TYPE_PARKING="TOLL_PROGRAM_TYPE_PARKING";//泊车
    public static final String TOLL_PROGRAM_TYPE_RESERVE="TOLL_PROGRAM_TYPE_RESERVE";//预约
    public static final String TOLL_PROGRAM_TYPE_CHARGE_PRE="TOLL_PROGRAM_TYPE_CHARGE_PRE";//充电预授权
    public static final String TOLL_PROGRAM_TYPE_PARKING_PRE="TOLL_PROGRAM_TYPE_PARKING_PRE";//泊车预授权
    public static final String TOLL_PROGRAM_TYPE_RESERVE_PRE="TOLL_PROGRAM_TYPE_RESERVE_PRE";//预约预授权
    //发票内容
    public static final String INVOICE_CONTENT="INVOICE_CONTENT";
    public static final String INVOICE_CONTENT_RESERVE="INVOICE_CONTENT_RESERVE";//预约费用
    public static final String INVOICE_CONTENT_CHARGE="INVOICE_CONTENT_CHARGE";//充电费用
    public static final String INVOICE_CONTENT_PARKING="INVOICE_CONTENT_PARKING";//泊车费用
    public static final String INVOICE_CONTENT_OTHER="INVOICE_CONTENT_OTHER";//附加费

    //发票状态
    public static final String INVOICE_STATUS="INVOICE_STATUS";
    public static final String INVOICE_HANDLE="INVOICE_HANDLE";  //已处理
    public static final String INVOICE_NO_HANDLE="INVOICE_NO_HANDLE";  //未处理

    //短信发送
    public static  final String URL="Url";
    public static  final String USER_ID="UserID";
    public static  final String ACCOUNT="Account";
    public static  final String PASSWORD="Password";

    //收款单位类型
    public static  final String PAYEE_TYPE="PAYEE_TYPE";
    public static  final String PAYEE_TYPE_CHARGE="PAYEE_TYPE_CHARGE";  //充电
    public static  final String PAYEE_TYPE_PARKING="PAYEE_TYPE_PARKING"; //泊车
    public static  final String PAYEE_TYPE_RESERVE="PAYEE_TYPE_RESERVE"; //预约

    //充电桩启停控制
    public static  final String EQUIPMENT_CONTROL="EQUIPMENT_CONTROL";
    public static  final String EQUIPMENT_CONTROL_ENABLED="EQUIPMENT_CONTROL_ENABLED";  //启用
    public static  final String EQUIPMENT_CONTROL_DISABLE="EQUIPMENT_CONTROL_DISABLE"; //停止
    //车位锁控制
    public static  final String PARKING_LOCK_CONTROL="PARKING_LOCK_CONTROL";     //设备启停控制
    public static  final String PARKING_LOCK_CONTROL_OPEN="PARKING_LOCK_CONTROL_OPEN";  //开启
    public static  final String PARKING_LOCK_CONTROL_CLOSE="PARKING_LOCK_CONTROL_CLOSE"; //关闭
    //充电桩升级控制
    public static  final String UPDATE_PROGRAM="UPDATE_PROGRAM";
    public static  final String UPDATE_PROGRAM_IMMEDIATELY="UPDATE_PROGRAM_IMMEDIATELY";  //立即执行
    public static  final String UPDATE_PROGRAM_FREE="UPDATE_PROGRAM_FREE"; //空闲执行
    public static  final String UPDATE_PROGRAM_POWER="UPDATE_PROGRAM_POWER"; //加电执行
    //集团会员类型
    public static  final String GROUP_TYPE="GROUP_TYPE";
    public static  final String GROUP_TYPE_TERRACE="GROUP_TYPE_TERRACE";  //平台发行
    public static  final String GROUP_TYPE_THIRD="GROUP_TYPE_THIRD"; //第三方发行
    //报文类型(堆状态，桩整体状态，充电位状态，充电位实时数据）
    public static  final String REPORT_TYPE="REPORT_TYPE";
//    public static  final String STACK_STATUS="STACK_STATUS"; //堆状态
    public static  final String PILE_STATUS="PILE_STATUS"; //桩整体状态
    public static  final String CHARGING_STATUS="CHARGE_STATUS"; //充电位状态
    public static  final String CHARGE_DATA="CHARGE_DATA"; //充电位实时数据
    //员工类型
    public static final String EMPLOYEE_TYPE_MANAGER = "EMPLOYEE_TYPE_MANAGER"; // 管理人员
    public static final String EMPLOYEE_TYPE_STATION_MASTER = "EMPLOYEE_TYPE_STATION_MASTER"; // 站长
    public static final String EMPLOYEE_TYPE_INSPECTION_MASTER = "EMPLOYEE_TYPE_INSPECTION_MASTER"; //巡检组长
    public static final String EMPLOYEE_TYPE_INSPECTION_MEMBER = "EMPLOYEE_TYPE_INSPECTION_MEMBER"; //巡检成员
    public static final String EMPLOYEE_TYPE_MAINTAINING_MASTER = "EMPLOYEE_TYPE_MAINTAINING_MASTER"; //维修组长
    public static final String EMPLOYEE_TYPE_MAINTAINING_MEMBER = "EMPLOYEE_TYPE_MAINTAINING_MEMBER"; //维修成员

    //设备维修状态
    public static final String PILES_FAULT_STATE_NEW = "PILES_FAULT_STATE_NEW"; // 故障新建
    public static final String PILES_FAULT_STATE_WAIT = "PILES_FAULT_STATE_WAIT"; //等待维修
    public static final String PILES_FAULT_STATE_MAINTAINING = "PILES_FAULT_STATE_MAINTAINING"; //正在维修
    public static final String PILES_FAULT_STATE_DONE = "PILES_FAULT_STATE_DONE"; //维修完了

    //设备故障类型
    public static final String PILES_FAULT_TYPE_SCRAPE = "PILES_FAULT_TYPE_SCRAPE"; // 报废
    public static final String PILES_FAULT_TYPE_LOST = "PILES_FAULT_TYPE_LOST"; //丢失

    // 自发卡状态
    public static final String CARD_PAY_ACCOUNT_NOT_ENOUGH = "CARD_PAY_ACCOUNT_NOT_ENOUGH"; // 自发卡账户余额不足
    public static final String PAY_ACCOUNT = "PAY_ACCOUNT";



}


