layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form','layer','upload','ajaxExtention','$tool','$api'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        upload = layui.upload,
        $tool = layui.$tool,
        $api = layui.$api;

    var user_roleIds =[];

    /**
     * 页面初始化
     * */
    function init() {
        //初始化用户信息
        initUserInfo();
        //初始化上传控件
        initUploadComponent();
    }
    init();

    /**
     * 初始化上传控件
     * */
    function initUploadComponent() {
        //执行实例
        var uploadInst = upload.render({
            elem: '#userFaceUpload' //绑定元素
            ,url: $tool.getContext()+'upload' //上传接口
            ,auto: false //选择文件后不自动上传
            ,bindAction: '#userInfoSubmit' //指向一个按钮触发上传
            ,accept:'images'
            ,size:'1024'//限制大小,单位kb
            ,data:{type:'userFace'}//上传业务类型，后台会根据这个值将文件放入相应文件夹下
            ,choose:function (obj) {
                //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                obj.preview(function(index, file, result){
                    //图片预览,result是图片base64编码
                    $('#userFace').css('background-image','url('+result+')');
                });
            }
            ,done: function(res){
                //上传完毕回调
                console.log(res);
            }
            ,error: function(){
                //请求异常回调
                layer.msg("上传失败");
            }
        });
    }

    /**
     * 加载角色列表
     * */
    function loadRoleList() {
        var url = $tool.getContext() + 'personCenter/roleList.do';
        var req = {
            page: 1,
            limit: 999
        };

        $api.GetRoleList(req,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var roleHtml = "";
                for (var i = 0; i < data.length; i++) {
                    //是否初始化选中
                    if($.inArray(data[i].id, user_roleIds) != -1){
                        roleHtml += '<input type="checkbox" disabled="disabled"  checked name="'+data[i].id+'" title="'+data[i].roleName+'">';
                    }else{
                        roleHtml += '<input type="checkbox" disabled="disabled" name="'+data[i].id+'" title="'+data[i].roleName+'">';
                    }
                }

                $('.role-check-list').append($(roleHtml));
                form.render();//重新绘制表单，让修改生效
            }
        });
    }

    /**
     * 初始化用户信息
     * */
    function initUserInfo() {
        var id = sessionStorage.getItem("userId");

        var url = $tool.getContext()+'personCenter/get.do';
        var req = {
            id:id
        };

        $api.GetUserInfo(req,function (res) {
            var data = res.data;
            $("[name='orgId']").val(data.orgId);
            $("[name='orgName']").val(data.orgName);
            $("[name='loginName']").val(data.loginName);
            $("[name='realName']").val(data.realName);
            $("[name='mobile']").val(data.mobile);

            user_roleIds = data.roleIdList;//保存用户所属角色id列表，初始化选中时用
            //加载角色列表
            loadRoleList();
            form.render();//重新绘制表单，让修改生效
        });
    }

    /**
     * 表单提交
     * */
    form.on("submit(editUser)", function (data) {
        var id = sessionStorage.getItem("userId");
        var orgId = data.field.loginName;
        var orgName = data.field.orgName;
        var loginName = data.field.loginName;
        var realName = data.field.realName;
        var mobile = data.field.mobile;
        var idList = user_roleIds;


        //请求
        var url = $tool.getContext() + '/personCenter/update.do';
        var req = {
            id:id,
            loginName: loginName,
            realName: realName,
            mobile: mobile,
            orgId: orgId,
            orgName: orgName,
            roleIdList: idList
        };

        $api.UpdateUserInfo(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("用户更新成功！", {time: 1000}, function () {
                //刷新页面
                location.reload();
            });
        });

        return false;
    })
});