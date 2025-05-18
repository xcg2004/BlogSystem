package com.xcg.blogsystem.domain.po;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description="权限实体")
public class Permission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "权限id")
    private Long id;
    @Schema(description = "权限名称")
    private String name;


}
