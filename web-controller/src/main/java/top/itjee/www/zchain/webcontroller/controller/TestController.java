package top.itjee.www.zchain.webcontroller.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import top.itjee.www.zchain.service.user.UserService;
import top.itjee.www.zchain.webcontroller.conf.activemq.Producer;
import top.itjee.www.zchain.webcontroller.info.AppInfo;
import top.itjee.www.zchain.webcontroller.vo.User;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api
@Controller
public class TestController {

    @Autowired
    AppInfo appInfo;

    @Autowired
    UserService userService;

    @Autowired
    Producer producer;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //true:允许输入空值，false:不能为空值
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    JedisSentinelPool jedisSentinelPool;

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getUserById(HttpServletRequest req, @PathVariable(value = "id", required = true) Integer id) throws IOException {
        HttpSession s = req.getSession();
        if(s.getAttribute("myid") == null)
            s.setAttribute("myid","adsfaffff");
        else
            System.out.println(s.getAttribute("myid"));

        producer.sendQueueMessage("天下多少事");
        //producer.sendTopicMessage("都付笑谈中");

        Jedis jedis = jedisSentinelPool.getResource();
        String sf = jedis.get("w" + Math.random() * 10);
        System.out.println("TT" + sf);
        jedis.close();

        //List<top.itjee.www.zchain.domain.User> list = userService.findUser();
        //System.out.println(list.size());
        Map<String, String> r = new HashMap();
        r.put("id", id.toString());
        r.put("name", "clc");
        r.put("sex", "男");
        System.out.println(appInfo.appName);
        return ResponseEntity.ok(r);
    }

    @CrossOrigin
    @ApiOperation(value = "更新信息", notes = "根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "user/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, String>> update(@PathVariable("id") Integer id,
                                                      @RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> list = bindingResult.getAllErrors();
            FieldError error = (FieldError) list.get(0);
            System.out.println(error.getObjectName());
            System.out.println(error.getField());
            System.out.println(error.getDefaultMessage());
            return ResponseEntity.ok(new HashMap());
        }
        Map<String, String> r = new HashMap();
        r.put("id", id.toString());
        r.put("name", "曹龙超");
        r.put("user", user.getName());
        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "单文件上传", notes = "上传测试信息")
    @RequestMapping(value = "user/upload", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public ResponseEntity<String> fileUpload(@ApiParam(value = "上传的文件", required = true) MultipartFile file) {
        System.out.println("文件名：" + file.getOriginalFilename());
        System.out.println("文件大小：" + file.getSize());
        return ResponseEntity.ok("ok");
    }


    @ApiOperation(value = "测试重定向", notes = "测试重定向")
    @RequestMapping(value = "user/redirect", method = RequestMethod.GET)
    public String fileUpload(RedirectAttributes attr) {
        attr.addFlashAttribute("name", "clc");
        attr.addFlashAttribute("success", "添加成功!");
        return "redirect:/user/mytest";
    }

    @ApiOperation(value = "接收重定向", notes = "接收重定向")
    @RequestMapping(value = "user/mytest")
    public ResponseEntity<Map<String, String>> fileUpload(@ModelAttribute("name") String name, @ModelAttribute("success") String success) {

        Map<String, String> r = new HashMap();
        r.put("name", name);
        r.put("user", success);
        return ResponseEntity.ok(r);
    }


}
