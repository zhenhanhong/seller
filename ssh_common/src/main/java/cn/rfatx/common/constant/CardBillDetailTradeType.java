package cn.rfatx.common.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 自发卡交易类型
 */
public enum CardBillDetailTradeType {
    RECHARGE("01", "充值"), FREEZE("02", "冻结"),UNFREEZE("03", "解冻"),PAY("04", "支付");

    private CardBillDetailTradeType(String value, String display) {
        this.value = value;
        this.display = display;
    }

    private final String value;
    private final String display;

    public String getValue() {
        return this.value;
    }
    public String getDisplay() {
        return this.display;
    }

    public static String getDisplayByValue(String value) {
        String result = "";
        for(CardBillDetailTradeType tradeType : CardBillDetailTradeType.values()){
            if (StringUtils.equals(tradeType.getValue(), value)) {
                result = tradeType.getDisplay();
            }
        }
        return result;
    }
}
