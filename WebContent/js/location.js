/** map:高德地图容器 * */
var map;
/** geolocation:高德地图定位组件 * */
var geolocation;
/** mLocationData:当前用户位置 * */
var mLocationData;
/** mCityInfo:当前用户城市信息 城市名 城市编码等 * */
var mCityInfo;

/** mCallback:回调用户位置信息(经纬度信息及城市信息) * */
var mCallback;

/**
 * 初始化地图及相关组件
 */
function initMap() {
	// 加载地图
	map = new AMap.Map('container-map', {
		resizeEnable : true
	});
	initMapPlugin();
}

/**
 * 初始化地图组件
 */
function initMapPlugin() {
	// 地图容器添加定位组件
	map.plugin('AMap.Geolocation', function() {
		geolocation = new AMap.Geolocation({
			// 配置组件信息
			enableHighAccuracy : true, // 高精度定位
			timeout : 10000, // 超过10秒后停止定位
			buttonOffset : new AMap.Pixel(10, 20), // 设置定位按钮偏移量
			zoomToAccuracy : true, // 定位成功后改变地图视野范围
			buttonPosition : 'RB'
		});
		map.addControl(geolocation);
		geolocation.getCurrentPosition();
		AMap.event.addListener(geolocation, 'complete', onLocationComplete); // 定位成功回调
		AMap.event.addListener(geolocation, 'error', onLocationError); // 定位失败回调
	});

	// 地图工具控件
	AMap.plugin([ 'AMap.ToolBar', 'AMap.Scale' ], function() {
		var toolBar = new AMap.ToolBar();
		var scale = new AMap.Scale();
		map.addControl(toolBar);
		map.addControl(scale);
		toolBar.setOffset(new AMap.Pixel(15, 50)); // 设置组件偏移量 须在组件添加至地图后调用
		scale.setOffset(new AMap.Pixel(15, 50));

	});
}

/**
 * 初始化定位组件
 */
function initGeolocation() {
	// 地图容器添加定位组件
	AMap.plugin('AMap.Geolocation', function() {
		geolocation = new AMap.Geolocation({
			// 配置组件信息
			enableHighAccuracy : true, // 高精度定位
			timeout : 10000, // 超过10秒后停止定位
			buttonOffset : new AMap.Pixel(10, 20), // 设置定位按钮偏移量
			zoomToAccuracy : true, // 定位成功后刁征地图视野范围
			buttonPosition : 'RB'
		});
		geolocation.getCurrentPosition();
		AMap.event.addListener(geolocation, 'complete', onLocationComplete); // 定位成功回调
		AMap.event.addListener(geolocation, 'error', onLocationError); // 定位失败回调
	});
}

/**
 * 定位成功回调
 */
function onLocationComplete(data) {
	mLocationData = data;
	getCityInfoByLatLng();
	mCallback(data, null);
	// alert("定位成功");
}

/**
 * 定位失败回调
 */
function onLocationError(data) {

}

/**
 * 根据用户经纬度 获取所在城市信息
 * 
 * @param {Function}
 *            callback 回调城市信息
 */
function getCityInfoByLatLng() {
	if (null == mLocationData) {
		mCallback(null, null);
		return;
	}
	var geocoder = new AMap.Geocoder({
		radius : 1000,
		extensions : "all"
	});
	geocoder.getAddress(mLocationData.position, function(status, result) {
		if (status == 'complete' && result.info == 'OK') {
			mCityInfo = result.regeocode.addressComponent;
			mCallback(mLocationData, mCityInfo);
		}
	});
}

/**
 * 根据用户IP 获取所在城市信息
 */
function getCityInfoByIP() {
	// 实例化城市查询类
	var citysearch = new AMap.CitySearch();
	// 自动获取用户IP，返回当前城市
	citysearch.getLocalCity(function(status, result) {
		if ('complete' == status && 'OK' == result.info) {
			if (result && result.city) {
				var cityInfo = result.city; // 当前城市信息
				// alert("当前城市" + cityInfo);
			}
		}
	});
}

/**
 * 回调用户位置信息
 * 
 * @param {Function}
 *            callback(location,cityInfo)
 */
function setLocationCallback(callback) {
	mCallback = callback;
}
