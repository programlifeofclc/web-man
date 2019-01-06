package top.itjee.www.zchain.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.itjee.www.zchain.enums.ThreadEnum;
import top.itjee.www.zchain.service.annotation.ReadWrite;
import top.itjee.www.zchain.utils.ThreadLocalUtil;

import java.lang.reflect.Method;
import java.util.LinkedList;

@Component
@Aspect
@Order(1)
public class ServiceAop {

    @Before("@within(org.springframework.stereotype.Service)")
    public void serviceBefore(final JoinPoint pjp) {
        try {
            Signature sig = pjp.getSignature();
            if (sig instanceof MethodSignature) {
                MethodSignature msig = (MethodSignature) sig;
                Method method = msig.getMethod();
                ReadWrite ann = method.getAnnotation(ReadWrite.class);
                if (ann != null) {
                    LinkedList<ReadWrite> list = ThreadLocalUtil.get(ThreadEnum.READ_WRITE_THREAD);
                    if (list == null || list.size() == 0) {
                        list = new LinkedList<>();
                    }
                    list.add(ann);
                    ThreadLocalUtil.set(ThreadEnum.READ_WRITE_THREAD, list);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("@within(org.springframework.stereotype.Service)")
    public void serviceAfter(final JoinPoint pjp) {
        try {
            Signature sig = pjp.getSignature();
            if (sig instanceof MethodSignature) {
                MethodSignature msig = (MethodSignature) sig;
                Method method = msig.getMethod();
                ReadWrite ann = method.getAnnotation(ReadWrite.class);
                if (ann != null) {
                    LinkedList<ReadWrite> list = ThreadLocalUtil.get(ThreadEnum.READ_WRITE_THREAD);
                    if (list != null) {
                        if (list.size() <= 1) {
                            ThreadLocalUtil.remove(ThreadEnum.READ_WRITE_THREAD);
                        } else {
                            list.removeLast();
                            ThreadLocalUtil.set(ThreadEnum.READ_WRITE_THREAD, list);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
