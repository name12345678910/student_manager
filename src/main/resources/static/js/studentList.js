var pageCurr;
$(function () {
    layui.use('table', function () {
        var table = layui.table
            , form = layui.form;

        tableIns = table.render({
            id: "studentTableId"
            , elem: '#studentList'
            , url: '/student/getStudentList'
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
                , {field: 'stu_no', title: '学生编号'}
                , {field: 'stu_name', title: '学生姓名'}
                , {field: 'stu_phone', title: '学生电话'}
                , {field: 'class_name', title: '学生班级'}
                , {field: 'major_name', title: '学生专业'}
                , {field: 'dorm_name', title: '宿舍楼名称'}
                , {field: 'dorm_room_no', title: '宿舍编号'}
                , {fixed: 'right', align: 'center', toolbar: '#optBar'}
            ]]
            , done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                // console.log(res);
                //得到当前页码
                // console.log(curr);
                //得到数据总量
                // console.log(count);
                $("[data-field='adminStatus']").children().each(function () {
                    if ($(this).text() == '0') {
                        $(this).text("不可用")
                    } else if ($(this).text() == '1') {
                        $(this).text("可用")
                    }
                });
            }
        });

        //监听工具条
        table.on('tool(studentTable)', function (obj) {
            var data = obj.data;
            $("#appId").val(data.id);
            if (obj.event === 'editStudent') {
                layer.open({
                    type: 2,
                    title: '编辑学生',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['60%', '70%'],
                    content: '/student/toEditStudent?studentId=' + data.id
                });
            }

            if (obj.event === 'lookStudent') {
                layer.open({
                    type: 2,
                    title: '查看管理员',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['60%', '70%'],
                    content: '/student/lookStudent?studentId=' + data.id
                });
            }

            if (obj.event === 'delStudent') {
                layer.confirm('您确定删除吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post("/student/delStudentById", {'studentId': data.id}, function (data) {
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









