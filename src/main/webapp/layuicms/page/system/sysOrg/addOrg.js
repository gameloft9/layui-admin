layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer','tree', 'jquery', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    var pId;//父机构id，添加时用

    /**
     * 页面初始化
     * */
    function init() {
        //初始化机构树
        initOrgTree();
    }
    init();

    /**
     * 初始化组织机构树
     * */
    function initOrgTree() {
        //获取所有组织机构树

        $api.GetAllOrg(null,function (res) {
            orgNodes = res.data;//保存一份
            renderTree('#org-tree', res.data);
        });

    }

    /**
     * 绘制树
     * @param id dom id
     * @param nodes 树节点数据
     * */
    function renderTree(id, nodes) {
        //绘制前先清空
        $(id).empty();

        //绘制
        layui.tree({
            elem: id
            , nodes: nodes
            , click: function (node) {//显示组织机构数据
                console.log(node); //node即为当前点击的节点数据
                pId = node.id;//保存机构id
            }
        });
    }

    /**
     * 监听radio选择
     * */
    form.on('radio(orgTypeFilter)', function (data) {
        //console.log(data.elem); //得到radio原始DOM对象
        var value = data.value;
        if ('2' === value) {//二级菜单
            $('.parent-org').removeClass('layui-hide');
            $('.parent-org').addClass('layui-anim-up');
        }else{
            $('.parent-org').addClass('layui-hide');
            $('.parent-org').removeClass('layui-anim-up');
        }
    });

    /**
     * 表单提交
     * */
    form.on("submit(addOrg)", function (data) {
        var orgName = data.field.orgName;
        var parentId = pId;

        //请求
        var req = {
            organizeName:orgName,
            parentId:parentId
        };

        $api.AddOrg(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("机构添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


