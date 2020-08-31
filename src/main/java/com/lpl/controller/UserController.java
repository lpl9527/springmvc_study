package com.lpl.controller;

import com.lpl.bean.User;
import com.lpl.exception.CustomException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * @RequestMapping
     *  作用：请求和方法的映射
     *  属性：
     *      method:指定请求的方式，可以指定多个值，满足其中一种方式即可访问
     *      params：指定请求时必须要携带的参数，未携带这些参数则请求不到此方法，
     *          可以指定多个值，指定多值时必须全部携带这些参数。当指定了请求的参数值时，要求此参数必须为此值。
     *          下面表示必须有username属性，其值为“哈哈哈”，必须有password属性。
     *      headers：指定的请求头必须存在。
     */
    @RequestMapping(value="/hello", method = RequestMethod.GET,
            params = {"username=哈哈哈","password"},
            headers = {"User-Agent"})
    public String sayHello(){
        System.out.println("你好！");
        return "success";
    }
    /**
     * 请求参数绑定
     *  1.请求参数与方法变量名称相同时可自动进行绑定；
     *  2.请求参数与方法变量名称不同时可以使用@RequestParam注解指定请求参数名称；
     *  3.请求参数与实体类中属性名称相同时可以自动封装到实体类中；
     *  4.类中的实体类属性的封装可以在表单中采用 实体类属性名.实体类属性的属性名 的方式赋值。
     *
     *  使用自定义类型转换器
     */
    @RequestMapping(value = "/param", method = {RequestMethod.GET, RequestMethod.POST})
    public String param(String username, Integer password, User user){
        System.out.println("参数username==" + username);
        System.out.println("参数password==" + password);
        System.out.println("参数User对象==" + user);
        return "success";
    }
    /**
     * 获取使用原生的ServletAPI，不写method默认使用GET方式
     */
    @RequestMapping("/servlet")
    public String servlet(HttpServletRequest request, HttpServletResponse response){

        System.out.println("resuest对象=" + request);
        String remoteHost = request.getRemoteHost();
        System.out.println("remoteHost=" + remoteHost);
        //获取Session
        HttpSession session = request.getSession(true); //没有的话就创建
        System.out.println("session对象=" + session);
        //获取ServletContext
        ServletContext servletContext = session.getServletContext();
        System.out.println("servletContext对象=" + servletContext);

        System.out.println("response对象=" + response);

        return "success";
    }
    /**
     * @RequestBody注解
     *  作用：获取到请求体中内容，并将请求体中内容转化为键值对的形式
     */
    @RequestMapping("/body")
    public String body(@RequestBody String body){

        System.out.println("请求体中内容==" + body);
        return "success";
    }
    /**
     *  请求转发或重定向
     */
    @RequestMapping("/forward")
    public String forward(HttpServletRequest request, HttpServletResponse response) throws Exception{

        //请求转发
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);
        //重定向
        //response.sendRedirect(request.getContextPath() + "/user/hello?username=哈哈哈&password=100");
        //return;
        //return "forward:/WEB-INF/pages/success.jsp";
        return "redirect:index.jsp";
    }
    /**
     * 传统方式文件上传
     */
    @RequestMapping(value = "/fileupload1", method = RequestMethod.POST)
    public String fileupload1(HttpServletRequest request) throws Exception {

        //使用fileupload组件完成文件上传
        //上传文件位置
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(realPath);
        //如果文件夹不存在，创建此文件夹
        if (!file.exists()){
            file.mkdirs();
        }
        //解析Request对象，获取到上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = upload.parseRequest(request);
        //遍历文件项
        for (FileItem item : fileItems){
            //判断当前项是否是上传文件项
            if (item.isFormField()){
                //普通表单项
            }else{
                //上传文件项
                //获取上传文件名称
                String filename = item.getName();
                //使文件名唯一，否则会进行覆盖
                String uuid = UUID.randomUUID().toString().replace("-", "");
                //完成上传
                item.write(new File(realPath, uuid + "_" + filename));
                //删除临时文件（小于10KB的文件会在缓存中生成临时文件）
                item.delete();
            }
        }
        return "success";
    }

    /**
     * springMvc传统方式文件上传，需要配置springMvc文件解析器
     */
    @RequestMapping(value = "/fileupload2", method = RequestMethod.POST)
    public String fileupload2(MultipartFile upload, HttpServletRequest request) throws Exception {

        //上传文件位置
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(realPath);
        //如果文件夹不存在，创建此文件夹
        if (!file.exists()){
            file.mkdirs();
        }
        //获取上传文件名称
        String filename = upload.getOriginalFilename();
        //使文件名唯一，否则会进行覆盖
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //完成上传
        upload.transferTo(new File(realPath, uuid + "_" + filename));
        //删除临时文件（小于10KB的文件会在缓存中生成临时文件）

        return "success";
    }
    /**
     * 异常处理，Controller抛出异常不做处理会直接抛出到客户端浏览器页面
     */
    @RequestMapping("/exception")
    public String exception() throws CustomException{

        try{
            //模拟异常
            int i = 1/0;
        }catch (Exception e){
            e.printStackTrace();
            //抛出自定义异常信息
            throw new CustomException("自定义异常CustomException信息！");
        }

        return "success";
    }
    /**
     * springMvc拦截器
     */
    @RequestMapping("/interceptor")
    public String interceptor(){

        System.out.println("拦截器要拦截的控制器方法执行了！！！");
        return "success";
    }

}
