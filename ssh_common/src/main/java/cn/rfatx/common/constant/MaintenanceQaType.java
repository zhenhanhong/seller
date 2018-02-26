package cn.rfatx.common.constant;

/*
*常见维修问题类型
 */
public  enum MaintenanceQaType {
    EQU("硬件设备"),  SOFT("软件"),  OTHERS("其他");

     private MaintenanceQaType(String displayName) { this.displayName = displayName; }

    private final String displayName;
    public String getTypeName() { return this.displayName; }

    public static MaintenanceQaType getValueByKey(String key) {
        for (MaintenanceQaType qaType: MaintenanceQaType.values()) {
            if (qaType.toString().equalsIgnoreCase(key)) {
                return qaType;
            }
        }
        return null;
    }
  }
