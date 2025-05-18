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
@TableName("article")
@Schema(description="Article对象")
public class Article extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "文章id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "文章摘要")
    private String summary;

    @Schema(description = "分类id")
    private Long categoryId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "已发布、草稿、已删除")
    private Integer status;

    @Schema(description = "阅读量")
    private Long viewCount;

    @Schema(description = "评论量")
    private Long commentCount;



}
