//autor yangwc3
//20150323
var query = {};
query.Page = function() {
	/* 每页的记录数 */
	var _pageInfo;
	var _listnerName;
	var defaultListnerName = "changePage";
	var defaultPageSize = 20;
	var _initPageNumber = function(totalNum, pageSize) {
		if (!pageSize || pageSize <= 0) {
			_pageInfo.pageSize = defaultPageSize;
		} else {
			_pageInfo.pageSize = pageSize;
		}
		if (!totalNum) {
			_pageInfo.total = 0;
		} else {
			_pageInfo.total = totalNum;
		}
		_pageInfo.first = 1;
		
		_pageInfo.end = parseInt(_pageInfo.total / _pageInfo.pageSize);
		if (_pageInfo.total % _pageInfo.pageSize > 0) {
			_pageInfo.end++;
		}
		if (_pageInfo.end == 0) {
			_pageInfo.end = 1;
		}
		
		//针对下一页查询情况下
		if(_pageInfo.curPage != 1 && _pageInfo.curPage <= _pageInfo.end){
			_pageInfo.curPage = _pageInfo.curPage;
		}else{
			_pageInfo.curPage = 1;
		}
		
	}
	//查询参数信息 
	/**
	 * @description 初始化页面
	 * @function pageInfoDivId : 要生成的分页信息的div层 pageSize : 每页的条数，传入为空，则默认20条 totalNum : 总的数量 listnerName ：
	 *           要派发的侦听的名字，传入为空，则默认为"changePage"
	 */
	var _init = function(pageInfoDivId, totalNum, pageSize, listnerName) {
		_pageInfo = {};
		_initPageNumber(totalNum, pageSize);
		if (!listnerName) {
			_listnerName = defaultListnerName;
		} else {
			_listnerName = listnerName;
		}

		$('#' + pageInfoDivId).empty();

		var divNavi1 = $('<div class="navi1">' + '&nbsp;第<SPAN id="spanPageInfo" class=orange12a>1/' + _pageInfo.end
				+ '' + '</SPAN>页&nbsp;<INPUT id="btnFirst" class=buttonl type=button value=首页 >'
				+ '<INPUT id="btnPre" class=buttonm type=button value=上页 >'
				+ '<INPUT id="btnNext" class=buttonm type=button value=下页 >'
				+ '<INPUT id="btnEnd" class=buttonr type=button value=尾页 >' + '</div>');
		var divNavi2 = $('<div class="navi2">共<SPAN class="orange12a" ><span id="spanTotalSize">' + _pageInfo.total
				+ '</span>条记录 ' + '每页显示<INPUT id="txtPageSize" value="' + _pageInfo.pageSize
				+ '" class=box size=2  style="width=30px"> '
				+ '<INPUT id ="btnSetPageSize" class=button_blue type=button value=设置 >'
				+ ' 转到第 <INPUT id="txtTurnPage" class=box size=2 name=textfield style="width=30px"> 页'
				+ ' <INPUT id="btnTurnPage" class=button_blue type=button value=GO> </div>');

		$('#' + pageInfoDivId).append(divNavi2).append(divNavi1)
				.append('&nbsp;&nbsp;&nbsp;&nbsp;<span id="spanOtherFunction"></span>');
		//相应点击  首页 事件
		$('#btnFirst').bind("click", function() {
			query.Page.turnFirst(query.Page);
			$('#spanPageInfo').html(query.Page.getPageInfo().curPage + "/" + query.Page.getPageInfo().end);
		});
		//相应点击  上一页 事件
		$('#btnPre').bind("click", function() {
			query.Page.turnPre();
			$('#spanPageInfo').html(query.Page.getPageInfo().curPage + "/" + query.Page.getPageInfo().end);
		});
		//相应点击  下一页 事件
		$('#btnNext').bind("click", function() {
			query.Page.turnNext();
			$('#spanPageInfo').html(query.Page.getPageInfo().curPage + "/" + query.Page.getPageInfo().end);
		});
		//相应点击  下一页  尾页
		$('#btnEnd').bind("click", function() {
			query.Page.turnEnd();
			$('#spanPageInfo').html(query.Page.getPageInfo().curPage + "/" + query.Page.getPageInfo().end);
		});
		//相应点击  设置页面
		$('#btnSetPageSize').bind("click", function() {
			var pageSize = $("#txtPageSize").val();
			var pageSize = new Number(pageSize);
			if (!isInteger(pageSize)) {
				pageSize = 20;
			}
			query.Page.setPageSize(pageSize);
			query.Page.turnFirst();
			$('#spanPageInfo').html(query.Page.getPageInfo().curPage + "/" + query.Page.getPageInfo().end);
		});
		//相应点击  指定要第N页  尾页
		$('#btnTurnPage').bind("click", function() {
			var toPage = $("#txtTurnPage").val();
			var toPage = new Number(toPage);
			if (!isInteger(toPage)) {
				toPage = 20;
			}
			query.Page.goPage(toPage);
			$("#txtTurnPage").html("");
			$('#spanPageInfo').html(query.Page.getPageInfo().curPage + "/" + query.Page.getPageInfo().end);
		});
	}

	/**
	 * @description 转到第一页
	 * @function
	 * @param
	 * @return
	 */
	var _turnFirst = function() {
		if (_pageInfo.curPage != _pageInfo.first) {
			_pageInfo.curPage = _pageInfo.first;
		}
		//$(this).dispatchJEvent(_listnerName, _pageInfo);
		var cbs = $.Callbacks('memory');
		cbs.add(_listnerName);
		cbs.fire();
	}

	/**
	 * @description 转到尾页
	 * @function
	 * @param
	 * @return
	 */
	var _turnEnd = function() {
		if (!_pageInfo.curPage != _pageInfo.end) {
			_pageInfo.curPage = _pageInfo.end;
		}
		//$(this).dispatchJEvent(_listnerName, _pageInfo);
		var cbs = $.Callbacks('memory');
		cbs.add(_listnerName);
		cbs.fire();		
	}

	/**
	 * @description 转到上页
	 * @function
	 * @param
	 * @return
	 */
	var _turnPre = function() {
		if (_pageInfo.curPage == _pageInfo.first) {
			alert('已经是第一页');
		} else {
			_pageInfo.curPage--;
			var cbs = $.Callbacks('memory');
			cbs.add(_listnerName);
			cbs.fire();
		}
	}

	/**
	 * @description 转到下页
	 * @function
	 * @param
	 * @return
	 */
	var _turnNext = function() {
		if (_pageInfo.curPage == _pageInfo.end) {
			alert('已经是最后一页');
		} else {
			_pageInfo.curPage++;
			//$(this).dispatchJEvent(_listnerName, _pageInfo);
			var cbs = $.Callbacks('memory');
			cbs.add(_listnerName);
			cbs.fire();
		}
	}
	
	/**
	 * @description 判断是否是数字
	 */
	function isInteger(num) {
		var patrn = /^[0-9]*[1-9][0-9]*$/;

		if (!patrn.exec(num))
			return false
		else
			return true
	}
	return {
		init : _init,
		turnFirst : _turnFirst,
		turnEnd : _turnEnd,
		turnPre : _turnPre,
		turnNext : _turnNext,
		getPageInfo : function() {
			return _pageInfo;
		},
		/**
		 * 设置每页多少条记录
		 */
		setPageSize : function(pageSize) {
			_initPageNumber(_pageInfo.total, pageSize);
			$('#spanPageInfo').html(query.Page.getPageInfo().curPage + "/" + query.Page.getPageInfo().end);
		},
		goPage : function(page) {
			//如果页数太多，就用最后一页，如果太少，就用第一页
			if (page > _pageInfo.end) {
				page = _pageInfo.end;
			}
			if (page < _pageInfo.first) {
				page = _pageInfo.first;
			}
			_pageInfo.curPage = page;
			//$(this).dispatchJEvent(_listnerName, _pageInfo);
			var cbs = $.Callbacks('memory');
			cbs.add(_listnerName);
			cbs.fire();

		},
		resetPageInfo : function(totalNumber) {
			_initPageNumber(totalNumber, _pageInfo.pageSize);
			$('#spanPageInfo').html(query.Page.getPageInfo().curPage + "/" + query.Page.getPageInfo().end);
			$("#spanTotalSize").html(totalNumber);
		}
	}
}();