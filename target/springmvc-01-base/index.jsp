<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>首页</h3>

    <a href="user/hello?username=哈哈哈&password=">跳转到成功页面>></a>
<br><br>
    <a href="user/param?username=哈哈哈&password=hahaha">请求参数绑定>></a>
<br><br>
    表单格式请求参数绑定（使用自定义类型转换器）>>>
    <form action="user/param" method="post">
        id: <input type="text" name="id"><br>
        username: <input type="text" name="username"><br>
        password: <input type="text" name="password"><br>
        birthday: <input type="text" name="birthday"><br>
        <input type="submit" value="提交">
    </form>
</body>
<br><br>
    <a href="user/servlet?username=啊啊啊">获取原生的ServletAPI >>></a>
<br><br>
    @RequestBody请求体请求 >>>
    <form action="user/body" method="post">
        username: <input type="text" name="username"><br>
        password: <input type="text" name="password"><br>
        <input type="submit" value="提交">
    </form>
<br><br>
<a href="user/forward">请求转发与重定向测试 >>></a>

<br><br>
传统方式文件上传（要求enctype属性的值必须是multipart/form-data，默认是application/x-www-form-urlencoded。
method属性取值必须为post） >>>
<form action="user/fileupload1" enctype="multipart/form-data" method="post">
    选择要上传的文件: <input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>

springMvc文件上传（要求enctype属性的值必须是multipart/form-data，默认是application/x-www-form-urlencoded。
method属性取值必须为post） >>>
<form action="user/fileupload2" enctype="multipart/form-data" method="post">
    选择要上传的文件: <input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>

<a href="user/exception">springMvc异常处理>>></a>
<br><br>
<a href="user/interceptor">springMvc拦截器>>></a>
</html>
