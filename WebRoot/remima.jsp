<%@ page language="java" import="java.util.*,com.top.cloud.bean.*"
	pageEncoding="utf8" contentType="text/html; charset=utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>

<head lang="en">
<meta charset="utf-8" />
<title>密码修改页面</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/mygrxx.css" />
<link rel="stylesheet" type="text/css" href="css/personal.css" />
 <link rel="shortcut icon" href="img/favicon2.ico" type="image/x-icon" />
<style type="text/css">
	.remima p {
	width: 800px;
	position: relative;
}
	.remima p .msg {
    position: absolute;
    left: 390px;  
}
.remima p .msg.onError {
	height: 50px;
	width: 280px;
	line-height: 30px;
	font-size: 15px;
	color: red;
}
.remima p .msg.onSuccess {
	height: 50px;
	width: 280px;
	line-height: 30px;
	font-size: 15px;
	color: #757575;
}
	</style>
</head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script >
	
</script>
<body>
	<div class="head ding">
		<div class="wrapper clearfix">
			<div class="clearfix" id="top">
				<h1 class="fl">
					<a href="index.html"><img src="img/logo-9.png" /> </a>
					<input id="cache" type="text" name="ID" value="${cache} " style="display: none;">
				</h1>
				<div class="fr clearfix" id="top1">
					<p class="fl">
						<a href="login.html" id="login">登录</a> <a href="regist.html"
							id="reg">注册</a>
					</p>
					<!--0-->
				</div>
			</div>
			<!--2-->
		</div>
	</div>
	<!------------------------------idea------------------------------>
	<div class="address mt">
		<div class="wrapper clearfix">
			<a id="shouyeId" href="index.html" class="fl">首页</a> <span>/</span> <a
			id="daohangId"	href="mygxin.jsp?id=ID" class="on">个人信息</a>
		</div>
	</div>

	<!------------------------------Bott------------------------------>
	<div class="Bott">
		<div class="wrapper clearfix">
			<div class="zuo fl">
				<h3>
					<a href="#"><img src="img/tx.png" /> </a>
					<p class="clearfix">
						<span class="fl" id="UserNameSpan"></span><span class="fr"><a
							href="login.html">[退出登录]</a> </span>
					</p>
				</h3>
				<div>
					<h4>个人中心</h4>
					<ul>
						<li><a id="mygxinID" href="mygxin.jsp?id=">我的中心</a>
						</li>

					</ul>
					<h4>账户管理</h4>
					<ul>
						<li><a id="mygrxxID" href="mygrxx.jsp?id=">个人信息</a>
						</li>
						<li><a id="remimaID"href="remima.jsp?id=">修改密码</a>
						</li>
					</ul>
					<h4></h4>
					<ul>
						<li><a href=""></a>
						</li>
						<li><a href=""></a>
						</li>
						<li><a href=""></a>
						</li>
						<li><a href=""></a>
						</li>
					</ul>
				</div>
			</div>
			<div class="you fl">
				<h2>修改密码</h2>
				<form id="apply_link_form" action="" method="post" class="remima">
					<p>
						<span ><!--选择验证身份方式： --></span><!-- <input type="checkbox" />密码验证 <input
							type="checkbox" />邮箱验证 -->
							<label name="label_blog" id="label_blog"  value="">  
					</p>
					<p>
						<span style="text-align: right;">原密码：</span><input id="oldpassword" name="oldpassword" type="password" value=""  autocomplete="off"/>
					</p>
					<p class="op">输入原密码</p>
					<p>
						<span style="text-align: right;">新密码：</span><input id="newpassword" name="newpassword"  type="password" value=""  autocomplete="off"/>
					</p>
					<p class="op">6-16 个字符，需使用字母、数字组合，不能使用纯数字、纯字母</p>
					<p>
						<span style="text-align: right;">重复新密码：</span><input id="repsd" type="password" autocomplete="off"/>
					</p>
					<p class="op">请再次输入密码</p>
					<p>
						<span style="text-align: right;">验证码：</span><input id="code" style="width:80px" type="text" value=""  autocomplete="off"/>
						<img class="yzm_img" style="width=50px" id='imgVcode' src="checkCode" onclick="document.getElementById('imgVcode').src = 'checkCode?'+(new Date()).getTime()"/>
					</p>
					<p class="op">按右图输入验证码，不区分大小写</p>
					<input type="submit" class="submit_btn" id="btnSubmit" onclick="sendSubmit();" value="提交" />
				</form>
			</div>
		</div>
	</div>

	<!--返回顶部-->
	<div class="gotop">
		<a href="index.html">
			<dl>
				<dt>
					<img src="img/gt1.png" />
				</dt>
				<dd>
					去购<br />物车
				</dd>
			</dl> </a> <a href="#" class="dh">
			<dl>
				<dt>
					<img src="img/gt2.png" />
				</dt>
				<dd>
					联系<br />客服
				</dd>
			</dl> </a> <a href="mygxin.html">
			<dl>
				<dt>
					<img src="img/gt3.png" />
				</dt>
				<dd>
					个人<br />中心
				</dd>
			</dl> </a> <a href="#" class="toptop" style="display: none">
			<dl>
				<dt>
					<img src="img/gt4.png" />
				</dt>
				<dd>
					返回<br />顶部
				</dd>
			</dl> </a>
		<p>400-800-8200</p>
	</div>
	<!--footer-->
	<div class="footer">
		<!-- 3-->
		<p class="dibu">
				<span>&copy;2017拓朴</span><span>·</span>
				<a href="javascript:;">拓朴圆桌</a><span>·</span>
				<a href="javascript:;">发现</a><span>·</span>
				<a href="javascript:;">云盘应用</a><span>·</span>
				<a href="javascript:;">使用机构账号登录</a><span>·</span>
				<a href="javascript:;">联系我们</a><span>·</span>
				<a href="javascript:;">存储来拓朴</a><br /> <span>·</span>
				<a href="javascript:;">京ICP证110745号</a><span>·</span><span>京公网安备11010802010035号</span><span>·</span>
				<a href="javascript:;">出版物经营许可证</a>
			</p>
	</div>
	<script src="js/jquery-1.8.3.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	//预编译:需要先全部加载所有的JQuery代码,绑定事件,事件生效
	//$(function(){ 所有的JQuery代码  });
	function getRequest(strParame) {
            var args = new Object();
            var query = location.search.substring(1);

            var pairs = query.split("&"); // Break at ampersand
            for (var i = 0; i < pairs.length; i++) {
                var pos = pairs[i].indexOf('=');
                if (pos == -1) continue;
                var argname = pairs[i].substring(0, pos);
                var value = pairs[i].substring(pos + 1);
                value = decodeURIComponent(value);
                args[argname] = value;
            }
            return args[strParame];
        }
	var ID=getRequest("id");
	console.log(ID);
	chedlogin();
	function chedlogin(){
	var UserNameSpan=document.getElementById("UserNameSpan");
	var apply_link_form=document.getElementById("apply_link_form");
	var mygxinID=document.getElementById("mygxinID");
	var mygrxxID=document.getElementById("mygrxxID");
	var remimaID=document.getElementById("remimaID");
	var shouyeId=document.getElementById("shouyeId");
	var daohangId=document.getElementById("daohangId");
		if(ID!=""){
			$.post(
		 		"getTopCloudUser.do", 
				{
					"id" : ID,
				}, 
				function(data) {
				console.log(data);
				var json=eval(data);
				UserNameSpan.innerHTML=""+json.username;
				apply_link_form.action="ChangePassword.do?id="+ID;
				var mygxinIDhref="mygxin.jsp?id="+ID;
				mygxinID.setAttribute('href', mygxinIDhref);  
				var mygrxxIDhref="mygrxx.jsp?id="+ID;
				mygrxxID.setAttribute('href', mygrxxIDhref); 
				var remimaIDhref="remima.jsp?id="+ID;
				remimaID.setAttribute('href', remimaIDhref); 
				var shouyeIdIDhref="index.html?id="+ID;
				shouyeId.setAttribute('href', shouyeIdIDhref);
				var daohangIdIDhref="remima.jsp?id="+ID; 
				daohangId.setAttribute('href', daohangIdIDhref); 
				});
			
			
		}
	
	}

var checkedpassword=false;
	var checkedckeckcode=false;
	var checkedpassworddel=false;
	function sendSubmit(){
	if(checkedpassword&&checkedcheckcode&&checkedpassworddel){
	var oldpassword=document.getElementById("oldpassword").value;
	var newpassword=document.getElementById("newpassword").value;
		$.post(
			"ChangePassword.do?",
			{
			"id" : ID,
			"newpassword":newpassword,
			"oldpassword":oldpassword
			},
		function(data){
		if(data==1){
			alert("修改成功");
			location="remima.jsp?id="+ID;
		}else{
			alert("修改失败");
		}
		}
		);
	}else{
		alert("数据不能为空");
	}
	}
	
		$(function() {
			$("form :input").blur(function() {
					var $parent = $(this).parent();
					$parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）

					//验证旧密码是否正确
					if($(this).is("#oldpassword")) {
						var oldpsdVal = $.trim(this.value);
						if(oldpsdVal == "") {
							var errorMsg = " 密码为空！";
							$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
							checkedusername=false;
						} else {
							$.get("CheckedOldPassWord.do?oldpassword="+oldpsdVal+"&id="+ID, function(data){
								if(data==1){
								var okMsg = " 密码输入正确";
							$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
							checkedusername=true;
								}else{
							var errorMsg = " 密码输入错误";
							$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
							checkedusername=false;
								}
	    					});
						}
					}
					
					
					//验证新密码格式是否正确
					if($(this).is("#newpassword")) {
						var psdVal = $.trim(this.value);
						var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
						if(psdVal == "" || !regPsd.test(psdVal)) {
							var errorMsg = " 请输入您的密码！";
							$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
							checkedpassword=false;
						} else {
							var okMsg = " 输入正确";
							$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
							checkedpassword=true;
						}
					}

					//验证确认密码
					if($(this).is("#repsd")) {
						var psdVal = $("#newpassword").val();
						var repsdVal = $.trim(this.value);
						if(repsdVal == "") {
							var errorMsg = " 请确认您的密码";
							$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
							checkedpassword=false;
						} else {
							if(repsdVal != psdVal) {
								var errorMsg = " 您输入密码前后不一致";
								$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
								checkedpassword=false;
							} else {
								var okMsg = " 输入一致";
								$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
								checkedpassword=true;
							}
						}
					}

//					验证验证码
					if($(this).is("#code")) {
						var code = $.trim(this.value);
						//异步发送AJAX请求
						
						$.get("checkCode.do?verifyCode=" + code, function(data) {
						console.log("-------------------------------------->"+data);
								if(data ==0) {
									var errorMsg = " ";
									$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
									checkedckeckcode=false;
								} else if(data==1){
									var okMsg = " 输入正确";
									$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
									checkedckeckcode=true;
								}
							});
						}
					 

					}).keyup(function() {
					//triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
					$(this).triggerHandler("blur");
				}).focus(function() {
					$(this).triggerHandler("blur");
				});

				//点击重置按钮时，通过trigger()来触发文本框的失去焦点事件
				$("#btnSubmit").click(function() {
					//trigger 事件执行完后，浏览器会为submit按钮获得焦点
					$("form .required:input").trigger("blur");
					var numError = $("form .onError").length;
					if(numError) {
						return false;
					}
					alert('修改成功！')
				});
			})
</script>

</body>

</html>