package cn.rfatx.atc.util.wsService;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * Created by dhc-user1 on 2017/9/3.
 */
public class ServerPasswordCallbackHandler implements CallbackHandler {
    //用户名，请根据客户的设定修改
    private final static String USER = "atx";
    //密码，请根据客户的设定修改
    private final static String PASSWORD = "pw";
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback wp = (WSPasswordCallback) callbacks[0];

        //设置密码
        if(wp.getIdentifier().equals(USER)){
            //这个密码和客户端发送的密码进行比较
            wp.setPassword(PASSWORD);
        }
    }
}
