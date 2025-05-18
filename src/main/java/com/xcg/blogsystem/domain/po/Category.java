package com.xcg.blogsystem.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author XCG
 * @since 2025-05-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("category")
@Schema(description="Category对象")
public class Category extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "分类名")
    private String name;



}
