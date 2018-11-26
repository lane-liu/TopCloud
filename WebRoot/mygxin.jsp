<%@ page language="java" import="java.util.*,com.top.cloud.bean.*"
	pageEncoding="utf8" contentType="text/html; charset=utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ID=request.getParameter("id"); 
%>
<!DOCTYPE html>
<html>

	<head lang="en">
		<meta charset="utf-8" />
		<title>个人系统</title>
		<link rel="stylesheet" type="text/css" href="css/public.css" />
		<link rel="stylesheet" type="text/css" href="css/mygxin.css" />
		<link rel="stylesheet" type="text/css" href="css/personal.css" />
		 <link rel="shortcut icon" href="img/favicon2.ico" type="image/x-icon" />
		 <style type="text/css">
		 .loading {
    margin: 100px auto;
    width: 8em;
    height: 8em;
    position: relative;
}
 
.loading .progress {
    position: absolute;
    width: 6em;
    height: 6em;
    background-color: white;
    border-radius: 50%;
    left: 1em;
    top: 1em;
    line-height: 6em;
    text-align: center;
}
 
.left,
.right {
    width: 4em;
    height: 8em;
    overflow: hidden;
    position: relative;
    float: left;
    background-color: #999999
}
 
.left {
    border-radius: 8em 0 0 8em;
}
 
.right {
    border-radius: 0 8em 8em 0;
}
 
.left:after,
.right:after {
    content: "";
    position: absolute;
    display: block;
    width: 4em;
    height: 8em;
    background-color: white;
    border-radius: 8em 0 0 8em;
    background-color: red;
}
 
.right:after {
    content: "";
    position: absolute;
    display: block;
    border-radius: 0 8em 8em 0;
}
.left:after {
    transform-origin: right center;
}
 
.right:after {
    transform-origin: left center;
    transform: rotateZ(45deg);
}
 .UploadWindows{
     	z-index:1;
    	height:300px;
    	width:300px;
    	background-color: #fff;
    	border: 2px dashed #ccc;
    	position: fixed;
    	left: 40%;
    	top:30%;
    	display: none;
    }
    .UploadWindowsFormfile{
    	height: 40px;
    	width: 180px;
    	 text-align:center;
    	margin-top: 20px;
    }
    .emailCheckedInfo{
    height: 40px;
    	width: 180px;
    	 text-align:center;
    	margin-top: 20px;
    }
    .UploadWindowsFormsubmit{
    	height: 40px;
    	width: 120px;
    	line-height: 40px;
    	margin-left: 10px;
    	margin-top: 80px;
    	background-color: #CCCCCC;
    	border-radius: 5px;
    }
    .UploadWindowsFormBtn{
    height: 40px;
    	width: 120px;
    	line-height: 40px;
    	margin-left: 30px;
    	margin-top: 80px;
    	background-color: #CCCCCC;
    	border-radius: 5px;
    }
#emailCheckedTextDiv{
display: none;
}
		 </style>
	</head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js">
	
</script>


	<body>
		<div class="UploadWindows" id="UploadWindows" >
		<span style="margin-left: 110px;font-size: 20px ">邮箱验证</span>
		<div >
		<input type="text" id="emailCheckedInfo"  name="emailCheckedInfo"  disabled="disabled" class="emailCheckedInfo" style="border: 1px solid black;">
		<a href="javascript:SendEmialCheckedCord();">点击发送邮箱验证码</a>
		</div>
		<div id="emailCheckedTextDiv">
		<input type="text"id="emailCheckedText" name="emailChecked" class="UploadWindowsFormfile" style="border: 1px solid black;">
		<a href="#">输入邮箱验证码</a>
		</div>
		<div >
		<button class="UploadWindowsFormsubmit" onclick="Checkmailboxcode();">提交</button>
		<input type="button" value="关闭" onclick="downUploadWindows();" class="UploadWindowsFormBtn">
		<input value="" name="filepath" style="display: none" id="UploadFilePath">
		</div>
	</div>
		<!------------------------------head------------------------------>
		<div class="head ding">
			<div class="wrapper clearfix">
				<div class="clearfix" id="top">
					<h1 class="fl"><a href="index.html"><img src="img/logo-9.png"/></a></h1>
					<div class="fr clearfix" id="top1">
						<p class="fl">
							<a href="login.html" id="login">登录</a>
							<a href="regist.html" id="reg">注册</a>
						</p>
						<!--0-->
					</div>
				</div>
			</div>
		</div>
		<!------------------------------idea------------------------------>
		<div class="address mt" id="add">
			<div class="wrapper clearfix">
				<a id="shouyeId" href="index.html" class="fl">首页</a>
				<span>/</span>
				<a id="daohangId" href="mygxin.jsp?id=ID" class="on">我的中心</a>
			</div>
		</div>

		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<div class="zuo fl">
					<h3>
						<a href="#"><img src="img/tx.png"/></a>
						<p class="clearfix"><span id="UserNameSpan" class="fl"></span><span class="fr">[退出登录]</span></p>
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
							<li>
								<a href=""></a>
							</li>
							<li >
								<a href=""></a>
							</li>
							<li>
								<a href=""></a>
							</li>
							<li>
								<a href=""></a>
							</li>
						</ul>
					</div>
				</div>
				<div class="you fl">
					<div class="tx clearfix">
						<div class="fl clearfix">
							<a href="#" class="fl"><img src="img/tx.png" /></a>
							<p class="fl"><span></span>
								<a href="mygrxx.jsp">修改个人信息></a>
							</p>
						</div>
						<div class="fr"><a href="javascript:ShowFrom();" style="color: red;">请绑定邮箱 ></a></div>
					</div>
					<div class="bott">
						<div class="clearfix">
							<a href="#" class="fl"><img src="img/gxin1.jpg" /></a>
							<p class="fl"><span>待支付的订单：<strong>0</strong></span>
								<a href="myorderq.html">查看待支付订单></a>
							</p>
						</div>
						<div class="clearfix">
							<a href="#" class="fl"><img src="img/gxin2.jpg" /></a>
							<p class="fl"><span>待收货的订单：<strong>0</strong></span>
								<a href="myorderq.html">查看待收货订单></a>
							</p>
						</div>
					
							<div class="loading">
   			 				<div class="left"></div>
   							 <div class="right"></div>
   							 <div class="progress" id="progress">85%</div>
   							 <span>储存空间</span>
   							 <a>储存空间扩容</a>
							</div>
						
					</div>
				</div>
			</div>
		</div>

		<!--返回顶部-->
		<div class="gotop">
			<a href="cart.html">
				<dl>
					<dt><img src="img/gt1.png"/></dt>
					<dd>去拓扑<br />云盘</dd>
				</dl>
			</a>
			<a href="#" class="dh">
				<dl>
					<dt><img src="img/gt2.png"/></dt>
					<dd>联系<br />我们</dd>
				</dl>
			</a>
			<a href="mygxin.html">
				<dl>
					<dt><img src="img/gt3.png"/></dt>
					<dd>个人<br />中心</dd>
				</dl>
			</a>
			<a href="#" class="toptop" style="display: none">
				<dl>
					<dt><img src=""/></dt>
					<dd>返回<br />顶部</dd>
				</dl>
			</a>
			<p>400-800-8200</p>
		</div>
		<div class="footer">

			<p class="dibu"><span>&copy;2017拓朴</span><span>·</span>
				<a href="javascript:;">拓朴圆桌</a><span>·</span>
				<a href="javascript:;">发现</a><span>·</span>
				<a href="javascript:;">云盘应用</a><span>·</span>
				<a href="javascript:;">使用机构账号登录</a><span>·</span>
				<a href="javascript:;">联系我们</a><span>·</span>
				<a href="javascript:;">存储来拓朴</a><br />
				<span>·</span>
				<a href="javascript:;">京ICP证110745号</a><span>·</span><span>京公网安备11010802010035号</span><span>·</span>
				<a href="javascript:;">出版物经营许可证</a>
			</p>
		</div>
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
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
	var mygxinID=document.getElementById("mygxinID");
	var mygrxxID=document.getElementById("mygrxxID");
	var remimaID=document.getElementById("remimaID");
	var shouyeId=document.getElementById("shouyeId");
	var daohangId=document.getElementById("daohangId");
	var emailCheckedInfo=document.getElementById("emailCheckedInfo");
	var progress=document.getElementById("progress");
	
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
				var mygxinIDhref="mygxin.jsp?id="+ID;
				mygxinID.setAttribute('href', mygxinIDhref);  
				var mygrxxIDhref="mygrxx.jsp?id="+ID;
				mygrxxID.setAttribute('href', mygrxxIDhref); 
				var remimaIDhref="remima.jsp?id="+ID;
				remimaID.setAttribute('href', remimaIDhref); 
				var shouyeIdIDhref="index.html?id="+ID;
				shouyeId.setAttribute('href', shouyeIdIDhref);
				var daohangIdIDhref="mygxin.jsp?id="+ID; 
				daohangId.setAttribute('href', daohangIdIDhref); 
				emailCheckedInfo.value=json.email;
				var s=1024*1024*1024;
				var k=json.surplusdevicespace/s*100+"";
				k=k.substring(0,5);
				progress.innerHTML=k+"%";
				});
			
			
		}
	
	}
function ShowFrom(){
	var UploadWindows=document.getElementById("UploadWindows");
	UploadWindows.style.display="block";
}
function downUploadWindows(){
	var UploadWindows=document.getElementById("UploadWindows");
	UploadWindows.style.display="none";
}
function SendEmialCheckedCord(){
	var emailCheckedTextDiv=document.getElementById("emailCheckedTextDiv");
	
	$.post(
	 		"Sendmailbox.do", 
			{
				"id" : ID,
			}, 
			function(data){
						if(data!=0){
							emailCheckedTextDiv.style.display="block";
							}
				});
			}

function Checkmailboxcode(){
	var emailCheckedText=document.getElementById("emailCheckedText");
	var emailCheckedTexts=emailCheckedText.value;
	$.post(
	 		"Checkmailboxcode.do", 
			{
				"id" : ID,
				"checkCode":emailCheckedTexts
			}, 
			function(data){
				console.log(data);
						if(data!=null){
							alert("邮箱验证成功");
							}else{
							alert("邮箱验证失败");
								}
				});	

	
}
</script>
	</body>

</html>