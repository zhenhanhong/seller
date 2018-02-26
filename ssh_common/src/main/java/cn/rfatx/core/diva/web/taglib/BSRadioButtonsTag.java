package cn.rfatx.core.diva.web.taglib;

/**
 * 客户端RadioButtons标签
 */
public class BSRadioButtonsTag extends BSAbstractMultiCheckedElementTag {
    private static final long serialVersionUID = 6257615872362092808L;

    @Override
    protected String getInputType() {
        return "radio";
    }
}
