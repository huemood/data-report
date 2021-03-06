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
	    $('#teacher-table').bootstrapTable('refresh');    //刷新表格
	}

	function initTable(){
	    var url = "/course/teacherActionList?random="+Math.random();
	    $('#teacher-table').bootstrapTable({
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
	        {
	            field : 'courseName',
	            title : '课程',
	            align : 'center',
	            valign : 'middle',
	            width:  '20%',
	            sortable : true
	        }, {
	            field : 'groupName',
	            title : '班级',
	            align : 'center',
	            valign : 'middle',
	            width:  '20%',
	            sortable : true
	        },  {
	            field : 'userName',
	            title : '姓名',
	            align : 'center',
	            valign : 'middle',
	            width:  '15%',
	            sortable : true
	        }, {
	            field : 'clickNum',
	            title : '点击次数',
	            align : 'center',
	            valign : 'middle',
	            width:  '15%',
	            sortable : true
	        }, {
	            field : 'onlineNum',
	            title : '上线天数',
	            align : 'center',
	            valign : 'middle',
	            width:  '15%',
	            sortable : true
	        }, {
	            field : 'postNum',
	            title : '发帖数',
	            align : 'center',
	            valign : 'middle',
	            width:  '15%',
	            sortable : true
	        }]
	    });
	}
	

//	limit, offset, search, sort, order 否则, 需要包含: 
	//	pageSize, pageNumber, searchText, sortName, sortOrder. 
	function queryParams(params) {
	    var param = {
	    	courseID : $("#courseID").val(),
		    fromSource : $("#fromSource").val(),
	    	termId : $("#termId").val(),
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
	
	
	function teacherActionExport() {
		window.open("/course/teacherActionExport?courseID="+$("#courseID").val()+"&fromSource="+$("#fromSource").val()+"&termId="+$("#termId").val(),"_blank");
	}
	
	function onPostBody() {
		resizeFrame();
	}
	
    function resizeFrame(){
        var content_iframe = window.parent.document.getElementById("tIframe");//获取iframeID
        var div_height = parseInt($("#queryTable").css("height")) + parseInt($("#teacher-table").css("height"));//使iframe高度等于子网页高度
        content_iframe.height = div_height + 150;
   } 

  </SCRIPT>
 </HEAD>

<BODY>

    
	  <table class="table" id="queryTable">
	      <form  method="post">
	        <tr>
		          <td style="width:5%">
		                                               学期: 
		          </td >
		          <td style="width:15%">
		            <select class="form-control  input-sm" id="termId" name="termId">   
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
		                <input type="hidden" id="courseID" name="courseID" value="${courseID}"/>
		                <input type="hidden" id="fromSource" name="fromSource" value="${fromSource}"/>
		          </td>
		          <td style="width:80%;">
		                <button type="button" id="queryBtn" onclick="doQuery();" class="btn btn-sm"><span class="glyphicon glyphicon-search"></span>查询</button>
		                <button type="button" class="btn  btn-sm" id="exportExcelBtn" onclick="teacherActionExport();"><span class="glyphicon glyphicon-save-file"></span>下载</button>
		          </td>
               
              </tr>
               </form>
	      </table>
	  <div class="div-20">
	</div>
    
    <div>
    	<div id="toolbar"><span style="color:#9d9d9d;font-size:14px">以下报表根据 ${latestReportGenerateTime} 采集数据生成</span></div>
      <table id="teacher-table">
        </table>
    </div>
</BODY>
</HTML>
