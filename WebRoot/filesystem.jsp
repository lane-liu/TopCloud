<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'filesystem.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="shortcut icon" href="img/favicon2.ico" type="image/x-icon" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   
  </body>
  <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript">
  filesystem();
  linkShare.do?id=1&filepath=root+xiazai.png$
  function filesystem(){
	  name = encodeURIComponent("yB5yjZ1ML2NvBn+JzBSGLA==");
	 $.post(
  			"linkShare.do?",
  			{"id":1
  	  			"filepath":},
  			function(data){
  	  			console.log(data);
  			}
  		);
		//location="linkSharedownload.do?MD5ID="+name;
	  }
  
  </script>
</html>
