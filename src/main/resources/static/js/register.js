window.onload = function () {
    //1.给表单绑定onsubmit事件
    // document.getElementById("form").onsubmit = function () {
    //
    //     return checkUserId() && checkName() && checkPassword1() && checkEmail() && checkPassword2() && checkTel();
    // }
    //分别绑定离焦事件

    document.getElementById("userName").onblur = checkUserName;
    document.getElementById("password").onblur = checkPassword;
    document.getElementById("email").onblur = checkEmail;
    document.getElementById("phone").onblur = checkPhone;
}
// 遍历学历
// function checkxueli() {
// 	var xueli_list = document.getElementsByName("xueli");	
// 	for(var i=0;i<xueli_list.length;i++){
// 		if(xueli_list[i].checked==true){
// 			var xueli=xueli_list[i].value;
// 			window.localStorage.setItem("xueli",xueli);
			
// 		}
// 	}	
// 	return true;
// 	}


//注册跳转登录校验并设置键值
function check() {

	var flag1 = checkUserName();
	var flag2 = checkPassword();
	var flag3 = checkEmail();
    var flag4 = checkPhone();
    if (flag1 && flag2 && flag3 && flag4) {

        //获取对应值

        var userName = document.getElementById("userName").value;
        var password = document.getElementById("password").value;
        var email = document.getElementById("email").value;
        var phone = document.getElementById("phone").value;

        //将userId的值存储在userid中，设置键值对

        window.localStorage.setItem("userName",userName);
        window.localStorage.setItem("password",password);
        window.localStorage.setItem("email",email);
        window.localStorage.setItem("phone",phone);

        // //获取userId的值
        // var userId = document.getElementById("userId").value;
        // //将userId的值存储在userid中
        // window.localStorage.setItem("userid",userId);//键 值

        return true;
    }
    else {
        return false;
    }
}
//校验用户Id
// function checkUserId() {
// 	//1.获取用户名的值
// 	var userId = document.getElementById("userId").value;
// 	//2.定义正则表达式
// 	var reg_userId = /^\w{6,8}$/;
// 	//3.判断值是否符合正则的规则
// 	var flag = reg_userId.test(userId);
// 	//4.提示信息
// 	var s_userId = document.getElementById("s_userId");
//
// 	if (flag) {
// 		//提示绿色对勾
// 		s_userId.innerHTML = "<img width='35' height='25' src='img/gou.png'/>";
//
// 		return true;
// 	} else {
// 		//提示红色用户名有误
// 		s_userId.innerHTML = "用户名格式有误";
// 		return false;
// 	}
// 	return flag;
// }
//校验用户名
function checkUserName() {
	//1.获取用户名的值
	var userName = document.getElementById("userName").value;
	//2.定义正则表达式
	var reg_userName = /^\w{2,10}$/;
	//3.判断值是否符合正则的规则
	var flag = reg_userName.test(userName);
	//4.提示信息
	var s_userName = document.getElementById("s_userName");

	if (flag) {
		//提示绿色对勾
		s_userName.innerHTML = "<img width='35' height='25' src='images/gou.png'/>";
		return true;
	} else {
		//提示红色用户名有误
		s_userName.innerHTML = "用户名格式有误";
		return false;
	}
	return flag;
}

//校验口令
function checkPassword() {
	//1.获取用户名的值
	var password = document.getElementById("password").value;
	//2.定义正则表达式
	var reg_password = /^\w{6,8}$/;
	//3.判断值是否符合正则的规则
	var flag = reg_password.test(password);
	//4.提示信息
	var s_password = document.getElementById("s_password");

	if (flag) {
		//提示绿色对勾
		s_password.innerHTML = "<img width='35' height='25' src='images/gou.png'/>";
		return true;
	} else {
		//提示红色用户名有误
		s_password.innerHTML = "口令格式有误";
		return false;

	}
	return flag;
}
//确认口令
// function checkPassword2() {
// 	//1.获取用户名的值
// 	var password1 = document.getElementById("password1").value;
// 	var password2 = document.getElementById("password2").value;
// 	//2.定义正则表达式
// 	var reg_password2 = /^\w{6,8}$/;
// 	//3.判断值是否符合正则的规则
// 	var flag = reg_password2.test(password2);
// 	//4.提示信息
// 	var s_password2 = document.getElementById("s_password2");
// 	if (flag) {
// 		if(password1==password2){  //判断两个口令是否一致
// 			//提示绿色对勾
// 			s_password2.innerHTML = "<img width='35' height='25' src='img/gou.png'/>";
// 			return true;
// 		}else
// 		{
// 			return false;
//
// 		}
// 	} else {
// 		//提示红色用户名有误
// 		s_password2.innerHTML = "两次输入口令不一致";
// 		return false;
// 	}
// 	return flag;
// }
//校验邮箱
function checkEmail() {
	//1.获取email的值
	var email = document.getElementById("email").value;
	//2.定义正则表达式
	var reg_email = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	//3.判断是否符合正则的规则
	var flag = reg_email.test(email);
	//4.提示信息
	var s_email = document.getElementById("s_email");

	if (flag) {
		//提示绿色对勾
		s_email.innerHTML = "<img width='35' height='25' src='images/gou.png'/>";
		return true;
	} else {
		//提示红色用户名有误
		s_email.innerHTML = "邮箱格式有误";
		return false;
	}
	return flag;
}
//校验phone
function checkPhone() {
	//1.获取Tel的值
	var phone = document.getElementById("phone").value;
	//2.定义正则表达式
	var reg_phone =/^\w{6}$/;
	//3.判断是否符合正则的规则
	var flag = reg_phone.test(phone);
	//4.提示信息
	var s_phone = document.getElementById("s_phone");

	if (flag) {
		//提示绿色对勾
		s_phone.innerHTML = "<img width='35' height='25' src='images/gou.png'/>";
		return true;
	} else {
		//提示红色用户名有误
		s_phone.innerHTML = "电话格式有误";
		return false;
	}
	return flag;
}
