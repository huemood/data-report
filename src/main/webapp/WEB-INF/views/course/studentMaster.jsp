
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
   <!--script src="../../../js/angular2.min.js"></script-->
   <script src="../../../js/bootstrap3-typeahead.min.js"></script>
   
  <style>
  .sample * {
      /*
      font-size: 12px;
      font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
       */
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
					studentIframe.attr("src", "/studentAction?orgId="+treeNode.id);
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
	/*
	var app = angular.module('myApp', []);
	  app.controller('customersCtrl', function($scope, $http) {
	      $http.get("/course/getCourseList")
	      .then(function (result) {
	          $scope.names = result.data.records;
	      });
	  });
	  */
  </SCRIPT>
      
  </head>

  <body>
 
    <div class="container-fluid sample" >
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
        	<div class="panel panel-default">
			    <div class="panel-body">
			       <div class="row">
		        		<div class="col-sm-9 col-md-8">
		        			<strong>输入课程名称</strong>
		        		</div>
				    </div>
		        	<div class="row">
		        		<div class="col-sm-10 col-md-9">
		        			<input id="searchCourse" type="text">
		        		</div>
		        		<div class="col-sm-1 col-md-2">
		        			<input id="searchBtn" type="button" value="搜索" onclick="searchCourse()"/>
		        		</div>
		        	</div>
			    </div>
			</div>
        	<p>
        	<div class="row">
        		<div class="col-sm-6 col-md-5">
        			<span class="glyphicon glyphicon-arrow-down">选择课程</span>
        		</div>
		    </div>
        	<div class="row">
        		<div class="col-sm-12 col-md-12">
        			<table class="table table-hover">
        				<thead>
							<tr>
								<th>课程代码</th>
								<th>名称</th>
								<!--th>来源</th-->
							</tr>
						</thead>
						<tbody id="courseTb" ></tbody>
        			</table>
        		</div>
        	</div>
        	
        	<!--div ng-app="myApp" ng-controller="customersCtrl"> 
				<table>
				  <tr ng-repeat="x in names">
				  	<td>{{ x.id }}</td>
				    <td>{{ x.fullname }}</td>
				    <td>{{ x.fromsource }}</td>
				  </tr>
				</table>
			</div-->
            <!--ul id="tree" class="ztree"></ul--> 
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
     
     <script>
     /*
  $("#searchCourse").typeahead({
      source: function (query, process) {
          return $.ajax({
              url: '/course/getCourseName',
              type: 'post',
              data: { courseName: query },
              //dataType: 'array',
              success: function (result) {
            	  //var resultList = result.map(function (item) {
                  //    var aItem = { id: item.id, name: item.name };
                  //    return JSON.stringify(aItem);
                  //});
            	  return process(result);
              }
          });
      },
      delay:500,
      minLength:1,
      items: 10,   //显示10条
      delay: 0,  //延迟时间
  });
     */
  var searchCourse = function(){
      $("#searchBtn").attr("disabled",true);
	  $("#courseTb").empty();
	  var fullName = $("#searchCourse").val();
	  console.log("fullName:" + fullName);
	  $.ajax({
		  type:"POST",
		  dataType:"json",
		  url:"/course/getCourseList",
		  data:{fullName:fullName},
		  success:function (json){
			  $.each(json,function(i,n){
				  var tBody = "";
				  tBody += "<tr onclick='csa("+i+")'><td class='_td"+i+"0'>" + n.shortname + "</td><td class='_td"+i+"1'>" + n.fullname + "</td></tr>";
				  $("#courseTb").append(tBody);
			  });
			  $("#searchBtn").attr("disabled",false);
		  },
	      error: function(json) {
	      	alert("加载失败");
	      	$("#searchBtn").attr("disabled",false);
	      }
	  });
  }
  function csa(i){
	  var shortName = $("._td"+i+"0").html();
	  //var fullname = $("._td"+i+"1").html();
	  console.log("shortname:" + $("._td"+i+"0").html());
	  //console.log("fullname:" + $("._td"+i+"1").html());
	  $("#sIframe").attr("src", "/course/studentAction?shortName="+shortName);
  }
  /*
  $(document).ready(function (){
	  $("#courseTb tr").bind("click",function(){
		 console.log("id:" + $(this).find("td").eq(0).html());
		 console.log("fromsource:" + $(this).find("td").eq(1).html());
	  });
  });
  */
  </script>
  </body>
  
</html>
