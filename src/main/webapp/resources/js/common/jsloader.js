/**************************************************************
		用于js中对js的加载
		by luoyang
 ***********************************************************/
function JSLoaderEnvironment(){
	
  this.loadedJs = new Array();
  
  this.loadJavaScript=function (url, overwrite){
//    url = this.stripExternalRef(url);
	var urlStrArray = url.split('/');
	var jsName = urlStrArray[urlStrArray.length-1];
	if(overwrite){
	    document.writeln("<scri"+"pt src='"+url+"' type='text/javascript'></sc"+"ript>");
	}else{
		var flag = false;
		for(var i = 0; i<this.loadedJs.length;i++){
			if(this.loadedJs[i] == jsName){
				flag =true;
				break;
			}
		}
		if(!flag){
			document.writeln("<scri"+"pt src='"+url+"' type='text/javascript'></sc"+"ript>");
		}
	}
    this.loadedJs[this.loadedJs.length] = jsName;
  };
};

JSLoader = new JSLoaderEnvironment();