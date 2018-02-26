package cn.rfatx.common.constant;

/**
 * Created by rf-author on 2017/02/22.
 */
public enum PileType {
    PILE_UNDO("未处理"), PILE_DO_EFFECTIVE("处理生效"), PILE_DO_FAILED("处理失败");

    private PileType(String s_type) {
        this.s_type = s_type;
    }

    private final String s_type;

    public String getTypeName() {
        return this.s_type;
    }
}
