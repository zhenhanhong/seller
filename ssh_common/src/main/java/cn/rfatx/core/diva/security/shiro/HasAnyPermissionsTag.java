package cn.rfatx.core.diva.security.shiro;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;
/**
 * 不知为何Shiro一直不提供HasAnyPermissions的Tag.
 *
 * @author Chirs Chou(chirs@zhoujin.com)
 */
public class HasAnyPermissionsTag extends PermissionTag{
    private static final long serialVersionUID=-4786931833148680306L;
    private static final String PERMISSION_NAMES_DELIMITER=",";
    @Override
    protected boolean showTagBody(String permissionNames){
        boolean hasAnyPermission=false;
        Subject subject=getSubject();
        if(subject!=null){
            for(String permission : permissionNames.split(PERMISSION_NAMES_DELIMITER)){
                if(subject.isPermitted(permission.trim())){
                    hasAnyPermission=true;
                    break;
                }
            }
        }
        return hasAnyPermission;
    }
}