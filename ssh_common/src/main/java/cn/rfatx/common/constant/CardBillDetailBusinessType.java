package cn.rfatx.common.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 自发卡业务类型
 */
public enum CardBillDetailBusinessType {
    CHARGING("充电"), RESERVERIING("预约"),PARKING("泊车");

    private CardBillDetailBusinessType( String display) {
        this.display = display;
    }

    private final String display;

    public String getDisplay() {
        return this.display;
    }

    public static String getDisplayByKey(String value) {
        String result = "";
        for(CardBillDetailBusinessType tradeType : CardBillDetailBusinessType.values()){
            if (StringUtils.equals(tradeType.toString(), value)) {
                result = tradeType.getDisplay();
            }
        }
        return result;
    }

//    public static CardBillDetailBusinessType getObjByKey(String value) {
//        CardBillDetailBusinessType result = null;
//        for(CardBillDetailBusinessType tradeType : CardBillDetailBusinessType.values()){
//            if (StringUtils.equals(tradeType.toString(), value)) {
//                result = tradeType;
//            }
//        }
//        return result;
//    }
}
