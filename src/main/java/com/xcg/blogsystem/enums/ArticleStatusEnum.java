package com.xcg.blogsystem.enums;

public enum ArticleStatusEnum {
    Draft(1), // 草稿
    UnderReview(2), // 待审核
    Published(3), //  已发布(审核通过)
    Rejected(4), //  未通过
    TakenOffShelf(5);   //  下架

    public final int value;
    ArticleStatusEnum(int value) {
        this.value = value;
    }
}
