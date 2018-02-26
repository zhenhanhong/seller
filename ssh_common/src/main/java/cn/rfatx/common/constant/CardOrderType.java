package cn.rfatx.common.constant;

/*
*自发卡交易类型
 */
public  enum CardOrderType {
    LOSS("挂失"),  BACK("退卡"),  FOUND("解挂"),  SINGLE_SALE("单卡出售"),  BATCH_SALE("批量售卡"), FORBIDDEN("禁用"), UNFORBIDDEN("解禁"), MADE_GIFTCARD("制作礼品卡");

     private CardOrderType(String displayName) { this.displayName = displayName; }

    private final String displayName;
    public String getTypeName() { return this.displayName; }

    public static CardOrderType getValueByKey(String key) {
        for (CardOrderType cardOrderType: CardOrderType.values()) {
            if (cardOrderType.toString().equalsIgnoreCase(key)) {
                return cardOrderType;
            }
        }
        return null;
    }
  }
