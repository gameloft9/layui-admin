/**
 * Created by gameloft9 on 2017/12/19.
 */
layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['laydate', 'form', 'layer', 'tree', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        laydate = layui.laydate,
        table = layui.table,
        $api = layui.$api;


    var orgNodes = [];//保存机构节点，所有要使用的均拷贝一份再处理

    /**
     * 页面初始化
     * */
    function init() {
        initOrgTree();//初始化组织机构树
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
                fillOrgInfo(node.id);
            }
        });
    }

    /**
     * 填充组织机构信息
     * */
    function fillOrgInfo(id) {
        var url = $tool.getContext() + 'org/get.do';
        var req = {
            id: id
        };

        $api.GetOrg(req,function (res) {
            var info = res.data;
            $('[name="id"]').val(info.id);
            $('[name="orgName"]').val(info.organizeName);
            $('[name="orgCode"]').val(info.organizeCode);
            $('[name="treePath"]').val(info.treePath);
        });
    }

    //更新
    form.on("submit(editOrg)", function (data) {
        var organizeName = data.field.orgName;
        var id = data.field.id;
        var url = $tool.getContext() + 'org/update.do';
        var req = {
            id: id,
            organizeName: organizeName
        };

        $api.UpdateOrg(req,function (res) {
            layer.msg("更新成功！", {time: 1000}, function () {
                window.location.reload();//刷新
            });
        });

        return false;
    });

    //删除
    form.on("submit(delOrg)", function (data) {
        var id = data.field.id;

        var url = $tool.getContext() + 'org/delete.do';
        var req = {
            id: id
        };

        $api.DeleteOrg(req,function (res) {
            layer.msg("删除成功！", {time: 1000}, function () {
                window.location.reload();//刷新
            });
        });

        return false;
    });

    //查询
    form.on("submit(queryOrg)", function (data) {
        var organizeName = data.field.organizeName;

        var find = findCursively(organizeName, orgNodes);
        if (find) {
            // 设置展开
            var nodes = setExpand(find.path,orgNodes);

            //重绘树
            renderTree('#org-tree', nodes);
            return false;
        }

        layer.msg("未找到该组织机构");
        return false;
    });

    //添加机构
    $(".add_btn").click(function () {
        var index = layui.layer.open({
            title: "添加机构",
            type: 2,
            content: "addOrg.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回机构列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });

        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

    /**
     * 递归查找
     * @param name 节点名称
     * @param 节点列表
     * @return false-没有找到，node-节点
     * */
    function findCursively(name, list) {
        for (var i = 0; i < list.length; i++) {
            if (name === list[i].name) {
                return list[i];
            }

            if ($tool.isEmpty(list[i].children)) {//没有子节点，继续
                continue;
            }

            return findCursively(name, list[i].children);//有子节点，递归
        }

        return false;
    }

    /**
     * 设置展开
     * */
    function setExpand(treePath,nodes) {
        var resNodes = $tool.jsonClone(nodes); // 拷贝一份，避免原数组被修改
        var paths = treePath.split('#');
        for(var i = 0;i<paths.length-1;i++){
            setExpandInner(paths[i],resNodes);
        }

        return resNodes;
    }

    /**
     * 设置展开的递归函数
     * */
    function setExpandInner(id,nodes){
        for (var i = 0; i < nodes.length; i++) {
            if (id === nodes[i].id) {
                nodes[i].spread = true;
                return ;
            }

            if ($tool.isEmpty(nodes[i].children)) {//没有子节点，继续
                continue;
            }

            return setExpandInner(id, nodes[i].children);//有子节点，递归
        }
    }

});