<%@ include file="../common/header.jsp" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/upload/js/jquery.dialog.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/common/upload/js/jquery.form.js"></script>
	<!--  
	<script type="text/javascript" src="<%=request.getContextPath()%>/common/upload/js/ajaxfileupload.js"></script>
	-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/common/upload/js/plupload.full.min.js"></script>
    <script type="text/javascript">
    
    

    
    function ajaxFileUpload1()
    {
    	var refreshDate = $("#refreshDate").val(); 
    	var dataitemId = $("#subupload").attr("ind");
    	
    	
		$('#uploadForm').ajaxSubmit ({
    		
    		url:'<%=basePath%>/file/fileAction.action',//用于文件上传的服务器端请求地址
           	dataType: 'json',//返回值类型 一般设置为json
    		 data: {'dataitemId':dataitemId,'uploadtime':refreshDate},
    	    success: function(data) {
    	    	if(data.status==1){
    	    		alert("上传成功");
				}else{
					alert(data.message);
					$('#attachmentFile'+n).val("");
					$('#attachmentText'+n).val("");
				}
    	    },
    		complete: function(xhr) {
    			
    		}
    	}); 
    }
    
    function ajaxFileUpload()
    {	
    	var refreshDate = $("#refreshDate").val(); 
    	var dataitemId = $("#subupload").attr("ind");
        $.ajaxFileUpload
        (
            {
            	url:'<%=basePath%>/file/fileAction.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                data: {'dataitemId':dataitemId,'refreshDate':refreshDate},
                success: function (data, status)  //服务器成功响应处理函数
                {
                	//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
                    var status  = data.status;
                    var message = data.message;
                    if(status != "1"){
                    	alert(data.message);
                    }else if(status == "1"){
                    	hideModal();
                        alert("上传成功！");
                        showmyTrade();
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
    }
    
    function uploadAttachment(n) {
		var af = $('#attachmentFile'+n);
		if(af.val()=='') {
			alert('请选择要上传的文件');
			return;
		}
		$("#attloading"+n).show();
		var attNames = $('#attachmentNames'+n).val();
		
		var url = "fileAction.action";
		$.ajaxFileUpload( {
			url : url, 
			secureuri : false,
			fileElementId : "attachmentFile"+n, 
			data:{attachmentNames:attNames},
			dataType : "json",
			success : function(data, status) {
				if(data.msg==1){
					alert("文件格式不正确（必须为.doc/.docx/.ppt/.xls/.txt/.pdf/.rar文件）");
					$('#attachmentFile'+n).val("");
					$('#attachmentText'+n).val("");
				}else if(data.msg==2){
					alert("文件太大!请重新选择");
					$('#attachmentFile'+n).val("");
					$('#attachmentText'+n).val("");
				}else if(data.msg==0){
					$('#attachmentFile'+n).val("");
					$('#attachmentText'+n).val("");
					$('#attIds'+n).val(data.attId);
					$('#attachmentNames'+n).val(data.attachmentextFileName);
					alert("上传成功!");
				}
			},complete: function(XMLHttpRequest, status){
				//alert("hide");
				$("#attloading"+n).hide();
			},
			error : function(data, status, e){
				alert(e);
			}
		});
	}
    
    function f_DL(){
        location.href="fileAction.action!download?dataitemId=001";
    }
    </script>

</head>
<body>

<a class="main-image-logo">
    <img class="img-responsive" alt="" src="../assets/img/logo.png">
</a>
<%@include file="../common/common.jsp" %>

<div class="main-image-0">
    <img alt="" src="../assets/img/header.png">
</div>

<div class="main-nav-bg"></div>
<div class="main-nav"><img alt="" src="../assets/img/location.png">&nbsp;您当前的位置：
		<span class="breadcrumb" style="background-color:white;padding:0px">
	  		<li><a href="../index.jsp">首页</a></li>
       		<li>我的地盘</li>
		</span>
</div>

<div class="main-body">
    <div>
        <div class="body-title">
            我的宝藏
        </div>
        <div>
            <ul class="nav nav-pills">
                <li class="baozangtab active" role="presentation"><a style="text-align:center;vertical-align:middle"
                                                                     id="getMybaozang" href="#">传家宝藏</a></li>
                <li class="baozangtab" role="presentation"><a style="text-align:center;vertical-align:middle"
                                                              id="getBuybaozang" href="#">收购宝藏</a></li>
            </ul>
            <div id="baozang-content" class="container">
                <div id="baozang-items" class="row">
                </div>
            </div>
        </div>
    </div>
    <div>
        <div class="body-title">
            我的交易记录
        </div>
        <div class="container">
            <form class="form-inline">
                <input class="form-control col-md-3" name="keyword" type="text" placeholder="请输入关键字"></input>
                <select class="form-control" id="tradeType" name="tradeType">
                    <option selected="selected" value="0">交易类型</option>
                    <option value="1">买入</option>
                    <option value="2">卖出</option>
                </select>

                <div data-link-format="yyyy-mm-dd" data-link-field="dtp_input2" data-date-format="" data-date=""
                     class="input-group date form_date col-md-2">
                    <input name="startTime" type="text" readonly="" value="" size="16" class="form-control">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                ----
                <div data-link-format="yyyy-mm-dd" data-link-field="dtp_input2" data-date-format="" data-date=""
                     class="input-group date form_date col-md-2">
                    <input name="endTime" type="text" readonly="" value="" size="16" class="form-control">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>

                <button id="getMytrade" type="button" class="btn btn-warning">查询</button>
            </form>

            <table id="trade-result" class="table table-striped">
                <thead>
                <tr>
                    <th>数据对象</th>
                    <th>数据周期</th>
                    <th>交易时间</th>
                    <th>交易类型</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="main-form-bottom-bg"></div>
<div class="main-image-1">
    <img alt="" src="../assets/img/6.png">
</div>
<div class="container">
    <footer>
        <div align="center">
            <p>©2014 版权所有 亚信集团股份有限公司 京ICP备11005544号-15 京公网安备110108007119号</p>
        </div>
    </footer>
</div>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<form id="uploadForm"  method="post" enctype="multipart/form-data">
    <div class="modal-dialog" role="document">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">上传数据周期</h4>
            </div>
            <div class="modal-body">
      		  	<span>
      		  		<label class="control-label col-md-2">上传周期</label>
      		  		<div data-link-format="yyyy-mm-dd" data-link-field="dtp_input2" data-date-format="" data-date=""
                         class="input-group date form_date col-md-8">
                        <input name="uploadtime" id="refreshDate" type="text" readonly="" value="" size="16" class="form-control">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                     <!-- <input id="subupload1" type="hidden"> -->
      		  	</span>
      		  	<span>
      		  		<label class="control-label col-md-2">文件上传</label>
      		  		<div class="input-group col-md-8">
                        <input type="file" id="file" name="file" >
                    </div>
      		  	</span>
      		  	<span>
      		  		<div class="col-md-2"></div>
      		  		<div class="col-md-8">
                        <input type="button" class="uploadsubmit" value="确认上传" ind="" onclick="ajaxFileUpload1();" id="subupload"  class="btn btn-default" >
                    </div>
      		  	</span>
      		  	
        		
            </div>
        </div>
    </div>
    </form>
</div>

</body>
<script id="baozang-item" type="text/x-jquery-tmpl">
		{{each(i,data) datas}}
			<div style="margin-bottom:10px" class="col-xs-6 col-md-6 item-image " onmouseover="showUpload(this)" onmouseout="hideUpload(this)" ><span class="col-xs-5 col-md-5"><img src="../assets/img/img3.png"><img class="item-image-hidden" ind="${'${'}data.dataitemId}" onclick="showModal(this)"  src="../assets/img/add.png"></span><span class="col-xs-7 col-md-7"><em style="font-weight:bold;font-style:normal;font-size:15px;line-height:30px;font-family:"微软雅黑"">${'${'}data.dataitemName}</em><br>${'${'}data.comment}</span></div>
		{{/each}}
</script>

<script id="baozang-item1" type="text/x-jquery-tmpl">
		{{each(i,data) datas}}
			<div style="margin-bottom:10px" class="col-xs-6 col-md-6 item-image " ><span class="col-xs-5 col-md-5"><img src="../assets/img/img3.png"><img class="item-image-hidden"  src="../assets/img/add.png"></span><span class="col-xs-7 col-md-7">${'${'}data.dataitemName}<br>${'${'}data.comment}</span></div>
		{{/each}}
</script>

<script id="trade-items" type="text/x-jquery-tmpl">
		{{each(i,data) datas}}
			<tr><td>${'${'}data.dataitemName}</td><td>${'${'}data.strRefreshtype}</td><td>${'${'}data.dataDate}</td><td>${'${'}data.tradeType}</td></tr>
		{{/each}}
</script>
<script type="text/javascript">
    $(function () {
        $('.form_date').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });

        var getMybaozang = function () {
            console.info(1);
            $('#getMybaozang').parent().addClass("active")
            $('#getBuybaozang').parent().removeClass("active")
            //var eachData = {datas:[{"dataitemName":"名称1",comment:"描述2"},{dataitemName:"名称3",comment:"描述4"},{dataitemName:"名称5",comment:"描述6"}]};
            $.get(host + "/datahub/dataItemsAction!dataitemTypeList.action?tradeType=" + 1, function (result) {
                var eachData2 = result;
                //alert(JSON.stringify(result));
                //	alert(eachData2);

                //	console.info(result);
                $("#baozang-items").empty();
                $("#baozang-item").tmpl(eachData2).appendTo('#baozang-items');
            });
        }
        getMybaozang();
        $("#getMybaozang").click(getMybaozang);

        var getBuybaozang = function () {
            $('#getMybaozang').parent().removeClass("active");
            $('#getBuybaozang').parent().addClass("active");
            //console.info(2);
            //var eachData = {datas:[{id:"名称5",desc:"描述55"},{id:"名称6",desc:"描述77"},{id:"名称7",desc:"描述99"}]};
            //var eachData1;
            $.get(host + "/datahub/dataItemsAction!dataitemTypeList.action?tradeType=" + 2, function (result) {
                var eachData1 = result;
                //alert(JSON.stringify(result));
                //alert(eachData1);

                //console.info(result);
                $("#baozang-items").empty();
                $("#baozang-item1").tmpl(eachData1).appendTo('#baozang-items');
            });


        }
        $("#getBuybaozang").click(getBuybaozang);

        var getMyTrade = function () {
            var keyWord = $("input[name=keyword]").val();
            var tradeType = $('#tradeType option:selected').val();
            var startTime = $("input[name=startTime]").val();
            var endTime = $("input[name=endTime]").val();
            //alert(keyWord+":"+tradeType+":"+startTime+":"+endTime);
            //console.info(3);
            //var eachData = {datas:[{id:"名称1",tradeType:"买入",tradeTime:"2015-01-10",dataCircle:"月"},{id:"名称3",tradeType:"买入",tradeTime:"2015-01-10",dataCircle:"月"},{id:"名称5",tradeType:"买入",tradeTime:"2015-01-10",dataCircle:"月"}]};
            $.get(host + "/datahub/dataItemsAction!dataitemList.action?keyword=" + encodeURI(encodeURI(keyWord)) + "&tradeType=" + tradeType + "&startTime=" + startTime + "&endTime=" + endTime, function (result) {
                var eachData3 = result;
                $("#trade-result tbody").empty();
                $("#trade-items").tmpl(eachData3).appendTo('#trade-result tbody');
            });
        }
        getMyTrade();
        $("#getMytrade").click(getMyTrade);

    });
    function showmyTrade() {
        $.get(host + "/datahub/dataItemsAction!dataitemList.action?keyword=" + '' + "&tradeType=" + '1' + "&startTime=" + '' + "&endTime=" + '', function (result) {
            var eachData3 = result;
            $("#trade-result tbody").empty();
            $("#trade-items").tmpl(eachData3).appendTo('#trade-result tbody');
        });
    }
    function showModal(e){
    	var that=e
    	var $ind=$(that).attr('ind');
    	
    	$('#subupload').attr('ind',$ind);
    	
    	//alert($('#subupload1').val())
        $('#myModal').modal('toggle')
    }
    function hideModal(){
        $('#myModal').modal('hide')
        $('#subupload').prop('ind','');
    }
    function showUpload(targets){
        $(targets).find('.item-image-hidden').css("visibility", "visible");
    }
    function hideUpload(targets){
        $(targets).find('.item-image-hidden').css("visibility", "hidden");
    }
    function upload(){
    	 $(this).find('.item-image-hidden').css("visibility", "visible");
    }
</script>
</html>