/**
 * 首页内容.js
 * */
layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
	ajaxExtention:'ajaxExtention',
	$tool:'tool'
}).use(['form','element','layer','carousel','jquery','ajaxExtention','$tool'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		element = layui.element,
		$ = layui.jquery,
		carousel = layui.carousel,
	    $tool = layui.$tool;

	function init() {
        carousel.render({
            elem: '#car'
            ,width: '100%' //设置容器宽度
            ,height: '400px'
            ,arrow: 'always' //始终显示箭头
        });
    }
    init();

	$(".panel a").on("click",function(){
		window.parent.addTab($(this));
	});


});
