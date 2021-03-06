
<!DOCTYPE html>
 <%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>国开学习分析云平台</title>

       <!-- Bootstrap core CSS -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/bootstrap-theme.min.css" rel="stylesheet">
     <!-- Custom styles for this template -->
    <link href="../../css/dashboard.css" rel="stylesheet">
    
    <script src="../../jquery/jquery.min.js"></script>
    <!--script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script-->
        
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../jqueryform/3.46/jquery.form.js" /></script>
    
    
     <link rel="stylesheet" href="../../zTreeV3/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../../zTreeV3/js/jquery.ztree.core.js"></script>
  
 
  <style>
  .sample * {
      /*
      font-size: 12px;
      font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
      */
    }
  </style>
  <SCRIPT type="text/javascript" >
  
   var menuClick = function(menuUrl,tag) {
	   /* $("#contentIframe").attr("src",menuUrl); */
	   
	   $('.nav.navbar-nav li').removeClass('active');
	   tag.parentNode.parentNode.parentNode.className="active";
	   
	  $("#contentDiv").load(menuUrl);
  };
  
	 $(document).ready(function() {
		 
		 /* $("#contentIframe").attr("src","/studentMaster"); */
		  $("#contentDiv").load("/studentMaster");
		 
		$("#pwdForm").submit(function() {
			var options = {
					   url:$(this).action,
					   datatype:"json",
					   success:function(data) {
						   var jdata = data;
						   //var jdata = JSON.parse(data);
						   $("#info").children().remove();
						   if (jdata.result=='true') {
							  $("#info").append(jdata.info);
						   } else {
							  $("#info").append(jdata.info);
							   return false;
						   }
					   },
					   beforeSubmit:function(formData) {
						   $("#info").html("");
						   for (var i=0; i < formData.length; i++) {
						        if (!formData[i].value) {
						           $("#info").append("您输入的新旧密码不能为空，请重新输入");
						            return false;
						        }
						        if (formData[i].value.length<6 || formData[i].value.length>11) {
						        	$("#info").append("您输入的新密码应在6到11位(含)之间，请重新输入");
						            return false;
						        }
						    }
						   
						   var newPwd = $("#newPwd").val();
						   var newPwdMore = $("#newPwdMore").val();
						   
						   if (newPwd != newPwdMore) {
							   $("#info").append("两次输入的新密码不一致，请重新输入");
					            return false;
						   }
					   }
			   };
			    $(this).ajaxSubmit(options);
			return false;
		});
	  });
	

  </SCRIPT>
      
  </head>

  <body>
  
      <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse  navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
         
<!--           <a class="navbar-brand" href="#"> <img src="../../img/logo.jpg" height=40 width=50></a>
 -->            <a class="navbar-brand" href="#"> 国开学习分析云平台</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="dropdown active">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
              <span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;机构报表 <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#" onClick="menuClick('/studentMaster',this)">学生行为分析</a></li>
                <li><a href="#" onClick="menuClick('/teacherMaster',this)" >教师行为分析</a></li>
              </ul>
            </li>
            <li>
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
              <span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;课程报表 <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#" onClick="menuClick('/course/studentMaster',this)">学生行为分析</a></li>
                <li><a href="#" onClick="menuClick('/course/teacherMaster',this)" >教师行为分析</a></li>
              </ul>
            </li>
            <li><a href="#about"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;教师</a></li>
            <li><a href="#contact"><span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;学生</a></li>
            <li><a href="#" onClick="menuClick('/operationLog',this)"><span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;日志</a></li>
            <li>
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
              <span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;机构历史报表 <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#" onClick="menuClick('/history/studentMaster',this)">学生行为分析</a></li>
                <li><a href="#" onClick="menuClick('/history/teacherMaster',this)" >教师行为分析</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                    <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <c:out value="${sessionScope.userinfo.username}"/>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dLabel">
                        <li><a href="#"  data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;密码修改</a></li>
                       <!--  <li><a href="#"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;个人设置</a></li> -->
                    </ul>
             </li>
            <li><a href="/logout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					密码修改
				</h4>
			</div>
			<form id="pwdForm" name="pwdForm" action="/pwdChg" method="post">
			<div class="modal-body">
					<div class="formgroup">
					    <label for="oldPwd" >原密码</label>
					    <input type="password" id="oldPwd"  name="oldPwd" class="form-control input-sm" placeholder="请输入原密码">
					</div>
					<div class="formgroup">
					    <label for="newPwd" >新密码</label>
					    <input type="password" id="newPwd"  name="newPwd" class="form-control input-sm" placeholder="请输入新密码">
					</div>
					<div class="formgroup">
					    <label for="newPwdMore" >确认新密码</label>
					    <input type="password" id="newPwdMore"  name="newPwdMore" class="form-control input-sm" placeholder="请再次确认新密码">
					</div>
				    <div class="formgroup">
					    <h4 id="info"></h4>
					</div>
			</div>
			<div class="modal-footer">
		    	<button type="submit" class="btn btn-primary btn-sm">保存</button>
				<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">关闭</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
    </div>
    
    <div ID="contentDiv" >
    
    </div>

<!--    <IFRAME ID="contentIframe" Name="contentIframe" FRAMEBORDER=0 SCROLLING=auto width=100%  height="500px" SRC=""  ></IFRAME>
 -->
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     
  </body>
</html>
