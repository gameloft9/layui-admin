/**
 * 扩展$.ajax,封装统一返回处理
 * Created by gameloft9 on 2017/12/6.
 */
layui.define(['jquery','layer','$tool'],function(exports){
    //拿到模块变量
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $tool = layui.$tool;

    //扩展ajax方法,用来统一处理后端抛出的异常
    (function($){
        //备份jquery的ajax方法
        var _ajax=$.ajax;

        //重写jquery的ajax方法
        $.ajax=function(opt){
            //备份opt中error和success方法
            var fn = {
                error:function(XMLHttpRequest, textStatus, errorThrown){},
                success:function(data, textStatus){},
                complete:function(XMLHttpRequest, textStatus){},
                beforeSend : function(XMLHttpRequest){}
            };
            if(opt.error){fn.error=opt.error;}
            if(opt.success){fn.success=opt.success;}
            if(opt.beforeSend){fn.beforeSend=opt.beforeSend;}
            if(opt.complete){fn.complete=opt.complete;}

            //扩展增强处理
            var _opt = $.extend(opt, {
                //拦截错误返回
                error: function (xhr,textStatus,errorThrown) {
                    if(xhr.status === 401){
                        window.location.href = $tool.getResUrl()+"layuicms/page/system/401.html";
                        return ;
                    }else if(xhr.status === 403){
                        //清空session
                        window.sessionStorage.removeItem("sysUser");
                        window.sessionStorage.removeItem("userId");
                        window.sessionStorage.removeItem("menu");
                        window.sessionStorage.removeItem("curmenu");

                        //window.parent防止在iframe里面跳转到登录页面
                        window.parent.parent.parent.parent.location.href = $tool.getResUrl()+"layuicms/login.html";

                        return ;
                    }else if(xhr.status === 404){
                        window.location.href = $tool.getResUrl()+"layuicms/page/system/404.html";
                        return ;
                    }
                    fn.error(xhr, textStatus, errorThrown);
                },
                //成功返回拦截错误码非'0000'的情况
                success: function (data, textStatus) {
                    if( '0000' !== data.code){//异常的统一处理
                        layer.msg(data.msg);
                        return;
                    }

                    fn.success(data, textStatus);
                },
                beforeSend: function (XMLHttpRequest) {
                    //显示遮罩层(2-图标类型,0.7-阴影程度，详细配置见layui官网)
                    layer.load(2,{shade: 0});
                    XMLHttpRequest.setRequestHeader("token","jkajldfjald;kf");
                    fn.beforeSend(XMLHttpRequest);
                },
                complete: function (XHR, TS) {
                    //请求完成后回调函数 (请求成功或失败之后均调用)。去掉遮罩层。
                    layer.closeAll('loading');
                    fn.complete(XHR, TS);
                }
            });
            return _ajax(_opt);
        };
    })($);

    //输出扩展模块(这里只是扩展ajax，并不需要真正输出模块)
    exports('ajaxExtention',{});
});

