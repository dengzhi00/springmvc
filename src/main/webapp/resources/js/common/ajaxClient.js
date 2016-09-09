//加载资源名称
//autor yangwc3
//language change
//20141026
var ajaxClient = {};
ajaxClient.invoke=(function(){	
  //同步返回json数据，需要等待结果
  function _callServiceAsJson(servName, pa){
	//将pa里面的特定节点进行转换
	var params = {};
	var data =  _transferParam(pa);
	
//	alert(JSON.stringify(data));
	
	params.serviceKey = servName;
	params.data = data;
	var result;
	try{
		//ajax请求
		$.ajax({     
			type: "post",  
			async: false, //同步和异步的参数
			timeout: 10000, //超时时间设置，单位毫秒
			url: '/eiWeb/HttpInvokeServlet', 
			cache: false, //关闭AJAX相应的缓存    
			dataType: "json",		
			data: JSON.stringify(params),
			contentType: "application/json; charset=utf-8",     
			success: function (data) { 
			   
			    if(data.resultCode == 0){
					data.success = true;
				}else{
					data.success = false;
				}   
			    result = data;
			},     
			error: function (XMLHttpRequest, textStatus, errorThrown) {     
			  result ={"success":false,"resultCode":"-1","resultMsg":errorThrown};
			}     
		}); 
	}catch(e){
		result ={"success":false,"resultCode":"-1","resultMsg":"获取后台服务异常!"+e.toString()};
	}
	return result;
}
  //转换参数形式
  function _transferParam(param){
	var result = {};
	var reqInfo = {};
	var pageInfo = {};
	//参数中的dbS
	if(!param.hasOwnProperty("dbS") || param.dbS == ''){
		alert('入参异常，未包含数据源节点!入参为：'+param);
	}else{
		result.dbS = param.dbS;
		delete param['dbS']; 
	}
	//参数中的busiType
	if(param.hasOwnProperty("busiType") && param.busiType != ''){
		result.busiType = param.busiType;
		delete param.busiType;
	}
	//pageInfo节点
	if(param.hasOwnProperty("offerSet") && param.hasOwnProperty("limit")){
		pageInfo.offerSet = param.offerSet;
		pageInfo.limit = param.limit;
		result.pageInfo = pageInfo;
		
		delete param['offerSet'];
		delete param['limit'];
	}
	
	//reqInfo 节点
	result.reqInfo = param;
	
	return result;
}
//异步调用之后回调函数，不需等待
  function _callServiceBack(servName, param,func){
	try{
		var params = {};
		params.serviceKey = servName;
		params.data = param;
		var result;
		//ajax请求
		$.ajax({     
			type: "post",  
			//async: false, //同步和异步的参数
			timeout: 10000, //超时时间设置，单位毫秒
			url: '/eiWeb/HttpInvokeServlet',    
			dataType: "json",		
			data: JSON.stringify(params),
			contentType: "application/json; charset=utf-8",     
			success: function (data) { 
			    var cbs = $.Callbacks('memory');
				cbs.add(func);
				//第一次fire会缓存传入的参数
				if(data.resultCode == 0){
					data.success = true;
				}else{
					data.success = false;
				}
				cbs.fire(data);		 
			},     
			error: function (XMLHttpRequest, textStatus, errorThrown) {     
		        var result ={"success":false,"resultCode":"-1","resultMsg":errorThrown};
			    var cbs = $.Callbacks('memory');
			    cbs.add(func);
				//第一次fire会缓存传入的参数
				cbs.fire(result);
			}     
		}); 
	}catch(e){
		var result ={"success":false,"resultCode":"-1","resultMsg":"获取后台服务异常!"};
		//eval(callBack+"("+result+")"); 
		var cbs = $.Callbacks('memory');
		cbs.add(func);
		//第一次fire会缓存传入的参数
		cbs.fire(result);
	}
}
  //解析接口返回的回参信息
  function _parseResultInfo(data){
	  var resultInfo = {};
	  resultInfo = data.resultInfo;
	  return resultInfo;
  }
  return {
	 callServiceAsJson : _callServiceAsJson,
	 callServiceBack : _callServiceBack,
	 parseResult : _parseResultInfo
  }
})();