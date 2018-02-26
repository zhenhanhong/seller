package cn.rfatx.entity;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
/**
 * 实体超类，增加了逻辑删除标记与数据是否有效的标记
 * @author huang an ding
 */
@MappedSuperclass
public class AtcEntity extends AuditEntity{
    private static final long serialVersionUID=1341387992747218346L;
    //逻辑删除标记 1.表示删除
    protected Integer removeTag=0;
    //用来标记数据是否失效 1.表示失效
    protected Integer invalid=0;

    @NotNull
    public Integer getRemoveTag(){
        if(removeTag==null){
            return 0;
        }
        return removeTag;
    }
    public void setRemoveTag(Integer removeTag){
        if(removeTag==null){
            removeTag=0;
        }
        this.removeTag=removeTag;
    }
    @NotNull
    public Integer getInvalid(){
        if(invalid==null){
            return 0;
        }
        return invalid;
    }
    public void setInvalid(Integer invalid){
        if(invalid==null){
            invalid=0;
        }
        this.invalid=invalid;
    }
}
