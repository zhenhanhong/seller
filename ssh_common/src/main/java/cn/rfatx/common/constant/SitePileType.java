package cn.rfatx.common.constant;

/**
 * Created by rf-author on 2017/02/22.
 */
public enum SitePileType {
    SITE_PILE_UNDO("未处理"), SITE_PILE_ALL_EFFECTIVE("全部生效"), SITE_PILE_PART_EFFECTIVE("部分生效"),SITE_PILE_PART_FAIL("部分失败"),  SITE_PILE_All_FAIL("全部失败");

    private SitePileType(String s_type) {
        this.s_type = s_type;
    }

    private final String s_type;

    public String getTypeName() {
        return this.s_type;
    }
}
