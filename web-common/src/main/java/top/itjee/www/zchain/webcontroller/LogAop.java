package top.itjee.www.zchain.webcontroller;


import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//@Component
@Aspect
public class LogAop {

    /**
     * controller 方法访问
     * @param pjp
     * @return
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)")
    public Object controllerLog(final ProceedingJoinPoint pjp) {
        try {
            Signature sig = pjp.getSignature();
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            MethodSignature msig = (MethodSignature) sig;
            Object target = pjp.getTarget();
            System.out.println("访问方法开始：" + msig.getName());
            Object[] objects = pjp.getArgs();
            List<Object> list = new ArrayList();
            if (objects != null) {
                for (Object o : objects) {
                    if (!(o instanceof HttpServletRequest) && !(o instanceof HttpServletResponse))
                        list.add(o);
                }
            }
            System.out.println("访问参数：" + JSON.toJSONString(list));
            Object ret = pjp.proceed();
            System.out.println("访问方法结束：" + msig.getName());
            return ret;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Around("execution(* top..*(..))")
    public Object methodLog(final ProceedingJoinPoint pjp) {
        try {
            Signature sig = pjp.getSignature();
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            MethodSignature msig = (MethodSignature) sig;
            Object target = pjp.getTarget();
            System.out.println("方法访问名字：" + msig.getName());
            Object ret = pjp.proceed();
            return ret;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }


}
