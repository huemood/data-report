<!DOCTYPE html>
 <%@ page contentType="text/html; charset=UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<HTML>
 <HEAD>
  <TITLE>  </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="../../../bootstrap-table/bootstrap-table.min.css">
  <link rel="stylesheet" href="../../../css/bootstrap-datetimepicker.min.css">

<script src="../../../bootstrap-table/bootstrap-table.min.js"></script>
  <script src="../../../bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
  <script src="../../../js/bootstrap-datetimepicker.min.js"></script>
  <script src="../../../js/bootstrap-datetimepicker.zh-CN.js"></script>
  <SCRIPT type="text/javascript" >
  $(function () {
	    initTable();
	});

	function doQuery(params){
	    $('#student-table').bootstrapTable('refresh');    //刷新表格
	}
	//修改——转换日期格式(时间戳转换为datetime格式)
    function changeDateFormat0(cellval) {
        if (cellval != null) {
            var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            return date.getFullYear() + "-" + month + "-" + currentDate;
        }
    }	
	function initTable(){
	    var url = "/operationLogList?random=" + Math.random();
	    $('#student-table').bootstrapTable({
	        method:'POST',
	        dataType:'json',
	        contentType: "application/x-www-form-urlencoded",
	        cache: false,
	        striped: true,                              //是否显示行间隔色
	        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	        url:url,
	        //height: 1024,
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
	            field : 'userID',
	            title : '账号',
	            align : 'center',
	            valign : 'middle',
	            width:  '15%',
	            sortable : true
	        }, {
	            field : 'operationType',
	            title : '操作类型',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true
	        }, {
	            field : 'operationTime',
	            title : '操作时间',
	            align : 'center',
	            valign : 'middle',
	            width:  '10%',
	            sortable : true,
	            formatter: function (value, row, index) {
                   	//console.log("value:" + value);
                   	var date =  new Date(value);
                   	Y = date.getFullYear() + '-';
                   	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
                   	D = date.getDate() + ' ';
                   	h = date.getHours() + ':';
                   	m = date.getMinutes() + ':';
                   	s = date.getSeconds(); 
                    return Y + M + D + h + m + s;
	            }
	        }
	        ]
	    });
	}
	

//	limit, offset, search, sort, order 否则, 需要包含: 
	//	pageSize, pageNumber, searchText, sortName, sortOrder. 
	function queryParams(params) {
	    var param = {
	    	//termId : $("#termId").val(),
	    	userID : $("#userID").val(),
	    	operationType : $("#operationType").val(),
	    	operationTimeStart : $("#operationTimeStart").val(),
	    	operationTimeEnd : $("#operationTimeEnd").val(),
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
		window.open("/course/studentActionExport?courseID="+$("#courseID").val()+"&fromSource="+$("#fromSource").val()+"&termId="+$("#termId").val(),"_blank");
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
        //var content_iframe = window.parent.document.getElementById("sIframe");//获取iframeID
        //var div_height = parseInt($("#queryTable").css("height")) + parseInt($("#student-table").css("height"));//使iframe高度等于子网页高度
        //content_iframe.height = div_height + 150;
   } 
    $(".dateTimePickerDiv").datetimepicker({
    	format: "yyyy-mm-dd",
    	language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
    	autoclose: 1,
    	todayHighlight: 1,
    	startView: 2,
    	minView: 2,
    	forceParse: 0
    });
  </SCRIPT>
 </HEAD>

<BODY >
	<p/>
    <div class="div-table">
        <form  method="post" class="form-horizontal">
        	<div class="form-group">
				<label class="col-lg-2 control-label" for="userID">账号</label>
				<div class="col-lg-2">
					<input id="userID" name="userID" type="text" class="form-control" placeholder=""/>
				</div>
				<label class="col-lg-2 control-label" for="operationType">类型</label>
				<div class="col-lg-2">
					<select id="operationType" name="operationType" class="selectpicker form-control">
						<option value="" selected="selected">请选择</option>
						<option value="登录">登录</option>
						<option value="注销">注销</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label" for="operationTimeStart">起始时间</label>
				<div class="col-lg-2">
					<div class="input-group date dateTimePickerDiv" data-link-field="operationTimeStart" data-link-format="yyyy-mm-dd">
				       	<input id="operationTimeStart" name="operationTimeStart" class="form-control" size="1" type="text" value="">
				        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			        </div>
		           	<input type="hidden"  value="" />
				</div>
				<label class="col-lg-2 control-label" for="operationTimeEnd">结束时间</label>
				<div class="col-lg-2">
					<div class="input-group date dateTimePickerDiv" data-link-field="operationTimeEnd" data-link-format="yyyy-mm-dd">
			        	<input id="operationTimeEnd" name="operationTimeEnd" class="form-control" size="1" type="text" value="">
			            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		            </div>
		            <input type="hidden"  value="" />
				</div>
				<div class="col-lg-4">
					<button type="button" id="queryBtn" onclick="doQuery();" class="btn  btn-sm"><span class="glyphicon glyphicon-search"></span>查询</button>
					<button type="button" class="btn   btn-sm" id="exportExcelBtn" onclick="studentActionExport();"><span class="glyphicon glyphicon-save-file"></span>下载</button>
				</div>
			</div>
     </form>
	 </div>
	 
	<div class="div-20">
	</div>

	<div class="div-table">
		<!--div id="toolbar"><span style="color:#9d9d9d;font-size:14px">以下报表根据 ${latestReportGenerateTime} 采集数据生成</span></div-->
		<table id="student-table" ></table>
	</div>
</BODY>
</HTML>
