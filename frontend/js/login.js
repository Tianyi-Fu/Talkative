$(function (){
    //给登录按钮绑定单击事件
    $("#btn_sub").click(function (){
        //2.发送ajax请求，提交表单数据
        $.post("http://127.0.0.1:8081/LoginServlet",$("#loginForm").serialize(),function (data){
            //data: {flag :false,errorMsg:''}
            if(data.flag){
                //登录成功
                location.href="dashboard.html";
            }else {
                //登录失败
                $("#errorMsg").html(data.errorMsg);
            }
        });
    });
});