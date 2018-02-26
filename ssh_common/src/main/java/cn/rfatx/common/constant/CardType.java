package cn.rfatx.common.constant;

/*
*卡类型
 */
public  enum CardType {
    CHARGINGCARD("普通卡"),  GIFTCARD("礼品卡");

     private CardType(String displayName) { this.displayName = displayName; }

    private final String displayName;
    public String getTypeName() { return this.displayName; }

    public static CardType getValueByKey(String key) {
        for (CardType cardType: CardType.values()) {
            if (cardType.toString().equalsIgnoreCase(key)) {
                return cardType;
            }
        }
        return null;
    }
  }
