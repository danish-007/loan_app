package com.example.loanApp.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditedEntity {
    @CreatedDate
    @Column(name = "created_time", nullable = false)
    private LocalDate createdTime;
    @CreatedBy
    @Column(name = "created_by", length = 20, nullable = false)
    private String createdBy;
    @LastModifiedDate
    @Column(name = "modified_time", nullable = false)
    private LocalDate modifiedTime;
    @LastModifiedBy
    @Column(name = "modified_by", length = 20, nullable = false)
    private String modifiedBy;

    public final LocalDate getCreatedTime() {
        return createdTime;
    }

    public final void setCreatedTime(LocalDate createDate) {
        this.createdTime = createDate;
    }

    public final String getCreatedBy() {
        return createdBy;
    }

    public final void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public final LocalDate getModifiedTime() {
        return modifiedTime;
    }

    public final void setModifiedTime(LocalDate modifiedDate) {
        this.modifiedTime = modifiedDate;
    }

    public final String getModifiedBy() {
        return modifiedBy;
    }

    public final void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
