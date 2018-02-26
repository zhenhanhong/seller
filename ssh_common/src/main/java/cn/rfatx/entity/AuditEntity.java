package cn.rfatx.entity;


import cn.rfatx.api.EntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


@MappedSuperclass
@EntityListeners({EntityListener.class})
public abstract class AuditEntity extends IdEntity {
    private static final long serialVersionUID = 1639494528180821290L;
    protected Long createId;
    protected Long createTime;
    protected Long updateId;
    protected Long updateTime;

    @JsonIgnore
    @Column(name = "create_id", length = 13, updatable = false)
    public Long getCreateId() {
        return this.createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    @JsonIgnore
    @Column(name = "create_time", length = 13, updatable = false)
    public Long getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @JsonIgnore
    @Column(name = "update_id", length = 13)
    public Long getUpdateId() {
        return this.updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    @Column(name = "update_time", length = 13)
    public Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Transient
    public boolean isEmptyString(Object s) {
        return (s == null) || (s.toString().trim().length() == 0) || (s.toString().trim().equalsIgnoreCase("null"));
    }


    @Transient
    public String getCreateTimeStr() {
        return this.createTime != null ? new DateTime(this.createTime).toString("yyyy-MM-dd HH:mm:ss") : null;
    }
}
