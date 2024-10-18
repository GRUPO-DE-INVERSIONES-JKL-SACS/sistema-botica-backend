package com.jkl.SistemaBotica.api.userPermission;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PermissionDTO {
    private Long id;
    private String name;
    @JsonProperty("can_read")
    private Boolean canRead;
    @JsonProperty("can_write")
    private Boolean canWrite;
    @JsonProperty("can_delete")
    private Boolean canDelete;
    @JsonProperty("can_update")
    private Boolean canUpdate;
    @JsonProperty("role_id")
    private Long roleId;
}