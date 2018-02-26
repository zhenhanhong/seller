package cn.rfatx.shiro.filter;



import cn.rfatx.core.diva.mapper.JsonMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


/**
 * 判断提示用户登录状态
 */
public class MobileAuthenticatingFilter
  extends AuthenticatingFilter
{
  protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse)
    throws Exception
  {
    return null;
  }


    /**
     * 判断提示用户登录状态
     * @param servletRequest 服务请求
     * @param servletResponse 服务回复
     * @return 结果
     * @throws Exception 异常
     */

  protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse)
    throws Exception
  {
    Subject s = SecurityUtils.getSubject();
    boolean flag = true;
    if (!s.isAuthenticated()) {
      HttpServletResponse response = (HttpServletResponse)servletResponse;
      response.setContentType("application/json;charset=utf-8");
      response.setCharacterEncoding("utf-8");
      response.setStatus(302);
//      RestStatus status = new RestStatus(Boolean.valueOf(false));
//      status.setErrorCode("302");
//      status.setErrorMessage("用户未登录");
//      response.getWriter().append(JsonMapper.nonDefaultMapper().toJson(status)).flush();
      flag = false;
    }
    return flag;
  }
}


