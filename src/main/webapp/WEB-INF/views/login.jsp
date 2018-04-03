<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<!-- <meta content="width=device-width,initial-scale=1,user-scalable=no" name="viewport"> -->
	<title>国开学习分析云平台</title>
	<link rel="icon" sizes="any" mask href="" />
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="../../css/login.css">
<!-- 	<link rel="stylesheet" type="text/css" href="../../css/screen.css">-->	
    <link href="../../css/footer.css" rel="stylesheet">

</head>
<body onload="">
	<div id='ouchn'>
	    <div>
	    <div class="hide"></div>
		<form action="/loginCheck" id='ouchnform' method="post">
		<div id='form-controll'>

			<!-- <div id='input-position-controll'> -->
			    <div class='msg_ok1' style='color:#692F9D'>
					<c:if test="${not empty message}">
                        ${message}
                     </c:if>
			    </div>	
				<input type="text"     class='input-controll'  id='user_name' name="username" placeholder='账号' value='zongbu'><br>
				<input type="password" class='input-controll'  id='password' name="password"  placeholder='密码' value='admin2017'><br>
				<input type="submit"   class='input-controll'  id='submit-controll' name="" value='登录'>
				<div class="clear"></div>
			<!-- </div> -->
		</div>
		</form>
		 <div class="clear"></div>
        </div>
	</div>



 <div id="copyright"  class="" >
              <div class="text-center  center-block">
                  <div >
                      <p>国家开放大学 版权所有&nbsp;&nbsp;&nbsp;&nbsp;交通地图 &nbsp;&nbsp;&nbsp;&nbsp;京ICP备05064925号-6 &nbsp;&nbsp;&nbsp;&nbsp;<img src="../../img/gaba.png" align="middle" alt="">京公网安备 11040202430135号</p>

                      <p>咨询热线：400-810-0090 &nbsp;&nbsp;&nbsp;&nbsp;技术热线：400-810-3299&nbsp;&nbsp;&nbsp;&nbsp;技术服务邮箱：open@crtvu.edu.cn</p>
                      
                      <p>北京市海淀区复兴路75号&nbsp;&nbsp;&nbsp;&nbsp;邮编：100039&nbsp;&nbsp;&nbsp;&nbsp;电话：57519016&nbsp;&nbsp;&nbsp;&nbsp;传真：57519017   </p>
                  </div>

                  <div >
                      <div>
                          <img src="../../img/01.jpg" alt="">
                          <p class="wec" >公众微信</p>
                      </div>
                      <div>
                           <img src="../../img/02.jpg" alt="">
                           <p  class="mob" >移动端访问</p>
                      </div>
                     
                       

                  </div>
                  
                     <div class="clear"></div>

              </div>

                    
        </div>    

	<script src="../../jquery/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	    if (top != window) {
	    	top.location.href=window.location.href;
	    }
	</script>

<script src="../../js/login.js" type="text/javascript"></script>
</body>
</html>