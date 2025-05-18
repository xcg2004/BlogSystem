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
@TableName("comment")
@Schema(description="Comment对象")
public class Comment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "评论id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "评论状态(已审核、未审核、已删除)")
    private Integer status;




}
