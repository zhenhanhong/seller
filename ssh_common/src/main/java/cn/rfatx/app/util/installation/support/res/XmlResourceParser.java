package cn.rfatx.app.util.installation.support.res;

import cn.rfatx.app.util.installation.support.util.AttributeSet;

/**
 * 对安卓App的xml处理
 */
public abstract interface XmlResourceParser
        extends XmlPullParser, AttributeSet {
    public abstract void close();
}
