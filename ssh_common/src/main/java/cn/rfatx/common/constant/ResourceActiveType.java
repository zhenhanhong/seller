package cn.rfatx.common.constant;

/**
 * Created by rf-author on 2017/02/22.
 */
public enum ResourceActiveType {
    ENABLE("可用"), COMMON("公用"), DISABLE("不可用"), PUBLICPACKAGE("公共包"), SETUPFILE("安装包");

    private ResourceActiveType(String s_type) {
        this.s_type = s_type;
    }

    private final String s_type;

    public String getTypeName() {
        return this.s_type;
    }
}
