 /*
 * Title: jquery.dialog.js. 
 * Description: 

 * Aucthor:chengjia
 * Create Date:2010-11-23
 * Call Method: $.dialog(msg);
 */
( function($) {
	  //配置信息
		var dialogTypeList = [
		{type:0,	className:'aibi_popup_success',contentUrl:'./dialogContent/popup_jquery3.html',width:440,height:2},
		{type:1,	className:'aibi_popup_wrong',contentUrl:'./dialogContent/popup_jquery4.jsp',width:440,height:272},
		{type:2, className:'aibi_popup_confirm',contentUrl:'./dialogContent/popup_jquery5.html',width:440,height:122}
		
	 ];
	jQuery.dialog =  function(options) {
		var settings = {
			type:0,//与dialogTypeList内容对应
			title:"默认",//弹出框的title
			contentUrl:dialogTypeList[0].contentUrl,
			className:dialogTypeList[0].className,
			width:dialogTypeList[0].width,
			height:dialogTypeList[0].height,
			errorCode:'',
			showHelp:false,//是否显示帮助
			showMail:false,//是否显示联系我们
			yesCall:false,
			noCall:false,
			selfIconCall:false,//自定义按钮事件
			error : function() {
				alert("对话框加载出错");
			}
		};
		var type = options.type ? options.type :settings.type;
		
	  var config_set =dialogTypeList[type];
	  
	 var opts = jQuery.extend(settings, options,config_set);
	
		//清除之前使用的popup div
		$("div").filter(".jquery_popup").empty().remove();
		//增加popup所在div
	 	var popupDiv =$("<div class='aibi_popup jquery_popup'></div>");
	 	popupDiv.appendTo("body");
	 	var popupUrl = opts.contentUrl;
	 	if(type==1){
	 	  popupUrl +="?errorCode="+opts.errorCode;
	 	}
	 //popup div内容设置
		$.ajax({
				type :'post',
				async :true,
				cache :false,
				url : popupUrl,
				dataType:"html",
				cache :false,
				success : function(html) {
				  popupDiv.addClass(opts.className);
					//根据type为dialog所在DIV增加样式
					popupDiv.append(html);

					//处理[帮助][联系我们]图标
					iconLink(popupDiv,opts);
					
			  		  $.dialog.alert(popupDiv,opts);	
			  		  //按钮事件注册
			  		  $(".aibi_btn_common[name='sure']").click(function(){
							   	popupDiv.dialog("close");  
							   	if(opts.yesCall) opts.yesCall();
							    
						  	}); 
						  $(".aibi_btn_common[name='cancel']").click(function(){
							   	popupDiv.dialog("close");  
							   	if(opts.noCall) opts.noCall();
							    
						  	}); 
						  	//自定义按钮事件
						  	if(opts.selfIconCall) opts.selfIconCall();
			  	
	  			popupDiv.dialog("open");  
	 		}
		});
		
	};


	 //弹出消息
		$.dialog.alert = function(popupDiv, opts) {
	    popupDiv.dialog({ 
	        autoOpen: false, 
	        title: opts.title, 
	        modal: true, 
	        resizable:false, 
          draggable: false,
	         width:opts.width,
	        height:80,
	        minHeight:40,
	        position:[350,150],
	        overlay: { 
	            opacity: 0.5
	           
	        }
	    }); 

	};


	//处理[帮助][联系我们]图标
	function iconLink(popupDiv,opts){
			var left_span = $('<span class="left"></span>');
					var help_link = ' <a href="#" class="aibi_icon_help" title="帮助">帮助</a>';
					if(opts.showHelp) left_span.append(help_link);
					var email_link = '<a href="#" class="aibi_icon_mail" title="联系我们">联系我们</a>';
					if(opts.showMail) left_span.append(email_link);
				   left_span.prependTo( popupDiv.find("div").filter(".bottom"));
					// popupDiv.find("div").filter(".bottom").prepend(left_span.html());
	}
})(jQuery);
