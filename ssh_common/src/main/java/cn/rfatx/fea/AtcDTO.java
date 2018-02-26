package cn.rfatx.fea;
import java.io.Serializable;
/**
 * 奥特迅DTO超类
 * @author huang an ding
 */
public class AtcDTO implements Serializable{
    private static final long serialVersionUID=7454580210212872964L;
    private Long id;
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
}
