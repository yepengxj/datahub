<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>数据下载窗口</title>
	<script type="text/javascript" src="/common/upload/js/jquery.js"></script>
	<script type="text/javascript" src="/common/upload/js/jquery.dialog.js"></script>
	<script type="text/javascript" src="/common/upload/js/jquery.form.js"></script>
	<script type="text/javascript" src="/common/upload/js/ajaxfileupload.js"></script>
    <script type="text/javascript">
    function ajaxFileUpload()
    {
        
        $("#loading")
        .ajaxStart(function(){
            $(this).show();
        })//开始上传文件时显示一个图片
        .ajaxComplete(function(){
            $(this).hide();
        });//文件上传完成将图片隐藏起来
        
        $.ajaxFileUpload
        (
            {
                url:'fileAction.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                data: {'dataitemId' :  '001'},
                success: function (data, status)  //服务器成功响应处理函数
                {
                    //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
                    alert(data.message);
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
				alert("hide");
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
    	<form action="" method="post" enctype="multipart/form-data">
        <img src="loading.gif" id="loading" style="display: none;">
        <input type="file" id="file" name="file" />
        <br />
        <input type="button" value="上传" onclick="ajaxFileUpload();">
        
        <input type="button" value="下载" onclick="f_DL()" />
        </form>
    </body>
</html>