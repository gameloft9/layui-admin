layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api',
    $sha1:'../../lib/sha1/sha1'//加密工具
}).use(['form','layer','ajaxExtention','$tool','$sha1','$api'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        $tool = layui.$tool,
        $sha1 = layui.$sha1,
        $api = layui.$api;


    //添加验证规则
    form.verify({
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    });

    //修改密码
    form.on("submit(changePwd)",function(data){
        var newPwd = data.field.newPwd;
        var oldPwd = data.field.oldPwd;

        oldPwd = $sha1.hex_sha1(oldPwd);
        newPwd = $sha1.hex_sha1(newPwd);
        //向服务端发送指令
        var url = $tool.getContext() + '/personCenter/changePwd.do';
        var req = {
            loginName: sessionStorage.getItem("sysUser"),
            oldPwd:oldPwd,
            newPwd:newPwd
        };

        $api.ChangePwd(req,function (res) {
            layer.msg("修改成功");
        });

        return false;
    });

});