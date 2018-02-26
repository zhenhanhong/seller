package cn.rfatx.common.constant;


public  enum UserSexType {
      MAN("男"),  WOMEN("女"),  NONE("无");

     private UserSexType(String s_type) { this.s_type = s_type; }

    private final String s_type;
    public String getTypeName() { return this.s_type; }
  }
