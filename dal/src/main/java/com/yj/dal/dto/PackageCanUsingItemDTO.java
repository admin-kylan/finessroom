package com.yj.dal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageCanUsingItemDTO {
    private String courseId;
    private String courseName;
    private String shopName;
    private String sdaduimName;
    private String sdaduimId;
    private String shopId;
    private Integer classCount;
    private Integer limitCount;
}
