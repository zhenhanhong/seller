package cn.rfatx.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * shior数据库领域接口
 */
public abstract interface ShiroDbRealmInterface
{
  public abstract AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken paramAuthenticationToken)
    throws AuthenticationException;
  
  public abstract AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection);
  
  public abstract void initCredentialsMatcher();
}


