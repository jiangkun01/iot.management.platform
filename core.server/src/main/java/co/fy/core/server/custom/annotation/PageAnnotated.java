package co.fy.core.server.custom.annotation;

import java.lang.annotation.*;

/**
 * Created by joker on 9/7/17.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PageAnnotated {

    /**
     * 没传 pageSize 参数时，每页的结果数，不写默认为 10
     */
    public int pageSize() default 10;
    /**
     * 每页结果数的最大值，不写默认为 100<br>
     * 如果 pageSize 大于这个值，则会被强制限制为这个值
     * @return
     */
    public int pageNum() default 1;

}
