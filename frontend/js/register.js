//校验用户名
//1.用户名：单词字符，4~20位
function checkUsername(){
    //1.获取用户名
    let username=$("#username").val();
    //2.定义正则
    let reg_username=/^\w{4,15}$/;
    //3.判断，给出信息
    let flag= reg_username.test(username);
    if(flag){
        //用户名合法
        $("#username").css("border","");
    }else{
        //用户名非法,加一个红色边框
        $("#username").css("border","1px solid red");
    }
    return flag;
}

//校验密码
function checkPassword(){
    //1.获取密码
    let password=$("#password").val();
    //2.定义正则
    let reg_password=/^\w{3,15}$/;
    //3.判断，给出信息
    let flag= reg_password.test(password);
    if(flag){
        //用户名合法
        $("#password").css("border","");
    }else{

        //用户名非法,加一个红色边框
        $("#password").css("border","1px solid red");
    }
    return flag;
}

$(function(){
    //表单提交时，调用所有的校验方法
    $("#registerForm").submit(function (){
        //1.发送数据到服务器
        if(checkUsername() && checkPassword()){
            //校验通过，发送Ajax请求，提交表单的数据
            $.post("RegistUserServlet",$(this).serialize(),function (data){
                //处理服务器响应的数据 data	{flag:true,errorMsg:"注册失败"}
                if(data.flag){
                    //注册成功，跳转成功页面
                    location.href="login.html";
                }else{
                    //注册失败,给errorMsg添加提示信息
                    $("#errorMsg").html(data.errorMsg);
                }
            });
        }
        //2.跳转页面

        //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回FALSE，则表单不提交
    });
    //当某一个组件失去焦点时,调用对应的校验方法
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
})