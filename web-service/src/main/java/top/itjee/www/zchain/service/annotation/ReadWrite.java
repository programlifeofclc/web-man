package top.itjee.www.zchain.service.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ReadWrite {

    option value() default option.WRITE;

    enum option {
        READ,
        WRITE;
    }
}
