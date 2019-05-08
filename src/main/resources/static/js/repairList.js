var pageCurr;
$(function () {
    layui.use('table', function () {
        var table = layui.table
            , form = layui.form;

        tableIns = table.render({
            id: "visitorTableId"
            , elem: '#visitorList'
            , url: '/repair/getRepairList'
            , method: 'get' //默认：get请求
            , cellMinWidth: 80
            , page: true
            , request: {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'limit' //每页数据量的参数名，默认：limit
            }, response: {
                statusName: 'code' //数据状态的字段名称，默认：code
                , statusCode: 200 //成功的状态码，默认：0
                , countName: 'totals' //数据总数的字段名称，默认：count
                , dataName: 'list' //数据列表的字段名称，默认：data
            }
            , cols: [[ //表头
                {type: 'checkbox'}
                , {field: 'id', title: 'id', sort: true}
                , {field: 'dormRoomId', title: '宿舍编号'}
                , {field: 'repairName', title: '报修人'}
                , {field: 'repairPhone', title: '报修号码'}
                , {field: 'repairTime', title: '报修时间'}
                , {field: 'repaitDescription', title: '报修描述'}
                , {field: 'status', title: '处理状态',templet: function (item) {
                    if (item.status == '0') {
                        return "未处理";
                } else { return "已处理";}}}
                , {fixed: 'right', align: 'center', toolbar: '#optBar'}
            ]]
        });

        //监听工具条
        table.on('tool(visitorTable)', function (obj) {
            var data = obj.data;
            $("#appId").val(data.id);
            if (obj.event === 'editVisitor') {
                layer.open({
                    type: 2,
                    title: '编辑报修信息',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['60%', '70%'],
                    content: '/repair/toEditRepair?repairId=' + data.id
                });
            }

            if (obj.event === 'lookVisitor') {
                layer.open({
                    type: 2,
                    title: '查看报修信息',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['60%', '70%'],
                    content: '/repair/lookRepair?repairId=' + data.id
                });
            }

            if (obj.event === 'delVisitor') {
                layer.confirm('您确定删除吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post("/repair/delRepairById", {'repairId': data.id}, function (data) {
                        if (data.success) {
                            layer.alert("删除成功", function (index) {
                                layer.close(index); //再执行关闭
                                load(obj);
                            });
                        } else {
                            layer.alert(data.message);
                        }
                    });
                }, function () {
                    return;
                });
            }
        });
    });
    //搜索框
    layui.use(['form', 'laydate'], function () {
        var form = layui.form, layer = layui.layer
            , laydate = layui.laydate;
        //TODO 数据校验
        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            //重新加载table
            load(data);
            return false;
        });
    });
});

function Format(datetime, fmt) {
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime) * 1000;
        } else if (datetime.length == 13) {
            datetime = parseInt(datetime);
        }
    }
    datetime = new Date(datetime);
    var o = {
        "M+": datetime.getMonth() + 1,                 //月份
        "d+": datetime.getDate(),                    //日
        "h+": datetime.getHours(),                   //小时
        "m+": datetime.getMinutes(),                 //分
        "s+": datetime.getSeconds(),                 //秒
        "q+": Math.floor((datetime.getMonth() + 3) / 3), //季度
        "S": datetime.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function load(obj) {
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}









