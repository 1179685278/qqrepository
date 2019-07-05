<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/1
  Time: 11:43
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
                url:'/permission/permissions',
                fitColumns:true,
                columns:[[
                    {field:'permissionId',title:'编号',width:100},
                    {field:'permissionName',title:'权限',width:100}
                ]],
                toolbar: [{
                    iconCls: 'icon-add',
                    text:"添加",
                    handler: function(){
                        add();
                    }
                },'-',{
                    iconCls: 'icon-cancel',
                    text:"删除",
                    handler: function(){
                        deleteForm();
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    text:"修改",
                    handler: function(){
                        fromupdate();
                    }
                }]
            });
        })


        //修改
        function fromupdate() {
            var permission = $('#dg').datagrid('getSelected');

            console.log(permission);



            $('#update').dialog({
                title: '修改页面',
                width: 400,
                height: 240,
                closed: false,
                cache: false,
                //href: 'get_content.php',
                modal: true
            });
            $('#updatefrom').form('load','/permission/permission/'+permission.permissionId);

        }

        //确认修改
        function updateForm(){
            $.messager.progress();	// 显示进度条
            $('#updatefrom').form('submit', {
                url: "/permission/permission",
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
                        $('#update').dialog("close");
                        //刷新
                        $('#dg').datagrid('reload');
                        //重置表单
                        $('#updatefrom').form("reset");
                    }
                    $.messager.progress('close');	// 如果提交成功则隐藏进度条
                }
            });


        }


        // 删除
        function deleteForm(){

            var permission = $('#dg').datagrid('getSelected');
            console.log(permission);

            $.messager.confirm('确认','确定要删除吗？',function(r){
                if (r){
                    $.ajax({
                        url:"/permission/permission",
                        type:"delete",
                        data:{
                            "permissionId":permission.permissionId
                        },
                        success:function(data){
                            if(data == "success"){
                                $.messager.alert('提示','删除成功');
                            }else{
                                $.messager.alert('提示',"删除失败")
                            }
                            $('#dg').datagrid('reload');
                        }
                    })
                }
            });
        }


        //增加弹框
        function add() {
            $('#add').dialog({
                title: '增加',
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
            $('#addfrom').form('submit', {
                url: "/permission/permission",
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
                        $('#addfrom').form("reset");
                    }
                    $.messager.progress('close');	// 如果提交成功则隐藏进度条
                }
            });


        }

        function clearForm(){
            $('#addfrom').form('clear');
        }
    </script>
</head>
<body>
    <table id="dg"></table>

    <div id="add" style="display: none;">
        <form id="addfrom" method="post">
            <table cellpadding="5">
                <tr>
                    <td>权限：</td>
                    <td><input class="easyui-textbox" type="text" name="permissionName" data-options="required:true"></input></td>
                </tr>

            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="submitForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="clearForm()">重置</a>
        </div>
    </div>

    <div id="update" style="display: none;">
        <form id="updatefrom" method="post">
            <input type="hidden" name="_method" value="put">
            <input type="hidden" name="permissionId">
            <table cellpadding="5">
                <tr>
                    <td>权限：</td>
                    <td><input class="easyui-textbox" type="text" name="permissionName" data-options="required:true"></input></td>
                </tr>

            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="updateForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="clearForm()">重置</a>
        </div>
    </div>
</body>
</html>
