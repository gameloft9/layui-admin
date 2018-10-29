layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['laydate', 'form', 'layer', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        laydate = layui.laydate,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例

    var isAllDel = false;//是否全部删除

    var delList = [];//待删除的id列表，非全部删除时有效

    var notDelList = [];//不删除的id列表，非全部删除时有效

    var queryParams = {//查询条件暂存
        loginName: "",
        operType: "",
        startTime: "",
        endTime: ""
    };

    /**
     * 页面初始化
     * */
    function init() {
        initDate();//初始化日期选择框
    }

    init();

    /**
     * 初始化日期选择
     * */
    function initDate() {
        laydate.render({
            elem: '#operDate'
            , type: 'datetime'
            , range: '&'
            , format: 'yyyy-MM-dd HH:mm:ss'
        });
    }

    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#log-data'
            , height: 395
            , url: $tool.getContext() + 'log/logList.do' //数据接口
            , method: 'post'
            , page: true //开启分页
            , cols: [[ //表头
                {type: "checkbox"}
                , {field: 'loginName', title: '操作者', width: '10%'}
                , {field: 'ipAddr', title: 'ip地址', width: '15%'}
                , {field: 'operType', title: '操作类型', width: '10%'}
                , {field: 'memo', title: '备注', width: '20%'}
                , {field: 'createDate', title: '操作日期', width: '15%'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(logFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                delLog(row.id);
            }
        });
    }

    defineTable();


    //查询
    form.on("submit(queryLog)", function (data) {
        var loginName = data.field.loginName;
        var operType = data.field.operType;
        var operDate = data.field.operDate;
        //拆分出开始结束日期
        var startTime = $.trim((operDate.toString().split("&"))[0]);
        var endTime = $.trim((operDate.toString().split("&"))[1]);

        //保存一份
        queryParams.loginName = loginName;
        queryParams.operType = operType;
        queryParams.startTime = startTime;
        queryParams.endTime = endTime;

        //表格重新加载
        tableIns.reload({
            where: {
                loginName: loginName,
                operType: operType,
                startTime: startTime,
                endTime: endTime
            }
        });

        return false;
    });

    //批量删除
    $(".logBatchDel_btn").click(function () {
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //拿到待删除的id列表
            var ids = getDelIds();
            var notDelIds = getNotDelIds();

            //未选择则不允许删除
            if (ids.length == 0 && notDelIds.length == 0 && isAllDel === false) {
                layer.msg("未选中任何行");
                return;
            }

            //向服务端发送删除指令
            var req = {
                allDel: isAllDel,
                delIds: ids,
                notDelIds: notDelIds,
                loginName: queryParams.loginName,
                operType: queryParams.operType,
                startTime: queryParams.startTime,
                endTime: queryParams.endTime
            };
            batchDelLog(req);
        });
    });

    /**
     * 删除日志
     * @param ids 删除id
     * */
    function delLog(id) {
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.DeleteLog(req,function (data) {
                layer.msg("删除成功", {time: 1000}, function () {
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    /**
     * 批量删除日志
     * 批量删除
     * @param req
     * */
    function batchDelLog(req) {
        //向服务端发送删除指令
        $api.BatchDeleteLog(JSON.stringify(req),{contentType: "application/json;charset=utf-8"},function (data) {
            layer.msg("删除成功", {time: 1000}, function () {
                //obj.del(); //删除对应行（tr）的DOM结构
                //清空列表
                clearParams();
                //重新加载表格
                tableIns.reload();
            });
        });
    }

    //监听表格复选框事件
    table.on('checkbox(logFilter)', function (obj) {
        console.log(obj.checked); //当前是否选中状态
        console.log(obj.data); //选中行的相关数据
        console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one

        if (obj.type === 'all') {//全选
            //清空列表
            delList = [];
            notDelList = [];
            if (obj.checked === true) {
                isAllDel = true;
                return;
            }

            isAllDel = false;
            return;
        } else {//非全选
            isAllDel = false;
            if (obj.checked === true) {//删除列表
                delList.push(obj.data.id);
                return;
            }

            //不删除列表
            notDelList.push(obj.data.id);//记录id
            return;
        }
    });

    /**
     * 拿到待删除的id列表
     * */
    function getDelIds() {
        return delList;
    }

    /**
     * 拿到非删除列表
     * */
    function getNotDelIds() {
        return notDelList;
    }

    /**
     * 清空参数
     * */
    function clearParams(){
       isAllDel = false;//是否全部删除

       delList = [];//待删除的id列表，非全部删除时有效

       notDelList = [];//不删除的id列表，非全部删除时有效

       queryParams = {//查询条件暂存
            loginName: "",
            operType: "",
            startTime: "",
            endTime: ""
        };
    }


});