/**
 * 字符串截取公用组件,长度超出的部分用省略号表示
 * @author ppf
 * @memberOf {TypeName} 
 */
jQuery.fn.limit=function(){  
    var self = $("[limit]");  
    self.each(function(){  
        var objString = $(this).text();  
        var objLength = $(this).text().length;  
        var num = $(this).attr("limit");  
        if(objLength > num){  
            $(this).attr("title",objString);  
            objString = $(this).text(objString.substring(0,num) + "...");  
        }  
    })  
}  
$(function(){  
    $("[limit]").limit();  
})  