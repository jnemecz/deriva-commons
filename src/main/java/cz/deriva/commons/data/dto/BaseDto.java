package cz.deriva.commons.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.deriva.commons.data.domain.ActionType;
import cz.deriva.commons.data.domain.RecordId;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseDto<ID extends RecordId> {

  @JsonProperty(value = "id")
  private ID id;

  @JsonProperty(value = "itime", access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime itime;

  @JsonProperty(value = "displayable_label", access = JsonProperty.Access.READ_ONLY)
  private String displayableLabel;

  @JsonIgnore
  private ActionType actionType;

  public ActionType getActionType() {
    return actionType;
  }

  public void setActionType(ActionType actionType) {
    this.actionType = actionType;
  }

  public String getDisplayableLabel() {
    return displayableLabel;
  }

  public void setDisplayableLabel(String displayableLabel) {
    this.displayableLabel = displayableLabel;
  }

  public LocalDateTime getItime() {
    return itime;
  }

  public void setItime(LocalDateTime itime) {
    this.itime = itime;
  }

  public ID getId() {
    return id;
  }

  public void setId(ID id) {
    this.id = id;
  }

}
