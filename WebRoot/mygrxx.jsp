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
		<title>个人信息修改页面</title>
		<link rel="stylesheet" type="text/css" href="css/public.css" />
		<link rel="stylesheet" type="text/css" href="css/mygrxx.css" />
		<link rel="stylesheet" type="text/css" href="css/personal.css" />
		 <link rel="shortcut icon" href="img/favicon2.ico" type="image/x-icon" />
	</head>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js">
	
</script>

	

	<body>
		<!------------------------------head------------------------------>
		<div class="head ding">
			<div class="wrapper clearfix">
				<div class="clearfix" id="top">
					<h1 class="fl">
					<a href="index.html"><img src="img/logo-9.png" /> </a>
				</h1>
					<div class="fr clearfix" id="top1">
						<p class="fl">
							<a href="login.html" id="login">登录</a>
							<a href="regist.html" id="reg">注册</a>
						</p>
						<!--0-->
						<!--0.1-->
					</div>
				</div>
			</div>
		</div>
		<!------------------------------idea------------------------------>
		<div class="address mt">
			<div class="wrapper clearfix">
				<a id="shouyeId" href="index.html" class="fl">首页</a><span>/</span>
				<a id="daohangId" href="mygxin.jsp" class="on">个人信息</a>
			</div>
		</div>

		<!------------------------------Bott------------------------------>
		<div class="Bott">
			<div class="wrapper clearfix">
				<div class="zuo fl">
					<h3>
					<a href="#"><img src="img/tx.png" /> </a>
					<p class="clearfix">
						<span id="UserNameSpan" class="fl"></span><span class="fr"><a
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
							<li>
								<a href=""></a>
							</li>
							<li>
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
					<h2>个人信息</h2>
					<div class="gxin">
						<div class="tx">
							<a href="#"><img src="img/tx.png" />

						</div>
						<div class="xx">
							<h3 class="clearfix">
							<strong class="fl">基础资料</strong><a href="#" class="fr" id="edit1">编辑</a>
						</h3>
							<div id="UserNAMEDiv">姓名：</div>
							<div id="UserTELDIV">电话：</div>
							<div id="UserSEXDiv">性别：</div>
							<div>
								<span class="fl">地址：中国</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--遮罩-->
		<div class="mask"></div>
		<!--编辑弹框-->
		<div class="bj">
			<div class="clearfix">
				<a href="#" class="fr gb"><img src="img/icon4.png" /></a>
			</div>
			<h3>编辑基础资料</h3>
			<form action="UpdateUserInfor.do?id=${cache.id}" method="post" id="ChangInfoIDFormId">
<%-- 				<p><label>id：</label><input type="text"  readonly="readonly" name="id" value="${user.id }" /></p> --%>
				<p><label>姓名：</label><input type="text"  readonly="readonly" value="" id="usernameinputID"/></p>
				
				<p><label>电话：</label><input type="text" name="tel" value="${cache.tel }" /></p>
				<p>
					<label>性别：</label>
					<td><input type="radio" name="sex" value="男" />男
						<input type="radio" name="sex" value="女" />女
						<input type="radio" name="sex" value="不告诉你" />不告诉你
					</td>
				</p>
				<p><label>地址：</label><input name="address" type="text" /></p>
				<div class="bc">
					<input type="submit" value="提交" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<!--高级设置修改-->
		<div class="xg">
			<div class="clearfix">
				<a href="#" class="fr gb"><img src="img/icon4.png" /></a>
			</div>
			<h3>切换账号地区</h3>
			<form action="#" method="get">
				<p><label>姓名：</label><input type="text" value="六六六" /></p>
				<div class="bc">
					<input type="button" value="保存" />
					<input type="button" value="取消" />
				</div>
			</form>
		</div>
		<!--修改头像-->
		<div class="avatar">
			<div class="clearfix">
				<a href="#" class="fr gb"><img src="img/icon4.png" /></a>
			</div>
			<h3>修改头像</h3>
			<form action="#" method="get">
				<h4>请上传图片</h4>
				<input type="button" value="上传头像" />
				<p>jpg或png，大小不超过2M</p>
				<input type="submit" value="提交" />
			</form>
		</div>

		<!--返回顶部-->
		<div class="gotop">
			<a href="cart.html">
				<dl>
					<dt>
					<img src="img/gt1.png" />
				</dt>
					<dd>
						去拓扑<br />云盘
					</dd>
				</dl>
			</a>
			<a href="#" class="dh">
				<dl>
					<dt>
					<img src="img/gt2.png" />
				</dt>
					<dd>
						联系<br />客服
					</dd>
				</dl>
			</a>
			<a href="mygxin.html">
				<dl>
					<dt>
					<img src="img/gt3.png" />
				</dt>
					<dd>
						个人<br />中心
					</dd>
				</dl>
			</a>
			<a href="#" class="toptop" style="display: none">
				<dl>
					<dt>
					<img src="img/gt4.png" />
				</dt>
					<dd>
						返回<br />顶部
					</dd>
				</dl>
			</a>
			<p>400-800-8200</p>
		</div>
		<!--footer-->
		<div class="footer">
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
	var UserNAMEDiv=document.getElementById("UserNAMEDiv");
	var UserTELDIV=document.getElementById("UserTELDIV");
	var UserSEXDiv=document.getElementById("UserSEXDiv");
	var usernameinputID=document.getElementById("usernameinputID");
	var ChangInfoIDFormId=document.getElementById("ChangInfoIDFormId");
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
				var mygxinIDhref="mygxin.jsp?id="+ID;
				mygxinID.setAttribute('href', mygxinIDhref);  
				var mygrxxIDhref="mygrxx.jsp?id="+ID;
				mygrxxID.setAttribute('href', mygrxxIDhref); 
				var remimaIDhref="remima.jsp?id="+ID;
				remimaID.setAttribute('href', remimaIDhref); 
				UserNAMEDiv.innerHTML=UserNAMEDiv.innerHTML+""+json.username;
				var shouyeIdIDhref="index.html?id="+ID;
				shouyeId.setAttribute('href', shouyeIdIDhref);
				var daohangIdIDhref="mygrxx.jsp?id="+ID; 
				daohangId.setAttribute('href', daohangIdIDhref); 
				if(json.tel==null){
					UserTELDIV.innerHTML=UserTELDIV.innerHTML+"待添加...";
					}else{
						UserTELDIV.innerHTML=UserTELDIV.innerHTML+""+json.tel;
						}
				if(json.tel==null){
					UserSEXDiv.innerHTML=UserSEXDiv.innerHTML+"待添加...";
					}else{
						UserSEXDiv.innerHTML=UserSEXDiv.innerHTML+""+json.sex;
						}
				usernameinputID.value=json.username;
				ChangInfoIDFormId.action="UpdateUserInfor.do?id="+ID;
				});
			
			
		}
	
	}

</script>
	</body>

</html>