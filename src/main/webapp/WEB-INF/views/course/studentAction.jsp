<!DOCTYPE html>
 <%@ page contentType="text/html; charset=UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<HTML>
 <HEAD>
  <TITLE>  </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="../../../css/bootstrap.min.css" type="text/css">
 <link rel="stylesheet" href="../../../bootstrap-table/bootstrap-table.min.css">
  <link rel="stylesheet" href="../../../css/action.css" type="text/css">

 <script src="../../jquery/jquery.min.js"></script>
 <script src="../../js/bootstrap.min.js"></script>
<script src="../../../bootstrap-table/bootstrap-table.min.js"></script>
<script src="../../../bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="../../../bootstrap-table/extensions/export/bootstrap-table-export.min.js"></script>
<script src="../../../bootstrap-table/extensions/export/tableExport.min.js"></script>


  <SCRIPT type="text/javascript" >
  $(function () {
	    initTable();
	});

	function doQuery(params){
	    $('#student-table').bootstrapTable('refresh');    //刷新表格
	}

	function initTable(){
	    var url = "/course/studentActionList?random="+Math.random();
	    $('#student-table').bootstrapTable({
	        method:'POST',
	        dataType:'json',
	        contentType: "application/x-www-form-urlencoded",
	        cache: false,
	        striped: true,                              //是否显示行间隔色
	        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	        url:url,
	      //  height: $(window).height() - 110,
	      //  width:$(window).width(),
	        showColumns:true,
	        pagination:true, //设置为 true 会在表格底部显示分页条
	        queryParams : queryParams,
	        minimumCountColumns:2,
	        pageNumber:1,                       //初始化加载第一页，默认第一页
	        pageSize: 20,                       //每页的记录行数（*）
	        pageList: [10, 20, 50, 100],        //可供选择的每页的行数（*）
	        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
	        showExport: true,                    
	        exportDataType: 'all',
	        responseHandler: responseHandler,
	        onPostBody:onPostBody,
	        toolbar: '#toolbar',
	        columns: [
	        /*
	        [{
	        	title : '以下报表根据 ${latesReportGenerateTime} 采集数据生成',
	        	halign: 'center',
	        	"align":"center",
	        	"colspan": 11
	        }],
	        [
	         */
	         {
	            field : 'courseName',
	            title : '课程',
	            halign: 'center',
	            align : 'center',
	            valign : 'middle',
	            width:  '15%',
	            sortable : true
	        }, {
	            field : 'groupName',
	            title : '班级',
	            align : 'center',
	            valign : 'middle',
	            width:  '15%',
	            sortable : true
	        }, {
	            field : 'stNo',
	            title : '学号',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }, {
	            field : 'userName',
	            title : '姓名',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }, {
	            field : 'clickNum',
	            title : '点击次数',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }, {
	            field : 'onlineNum',
	            title : '上线天数',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }, {
	            field : 'viewresNum',
	            title : '浏览资源',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }, {
	            field : 'finshtaskNum',
	            title : '完成作业',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }, {
	            field : 'notTaskNum',
	            title : '未批作业',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }, {
	            field : 'postNum',
	            title : '发帖数',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }, {
	            field : 'notPostNum',
	            title : '未回贴数',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }//]
	        ]
	    });
	}
	

//	limit, offset, search, sort, order 否则, 需要包含: 
	//	pageSize, pageNumber, searchText, sortName, sortOrder. 
	function queryParams(params) {
	    var param = {
	    	termId : $("#termId").val(),
	    	zzid : $("#zzid").val(),
	    	shortName : $("#shortName").val(),
	    	//limit : this.limit, // 页面大小
	        //offset : this.offset, // 页码
	        pageindex : this.pageNumber,
	        pageSize : this.pageSize, //每页的记录数
	        sortOrder: params.order,//排序
	        sortName:params.sort//排序字段
	        
	    };
	    
	    return param;
	    
	   
	} 

	// 用于server 分页，表格数据量太大的话 不想一次查询所有数据，可以使用server分页查询，数据量小的话可以直接把sidePagination: "server"  改为 sidePagination: "client" ，同时去掉responseHandler: responseHandler就可以了，
	function responseHandler(res) { 
		//console.log("responseHandler:" + res);
	    if (res) {
	        return {
	            "rows" : res.rows,
	            "total" : res.records
	        };
	    } else {
	        return {
	            "rows" : [],
	            "total" : 0
	        };
	    }
	}
	
	
	function studentActionExport() {
		window.open("/course/studentActionExport?zzid=" + $("#zzid").val() + "&shortName="+$("#shortName").val()+"&termId="+$("#termId").val(),"_blank");
		/* 　　$.ajax({
		　　    type:"get",
		　　    async:false,
		　　   url:"/studentActionExport?zzid="+$("#zzid").val()+"&termId="+$("#termId").val()
		　　}); */
	}
	
	function onPostBody() {
		resizeFrame();
	}
	
    function resizeFrame(){
        var content_iframe = window.parent.document.getElementById("sIframe");//获取iframeID
        var div_height = parseInt($("#queryTable").css("height")) + parseInt($("#student-table").css("height"));//使iframe高度等于子网页高度
        content_iframe.height = div_height + 150;
   } 

  </SCRIPT>
 </HEAD>

<BODY >
    <div class="div-table">
        <form  method="post" >
	     <table class="table" id="queryTable" >
	  
	        <tr>
		          <td style="width:5%">
		                                               学期: 
		          </td >
		          <td style="width:15%">
		            <select class="form-control  input-sm " id="termId" name="termId">   
		                    <option value="" selected="selected">全部</option>
		                    <c:forEach var="data" varStatus="i" items="${termList}">
			                    <c:choose>
								   <c:when test="${i.index==0}">  
								   	 <option value="${data.termId }">${data.termName }</option>
								   </c:when>
								   <c:otherwise> 
								     <option value="${data.termId }">${data.termName }</option>
								   </c:otherwise>
								</c:choose>  
		                    </c:forEach>
		                </select>
		                <input type="hidden" id="zzid" name="zzid" value="${zzid}"/>
		                <input type="hidden" id="shortName" name="shortName" value="${shortName}"/>
		          </td>
		          <td style="width:80%;">
		                <button type="button" id="queryBtn" onclick="doQuery();" class="btn  btn-sm"><span class="glyphicon glyphicon-search"></span>查询</button>
		                <button type="button" class="btn   btn-sm" id="exportExcelBtn" onclick="studentActionExport();"><span class="glyphicon glyphicon-save-file"></span>下载</button>
		          </td>
               
              </tr>
              
	      </table>
	 </form>
	 </div>
	 
	<div class="div-20">
	</div>

	<div class="div-table">
		<div id="toolbar"><span style="color:#9d9d9d;font-size:14px">以下报表根据 ${latestReportGenerateTime} 采集数据生成</span></div>
		<table id="student-table" ></table>
	</div>
</BODY>
</HTML>
