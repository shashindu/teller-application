package com.ncs.tellerapplication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractBaseEntity {

	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedTimestamp;

    @PrePersist
    protected void onCreate() {
    	updatedTimestamp = createdTimestamp = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    	updatedTimestamp = new Date();
    }
    
}
