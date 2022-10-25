package com.medicion.contador.commons;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class Base implements Activable, Serializable {

	private static final long serialVersionUID = -3338767531279215034L;

	@JsonIgnore
	@Column(name = "created_at", nullable = false)
	protected LocalDateTime createdAt;

	@JsonIgnore
	@Column(name = "last_update_at", nullable = true)
	protected LocalDateTime lastUpdateAt;

	@JsonIgnore
	@Column(nullable = true)
	protected Boolean enabled;

	@JsonIgnore
	@Column(nullable = true)
	protected Boolean deleted;

	/*
	 * Getters and setters
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastUpdateAt() {
		return lastUpdateAt;
	}

	public void setLastUpdateAt(LocalDateTime lastUpdateAt) {
		this.lastUpdateAt = lastUpdateAt;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled == null ? false : enabled.booleanValue();
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return deleted == null ? false : deleted.booleanValue();
	}

	/*
	 * Methods
	 */
	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.setEnabled(true);
	}

}
