package cn.rfatx.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.validation.groups.Default;
import java.io.Serializable;


@MappedSuperclass
public abstract class IdEntity implements Serializable {
    private static final long serialVersionUID = 2498902831272177631L;
    protected Long id;

    public static abstract interface Save extends Default {
    }

    public static abstract interface Update extends Default {
    }

    @javax.persistence.Id
    @GeneratedValue(generator = "gvn")
    @org.hibernate.annotations.GenericGenerator(name = "gvn", strategy = "native")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static enum ActiveType {
        DISABLE, ENABLE;

        private ActiveType() {
        }
    }
}
