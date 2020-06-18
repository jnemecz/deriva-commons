package cz.deriva.commons.data.persistence;



import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity implements Persistable<Long> {

  @Column(name = "itime", updatable = false)
  public LocalDateTime itime;

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  protected Long id;

  @PrePersist
  public void prePersist() {
    this.itime = LocalDateTime.now();
  }

  public LocalDateTime getItime() {
    return itime;
  }

  public void setItime(LocalDateTime itime) {
    this.itime = itime;
  }

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean isNew() {
    return this.id == null;
  }

}

