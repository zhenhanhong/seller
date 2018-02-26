package cn.rfatx.atc.util.wsService;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * Created by dhc-user1 on 2017/9/3.
 */
public class ClientPasswordCallbackHandler implements CallbackHandler {
    //密码，请根据需要修改
    private final static String PASSWORD = "pw";

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback wp = (WSPasswordCallback) callbacks[0];
        wp.setPassword(PASSWORD);
    }
}
