package cn.rfatx.app.util;


import cn.rfatx.api.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析用户的配置文件
 */
public class Users {
    private static Logger log = LoggerFactory.getLogger(Users.class);
    public static String name() {
        return ShiroUser().getName();
    }

    public static Long id() {
        if(null == ShiroUser()){
            return null;
        }
        return ShiroUser().getId();
    }

    public static ShiroUser ShiroUser() {
        Subject s = SecurityUtils.getSubject();
        if(s != null){
            if(s.getSession() != null){
                log.info("****************Shiro Session情报(Id)：" + s.getSession().getId());
                log.debug("****************Shiro Session情报(StartTime)：" + s.getSession().getStartTimestamp());
                log.info("****************Shiro Session情报(Timeout)：" + s.getSession().getTimeout());
            }else{
                log.debug("****************Shiro Session 为空。");
            }
        }else{
            log.debug("****************Shiro subject 为空。");
        }
        ShiroUser user = (ShiroUser) s.getPrincipal();
        return user;
    }
}

