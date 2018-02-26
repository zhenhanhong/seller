package cn.rfatx.atc.util;

import java.math.BigDecimal;

/**
 * 全局变量的 key
 * Created by Young Lee
 */
public class AtcSetting {
    /**  连续预约时间间隔(分钟)  **/
    public static final String RESERVE_BETWEEN="RESERVE_BETWEEN";
    /**  预约默认有效时长(分钟)  **/
    public static final String RESERVE_TIME="RESERVE_TIME";
    /**  预约时间提前提醒(分钟)  **/
    public static final String RESERVE_REMIND_TIME="RESERVE_REMIND_TIME";
    /**  预约登记有效时长(小时)  **/
    public static final String BOOK_MAX_TIME="BOOK_MAX_TIME";
    /**  全局最高电价(度/元)  **/
    public static final String MAX_PRICE_OF_ELECTRICITY="MAX_PRICE_OF_ELECTRICITY";
    /**  全局设备功率(W)  **/
    public static final String MAX_PILES_OF_POWER="MAX_PILES_OF_POWER";
    /**  全局电池容量  **/
    public static final String MAX_BATTERY_CAPACITY="MAX_BATTERY_CAPACITY";
    /**  最大充电周期(小时)  **/
    //public static final String MAX_CHARGE_CYCLE="MAX_CHARGE_CYCLE";
    /**  预约挂起状态失效时间  **/
    public static final String RESERVE_HAND_UP_LOST="RESERVE_HAND_UP_LOST";
    /**  登记预约失效时间  **/
    public static final String BOOK_LOST_TIME="BOOK_LOST_TIME";
    /**  充电桩与汽车连接时间限制(充电启动前)  **/
    public static final String PILES_CONN_TIME="PILES_CONN_TIME";
    /**  请求预授权结果延迟时长  **/
    public static final String PRE_DELAY_TIME="PRE_DELAY_TIME";
    /**  外部重定向请求BankGateWay服务器地址  **/
    public static final String REDIRECT_BASE_PATH="REDIRECT_BASE_PATH";
    /**  内部请求BankGateWay服务器地址  **/
    public static final String INTERNAL_BGW_BASE_PATH="INTERNAL_BGW_BASE_PATH";
    /**  登记预约提前处理时间  **/
    public static final String REGISTER_RESERVE_HANDLE_TIME="REGISTER_RESERVE_HANDLE_TIME";
    /**  登记预约提前通知时间  **/
    public static final String REGISTER_RESERVE_NOTICE_TIME="REGISTER_RESERVE_NOTICE_TIME";
    /** 预授权-商联返回路径**/
    public static final String PRE_AUTH_BACK_PATH="PRE_AUTH_BACK_PATH";
    /** 预约权预授权-商联返回路径**/
    public static final String RESERVE_PRE_AUTH_BACK_PATH="RESERVE_PRE_AUTH_BACK_PATH";
    /** 充值-商联返回路径**/
    public static final String RECHARGE_BACK_PATH="RECHARGE_BACK_PATH";
    /** 消费-商联返回路径**/
    public static final String CONSUME_BACK_PATH="CONSUME_BACK_PATH";
    /** 服务器路径**/
    public static final String LOCAL_PATH="LOCAL_PATH";
    /** 电桩二维码池代码**/
    public static final String PILES_CODE_POOL_SIZE="PILES_CODE_POOL_SIZE";
    // first stage modify start
    /** 限制充电桩二维码池最小数量百分比**/
    public static final String PILES_CODE_POOL_SIZE_PERCENT="PILES_CODE_POOL_SIZE_PERCENT";
    // first stage modify start
    /** 充电桩下行指定发送地址**/
    public static final String DEVICE_URL="DEVICE_URL";
    /** 下行指令--发送二维码**/
    public static final String SEND_QR_CODE="SEND_QR_CODE";
    /** 下行指令--预约锁定**/
    public static final String RESERVE_LOCK="RESERVE_LOCK";
    /** 获取服务层服务器地址**/
    public static final String SERVICE_BASE_PATH="SERVICE_BASE_PATH";
    /** 获取管理层服务器地址**/
    public static final String MANAGER_BASE_PATH="MANAGER_BASE_PATH";
    /**预授权完成名称**/
    public static final String CONFIRM_TRADE_NAME="CONFIRM_TRADE_NAME";
    /**预授权撤销名称**/
    public static final String REVOKE_TRADE_NAME="REVOKE_TRADE_NAME";
    /**预授权完成短信**/
    public static final String CONFIRM_TRADE_MESSAGE="CONFIRM_TRADE_MESSAGE";
    /**预授权完成单位**/
    public static final String CONFIRM_TRADE_UNIT="CONFIRM_TRADE_UNIT";
    /**设备升级相关配置**/
    public static final String UP_UPDATE_IP="UP_UPDATE_IP";//升级服务器IP。20位ASCII值
    public static final String UP_UPDATE_PORT="UP_UPDATE_PORT";//升级服务器端口。5位ASCII值
    public static final String UP_LOG_IP="UP_LOG_IP";//日志服务器IP。20位ASCII值
    public static final String UP_LOG_PORT="UP_LOG_PORT";//日志服务器端口 。5位ASCII值
    public static final String UP_USER_NAME="UP_USER_NAME";//升级用户名。20位ASCII值
    public static final String UP_PASS_WORD="UP_PASS_WORD";//升级密码。20位ASCII值
    public static final String UP_EQUIPMENT_TYPE="UP_EQUIPMENT_TYPE";//设备型号。20位ASCII值
    public static final String UP_EQUIPMENT_VERSION_NUMBER="UP_EQUIPMENT_VERSION_NUMBER";//设备版本编号。50位ASCII值
    /**充电枪连接有效时长**/
    public static final String READY_TIME="READY_TIME";

    /** 系统URL前缀 **/
    public static final String ATX_WEB_URL_PER="ATX_WEB_URL_PER";

    /**  广东省平台服务器地址  **/
    public static final String PROVINCE_PLATFORM_BASE_PATH="PROVINCE_PLATFORM_BASE_PATH";
    /** 电桩状态上传的最大间隔 **/
    public static final String PILE_ONLINE_CHECK_TIME = "PILE_ONLINE_CHECK_TIME";
	
    /** 自发卡可透支额度 **/
    public static final String SELF_MADE_CARD_SKYHIGH_CREDIT_LINE="SELF_MADE_CARD_SKYHIGH_CREDIT_LINE";
    /** 自发卡冻结时间分钟 **/
    public static final String CARD_LOCK_TIME = "CARD_LOCK_TIME";
    /** 自发卡允许输错密码次数 **/
    public static final String CARD_FAIL_TOTAL = "CARD_FAIL_TOTAL";
    /** app端是否支持两种支付账户 **/
    public static final String SUPPORT_DOUBLE_PAY_ACCOUNT_MODEL = "SUPPORT_DOUBLE_PAY_ACCOUNT_MODEL";
    /** 免密支付，每天的上限额度 **/
    public static final String FREEPWD_LIMITED_BALANCE = "FREEPWD_LIMITED_BALANCE";
    /** 运营商数据取得开关 **/
    public static final String GZ_GL_GET_ONLY_ATX_DATA_FLG = "GZ_GL_GET_ONLY_ATX_DATA_FLG";
    /** 广州市平台对接用额定电压 **/
    public static final String GZ_INTERFACE_PILE_EDDY = "GZ_INTERFACE_PILE_EDDY";
    /** 广州市平台对接用额定电流 **/
    public static final String GZ_INTERFACE_PILE_EDDL = "GZ_INTERFACE_PILE_EDDL";

    /** 广州市平台访问URL **/
    public static final String GZ_CITY_PLATFORM_URL = "GZ_CITY_PLATFORM_URL";
    /** 广州市平台接入认证用User **/
    public static final String GZ_CITY_PLATFORM_AUTHENTICATION_USER = "GZ_CITY_PLATFORM_AUTHENTICATION_USER";
    /** 广州市平台接入认证用Psw **/
    public static final String GZ_CITY_PLATFORM_AUTHENTICATION_PASSWORD = "GZ_CITY_PLATFORM_AUTHENTICATION_PASSWORD";

    // 监视系统相关 start
    /** 站点地图设置用URL **/
    public static final String MONITORING_SITEMAP_SETTING = "MONITORING_SITEMAP_SETTING";
    /** 站点地图用URL **/
    public static final String MONITORING_SITEMAP = "MONITORING_SITEMAP";
    /** 总体监控用URL **/
    public static final String MONITORING_TOTAL_MONITOR = "MONITORING_TOTAL_MONITOR";
    /** 状态平铺用URL **/
    public static final String MONITORING_DEVSTATUS_TILE = "MONITORING_DEVSTATUS_TILE";
    /** 状态列表用URL **/
    public static final String MONITORING_STATE_LIST = "MONITORING_STATE_LIST";
    /** 视频监控设置用URL **/
    public static final String MONITORING_VIDEO_SETTING = "MONITORING_VIDEO_SETTING";
    /** 视频监控用URL **/
    public static final String MONITORING_VIDEO_SURVEILLANCE = "MONITORING_VIDEO_SURVEILLANCE";
    /** 实时故障用URL **/
    public static final String MONITORING_FAULT = "MONITORING_FAULT";
    /** 事件列表用URL **/
    public static final String MONITORING_EVENT_HISTORY = "MONITORING_EVENT_HISTORY";
    /** 警告列表用URL **/
    public static final String MONITORING_ALERT_HISTORY = "MONITORING_ALERT_HISTORY";
    // 监视系统相关 end
}
