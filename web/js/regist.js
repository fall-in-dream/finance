
//在文档加载（就绪）之后运行下面这些jquery代码
//即在 DOM 加载完成后才可以对 DOM 进行操作。
$(document).ready(function(){
	//开始jquery代码
	
	
	//用户名  失去焦点时，进行用户名是否存在的判断
	$("#username").blur(function(){
		var username=$("#username").val();
		if(username==null||username==""){
			$("#msgLabel").text("用户名不能为空");
		} else {
			$.ajax({
				//请求资源路径
				url:"RegisterServlet?method=checkUsername&username=" + $("#username").val(),
				//规定请求的类型（GET 或 POST）
				type:"post",
				async:true,
				//规定要发送到服务器的数据（json格式）
				//发送数据到服务器时所使用的内容类型
				contentType:"application/json;charset=utf-8",
				dataType:"json",//返回时的数据类型json
				//当请求成功时运行的函数
				success:function(data){
					//alert(data.name);
					//返回的data是 exit 或者 notexit

					if(data.status=="exit"){//用户已存在
						$("#msgLabel").text("该用户已存在，请重新输入");
						$("#username").val("");//清空值
//						$("#username").focus();
					}
					else{
						$("#msgLabel").text("");
					}
				},
				//当请求失败时运行的函数
				error:function(data){
					$("#msgLabel").text("失败");
				}

			});
		}
	});
	
	//密码框获取焦点时的事件
	$("#password").focus(function(){
		var username=$("#username").val();
		if(username==null||username==""){
			$("#msgLabel").text("用户名不能为空");
//			$("#username").focus();
		}
	});
	
	//密码框失去焦点时的事件
	$("#password").blur(function(){
		var password=$("#password").val();
		if(password==null||password==""){
			$("#msgLabel").text("密码不能为空");
//			$("#password").focus();
		}else{
			$("#msgLabel").text("");//清空
		}
	});
	
	//确认密码框失去焦点时的事件
	$("#repassword").blur(function(){
		var password=$("#password").val();
		var repassword=$("#repassword").val();
		if(repassword==null||repassword==""){
			$("#msgLabel").text("确认密码不能为空");
//			$("repassword").focus();
		}
		else{
			if(password!=repassword){
				$("#msgLabel").text("两次输入密码不一致，请重新输入新密码");
				$("#password").val("");
				$("#repassword").val("");
//				$("#password").focus();
			}
		}
	});
	
	//邮箱输入框非空时，进行邮箱格式的校验
	$("#email").blur(function(){//失去焦点时
		var x=$("#email").val();
		
		//邮箱格式的校验
		if(x!=null||x!=""){
			var atpos=x.indexOf("@");//@的位置
			var dotpos=x.lastIndexOf(".");//.的位置
			//位置从第0位开始算起
			//@的位置在1或者1以后
			//.的位置在@的位置之后，而且最少是多两个位置
			//.的位置加2，必须比email的长度少于2
		 	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length){
		 		$("#msgLabel").text("不是一个有效的 e-mail 地址，请修改email");
//		 		$("#email").focus();
		 		return false;
		 	}
		 	else{
		 		$("#msgLabel").text("");
		 	}
		 	
		}
	});
	
	//手机输入框为非空时，进行手机格式的校验
	$("#phone").blur(function(){//失去焦点时
		 var phone=$("#phone").val();
		 //正则表达式
		 var regExp =/^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
         if(!regExp.test(phone)){  
        	$("#msgLabel").text("不是一个有效的手机号码，请修改phone");
//		 	$("#phone").focus();
              return false;  
         }
         else{
		 		$("#msgLabel").text("");
		 	}
	});
	
	//注册按钮判断事件--什么时候表单不提交
	$("#registbtn").click(function(){
		var label=$("#msgLabel").text();//消息提示框内容为空
		var phone=$("#phone").val();//最后一个输入框phone的判断  是否为空，极为重要
		if(label==""||label==null){
			if(phone!=""&&phone!=null){
				return true;
			}
		}
		return false;//表单不提交
	});
	//重置按钮事件 --重置消息提示框
	$("#resetbtn").click(function(){
		$("#msgLabel").text("");//清空
		$("#username").val("");//清空
		$("#password").val("");//清空
		$("#repassword").val("");//清空
		$("#email").val("");//清空
		$("#phone").val("");//清空
	});


});

var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
function sendMessage() {
	if($("#email").val()){
		$.ajax({
			type:"POST",
			url :"SendEmailServlet?random"+Math.random(),
			data:{
				 email:$("#email").val(),
			},
			success:function(data){
				 alert("验证码发送成功，请注意查收。");
			},
		});
		curCount = count;
		$("#get-code").attr("onclick", "void(0)");
		$("#get-code").css("cursor", "auto");
		$("#get-code").html(curCount + "秒后可重新发送");
		InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次请求后台发送验证码 TODO
	}else{
		alert("邮箱发送失败!未填写邮箱或邮箱输入错误");
	}
}

//timer处理函数
function SetRemainTime() {
	if (curCount == 0) {
		window.clearInterval(InterValObj);//停止计时器
		$("#get-code").attr("onclick", "sendMessage()");//启用按钮
		$("#get-code").css("cursor", "pointer");
		$("#get-code").html("重新发送验证码");
	} else {
		curCount--;
		$("#get-code").html(curCount + "秒后可重新发送");
	}
}
