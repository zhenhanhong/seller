package cn.rfatx.listener;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dhc-user1 on 2017/7/21.
 */
public class TraceSessionListener extends SessionListenerAdapter{

    private Logger log = LoggerFactory.getLogger(TraceSessionListener.class);

    public void onStop(Session session) {
        log.info("Session被删除 【SessionID】" + session.getId());
        log.info("Session过期 【Session超时时间】" + session.getTimeout());
        log.info("Session被删除 【最后访问时间】" + DateFormatUtils.format(session.getLastAccessTime(), "yyyy-mm-dd HH:MM:SS.sss"));
    }

    public void onExpiration(Session session) {
        log.info("Session过期 【SessionID】" + session.getId());
        log.info("Session过期 【Session超时时间】" + session.getTimeout());
        log.info("Session过期 【最后访问时间】" + DateFormatUtils.format(session.getLastAccessTime(), "yyyy-mm-dd HH:MM:SS.sss"));
    }
}
