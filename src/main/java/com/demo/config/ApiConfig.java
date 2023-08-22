package com.demo.config;
public class ApiConfig {

    //无需权限即可访问的Api接口地址
    public static String[] NoAuthApi = new String[]{
            // vue打包后的静态资源放行, 注意一定不要加/static/，不然无法排除成功！！！
            "/*.*",
            "/**/*.*.*",
            "/**/*.*.*.*"
    };
}