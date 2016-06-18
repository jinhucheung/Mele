/**
 * 数据处理工具
 * 
 */
var dir_img_stores = "http://localhost:8080/mele/data/img/stores/";
var dir_img_foodmenu = "http://localhost:8080/mele/data/img/foodmenu/";
var dir_img_user = "http://localhost:8080/mele/data/img/user/";

/**
 * 构建商家信息块
 * 
 * @param storeId
 * @param imgSrc
 * @param storeName
 * @param startPrice
 * @param transportPrice
 * @param distance
 * @param orderNums
 * @returns {String}
 */
function build_storeDiv(storeId, imgSrc, storeName, startPrice, transportPrice,
		distance, orderNums) {
	var storeDiv = "<section class='rstblock clearfix'>"//
			+ "<div class='leftArea'>"//
			+ "<img"//
			+ " src='" + dir_img_stores + imgSrc//
			+ "'>"//
			+ "<span class='distance'>"//
			+ distance + "米"//
			+ "</span>"//
			+ "</div>"//
			+ "<a href='client.html?"//
			+ "id=" + storeId//
			+ "'>"//
			+ "<div class='rightArea'>"//
			+ "<h4 class='rstblock-name'>"//
			+ storeName//
			+ "</h4>"//
			+ "<div class='rstblock-monthsales'>"//
			+ "已售" + orderNums + "单"//
			+ "</div>"//
			+ "<div class='rstblock-cost'>"//
			+ startPrice + "元起送/" + transportPrice + "元配送"//
			+ "<div class='rstblock-activity'>"//
			+ "<span class='iconsub'>减</span>"//
			+ "<span class='iconfirst'>首</span>"//
			+ "<spanclass='iconbuy'>付</span>"//
			+ "</div>"//
			+ "</a>"//
			+ "</section>";
	return storeDiv;
}

/**
 * 构建商家菜式分类标签块
 * 
 * @param labelId
 * @param lableName
 */
function build_storeMenu_typeLabels_li(labelId, lableName) {
	var typeLabel_li = "<li role='presentation'>"//
			+ "<a href='#"//
			+ labelId//
			+ "'>"//
			+ lableName//
			+ "</a>"//
			+ "</li>";
	return typeLabel_li;
}

function build_storeMenu_infoLabels_div(labelId) {
	var infoLables_div = "<div class='panel right-nav-item'><div class='panel-heading'><h3 id='"
			+ labelId
			+ "' class='panel-title'>餐厅公告</h3>"
			+ "</div><ul class='list-group'><li class='list-group-item'><p>餐厅咨询电话 611350,661725</p><p class='sale-price'>&yen0</p> <span"
			+ "	class='glyphicon glyphicon-plus bottom-icon' aria-hidden='true'></span></li><li class='list-group-item'><p>餐厅咨询电话 611350,661725</p>"
			+ "<p class='sale-price'>&yen0</p> <span class='glyphicon glyphicon-plus bottom-icon' aria-hidden='true'></span>"
			+ "	</li></ul></div>";
	return infoLables_div;
}

/**
 * 构建用户收货地址区域
 * 
 * @param contact
 * @param tel
 * @param address
 */
function build_userAddress_info_div(addressId, contact, tel, address) {
	var mt = "1" != addressId ? " mt" : "";
	var infoDiv = "<div class='address-demail"//
			+ mt//
			+ "'>"//
			+ "<ul>"//
			+ "<li class='clearfix'>"//
			+ "<div class='address-item'>"//
			+ "<p>"//
			+ "<span>" + contact + "</span>"//
			+ "<span>" + tel + "</span>"//
			+ "</p>"//
			+ "<p>" + address + "</p>"//
			+ "</div>"//
			+ "<a href='#"//
			+ addressId//
			+ "' class='img-right'>"//
			+ "<img src='images/ic_pan.png'>"//
			+ "</a>"//
			+ "</li>"//
			+ "</ul>"//
			+ "</div>";
	return infoDiv;
}

/**
 * 构建订单信息区域
 */
function build_orderInfo_div(divId, orderCode, orderTime, storeInfo, foods,
		foodNumArray, totalPrice) {
	var orderInfoDiv = "<div class='order-info' "//
			+ "id='" + divId + "' "//
			+ "value='" + orderCode + "'>"//
			+ "<div class='order-info-head'>"//
			+ "<p>"//
			+ "&nbsp;&nbsp;订单已完成&nbsp; <span class='order-info-head-time'>"//
			+ orderTime//
			+ "</span>"//
			+ "</p>"//
			+ "<a class='order-recycle' href='#'>"//
			+ "<img src='images/recycle.png' alt='删除'/>"//
			+ "</a>"//
			+ "</div>"//
			+ "<div class='order-detail'>"//
			+ "<div class='order-detail-left'>"//
			+ "<img src='" + dir_img_stores + storeInfo.storeLogo + "'>"//
			+ "</div>"//
			+ "<div class='order-detail-right'>"//
			+ "<a href='#'>&nbsp;" + storeInfo.storeName + "&nbsp;&gt</a>";//
	for (var i = 0; i < foods.length; i++) {
		orderInfoDiv += "<div>"//
				+ "<span>&nbsp;" + foods[i].foodName + "</span>"//
				+ "<span class='num'>" + foods[i].foodPrices + "&times;"
				+ foodNumArray[i] + "&nbsp;&nbsp;</span>"//
				+ "</div>";
	}
	orderInfoDiv += "<div class='order-money'>"//
			+ "共" + foods.length + "份，实付"//
			+ "<span>&yen;" + totalPrice + "</span>"//
			+ "</div>"//
			+ "</div>"//
			+ "</div>"//
			+ "</div>";
	return orderInfoDiv;
}

/**
 * 保存cookie
 * 
 * @param c_name
 * @param value
 * @param expiredays
 */
function set_Cookie(c_name, value) {
	document.cookie = c_name + "=" + escape(value);
}

/**
 * 获取cookie
 * 
 * @param c_name
 * @returns
 */
function get_Cookie(c_name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end))
		}
	}
	return null;
}

/**
 * 获取url中的参数值
 * 
 * @param name
 *            键
 * @returns 值
 */
function get_url_param(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURI(r[2]);
	return null;
}

/**
 * 获取订单中菜式价格数量
 * 
 * @param foodPriceNum
 * @param numArray
 * @param priceArray
 */
function get_order_foods_price_nums(foodPriceNum, priceArray, numArray) {
	var priceNum = foodPriceNum.split(";");
	for (var i = 0; i < priceNum.length; i++) {
		if ("" != priceNum[i]) {
			var result = priceNum[i].split("x");
			priceArray.push(result[0]);
			numArray.push(result[1]);
		}
	}
}

/**
 * 计算订单总价
 * 
 * @param numArray
 * @param priceArray
 */
function cal_order_foods_total_price(priceArray, numArray) {
	var totalPrice = 0.0;
	for (var i = 0; i < numArray.length; i++) {
		totalPrice += parseFloat(priceArray[i]) * parseInt(numArray[i]);
	}
	return totalPrice;
}
