<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/2
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/themes/icon.css">
    <script type="text/javascript" src="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/jquery.easyui.min.js"></script>

    <script>
        $(function () {
            $('#dg').datagrid({
                url:'/user/quan',
                fitColumns:true,
                columns:[[
                    {field:'userId',title:'编号',width:100},
                    {field:'userName',title:'姓名',width:100},
                    {field:'roleName',title:'角色',width:100},
                    {field:'permissionName',title:'权限',width:100}
                ]],
                toolbar: [{
                    iconCls: 'icon-add',
                    text:"赋角色",
                    handler: function(){
                        roleadd();
                    }
                }]

            });
        })

        var active = {
            "aaa":function (userId) {
                $.get("/quan/quan",{'userId':userId},function (data) {

                    var roles = data.roles;
                    console.log(roles);
                    var roleids = data.roleids;
                    console.log(roleids);

                    $("#rolefrom").empty();//清除当前元素中的对象
                    $("#rolefrom").append('<input type="hidden" name="userId" value="'+userId+'"><br>');
                    for (var i=0;i<roles.length;i++){
                        var che = '';
                        if(roleids.indexOf(roles[i].roleId)>-1){
                            che = 'checked';
                        }
                        $("#rolefrom").append('<input type="radio" '+che+' name="roleId" value="'+roles[i].roleId+'" >'+roles[i].roleName+'<br>')
                        /*<input type="checkbox" name="roleId" value="1" >asd <br>
                        * */

                    }
                    //$("#rolefrom").append(' <input type="submit" value="提交">')


                },'json');
            }
        }

        //增加弹框
        function roleadd() {
            var user = $('#dg').datagrid('getSelected');
            console.log("进添加弹框");
            var userId =user.userId;
            active.aaa(userId);



            $('#add').dialog({
                title: '增加角色',
                width: 400,
                height: 200,
                closed: false,
                cache: false,
                modal: true
            });
        }


        //添加提交
        function submitForm(){
            $.messager.progress();	// 显示进度条
            $('#rolefrom').form('submit', {
                url: "/quan/role",
                onSubmit: function(){
                    var isValid = $(this).form('validate');
                    if (!isValid){
                        $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                    }
                    return isValid;	// 返回false终止表单提交
                },
                success: function(data){
                    console.log(data)
                    if(data == 'success'){
                        //close
                        $('#add').dialog("close");
                        //刷新
                        $('#dg').datagrid('reload');
                        //重置表单
                        $('#rolefrom').form("reset");
                    }
                    $.messager.progress('close');	// 如果提交成功则隐藏进度条
                }
            });


        }

        function clearForm(){
            $('#rolefrom').form('clear');
        }
    </script>
</head>
<body>
    <table id="dg"></table>

    <div id="add" style="display: none;">
        <form action="/quan/role" method="post" id="rolefrom">

        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="submitForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="clearForm()">重置</a>
        </div>
    </div>
</body>
</html>
