package top.itjee.www.zchain.webcontroller.conf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebExceptionHandle {

    //log4j2 分开日志文件
    private static Logger logger = LogManager.getLogger(WebExceptionHandle.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        Exception ss = new Exception();
        String s = ss.getMessage();
        logger.error("服务运行异常", e);
        e.printStackTrace();
        return ResponseEntity.ok("ok");
    }

}
