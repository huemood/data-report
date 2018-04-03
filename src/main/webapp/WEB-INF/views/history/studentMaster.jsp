
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
    <link rel="icon" href="../../../favicon.ico">

    <title>国开学习分析云平台</title>

       <!-- Bootstrap core CSS -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../../css/bootstrap-theme.min.css" rel="stylesheet">
     <!-- Custom styles for this template -->
    <link href="../../../css/dashboard.css" rel="stylesheet">
   <!--  
    <script src="../../../jquery/jquery.min.js"></script>
    <script  src="../../../js/bootstrap.min.js"></script>
    
    
     <link rel="stylesheet" href="../../../zTreeV3/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../../../zTreeV3/js/jquery.ztree.core.js"></script> -->
  
 
  <style>
  .sample * {
      font-size: 12px;
      font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
    }
  </style>
  <SCRIPT type="text/javascript" >
	var zTree;
	var studentIframe;
	var firstAsyncSuccessFlag = 0;

	
	var setting = {
			async: {
				enable: true,
				url:"/getOrgList",
				autoParam:["id"],
				otherParam:{"initId":"${sessionScope.userinfo.zzid}"}
			},
			view: {
				dblClickExpand: false,
				showLine: true,
				selectedMulti: false
			},
	  	    data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: "NULL"
				}
			}, 
			callback: {
				beforeClick: function(treeId, treeNode) {
					
				},
				onAsyncSuccess: function (event, treeId, treeNode, msg) {
					var treeObj = $.fn.zTree.getZTreeObj("tree");
					var node = treeObj.getNodeByParam("id", "${sessionScope.userinfo.zzid}"); 
					if (treeNode != null) {
						node = treeNode;
					}
					if (node != null) {
						treeObj.selectNode(node);
						if (firstAsyncSuccessFlag == 0) {
							treeObj.expandNode(node,true);
							firstAsyncSuccessFlag = 1;
						} 
						  
						treeObj.setting.callback.onClick(null,treeId, node);//调用事件  
						//
						//console.log("onAsyncSuccess"+parseInt($(".sidebar")[0].scrollHeight));
					}
				},
				onCollapse: function(event, treeId, treeNode) {
				    //console.log("onCollapse"+parseInt($("#tree").css("height")));
				},
				onExpand:  function(event, treeId, treeNode) {
				    //console.log("onExpand"+parseInt($("#tree").css("height")));
				},
				onClick: function(event, treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("tree");
					studentIframe.attr("src", "/history/studentAction?orgId="+treeNode.id);
					return true;
				}  
			}
		};

	


	$(document).ready(function(){
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting);
		studentIframe = $("#sIframe");
	//	studentIframe.bind("load", loadReady);
	});
	
	function loadReady() {
		var bodyH = studentIframe.contents().find("body").get(0).scrollHeight,
		htmlH = studentIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), 
		minH = Math.min(bodyH, htmlH),
		//h = studentIframe.height() >= maxH ? minH:maxH;
		h = maxH;
		if (h < 500) h = 500;
		studentIframe.height(h);
		console.log(h);
	}
	

  </SCRIPT>
      
  </head>

  <body>
 
    <div class="container-fluid sample" >
      <div class="row">
      	<div class="col-sm-3 col-md-2 sidebar">
        	<ul id="tree" class="ztree" style="overflow-x:scroll"></ul>	
	    </div>
       </div>
       <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">学生行为分析</h1>
           <IFRAME ID="sIframe" Name="sIframe" FRAMEBORDER=0 SCROLLING=auto width=100%  height="500px" SRC=""  ></IFRAME>
        </div>
      </div>
    </div>
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     
  </body>
</html>
