package com.yj.dal.config.mybatis;

import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 〈一句话功能简述〉<br>
 * 〈Mybaits-plus配置〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
@Configuration
@MapperScan(basePackages = {
        "com.yj.dal.dao"
})
public class MybatisPlusConfig {
    /**
     * @Description: mybatis-plus SQL 执行性能分析
     * @Author: 欧俊俊
     * @Date: 2018/9/14 9:40
     */
    @ConditionalOnProperty(
            value = {"sql.isPrint"},
            matchIfMissing = false)
    @Bean
    // 设置 dev 环境开启
    @Profile({"dev"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // <!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->
        performanceInterceptor.setMaxTime(10000);
        // <!--SQL是否格式化 默认false-->
        performanceInterceptor.setFormat(false);
        return performanceInterceptor;
    }
    /**
     * @Description: mybatis-plus分页插件
     * @Author: 欧俊俊
     * @Date: 2018/9/14 9:40
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }


}
