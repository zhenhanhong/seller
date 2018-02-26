package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rf-author on 2017/03/02.
 */
public interface AsynHttpCallBack {

    static final Logger log = LoggerFactory.getLogger(AsynHttpCallBack.class);

    /**
     * 处理异常时，执行该方法
     * @param e 异常
     * @return null
     */
    default public Object failed(Exception e) {
        log.error(Thread.currentThread().getName() + "--失败了--" + e.getClass().getName() + "--" + e.getMessage());

        return null;
    }

    /**
     * 处理正常时，执行该方法
     * @param respBody 正常
     * @return null
     */
    default Object completed(String respBody) {
        log.info(Thread.currentThread().getName() + "--获取内容：" + respBody);

        return complete(respBody);
    }

    /**
     * 处理取消时，执行该方法
     *
     * @return null
     */
    default public Object cancelled() {
        log.info(Thread.currentThread().getName() + "--取消了");
        return null;
    }

    Object complete(String respBody);

}