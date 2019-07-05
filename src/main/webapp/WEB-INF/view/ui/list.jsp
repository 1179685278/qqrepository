<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/14
  Time: 11:44
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

       


        function add(tit,url){

            var  esx = $('#tabs').tabs('exists',tit)
            if(!esx){
                $('#tabs').tabs('add',{
                    title:tit,
                    content:'<iframe style="width:100%;height:100%;position:relative;" src="'+url+'" frameborder="0" scrolling="true" ></iframe>',
                    closable:true,
                    tools:[{
                        iconCls:'icon-mini-refresh',
                        handler:function(){
                            alert('refresh');
                        }
                    }]
                });
            }else {
                $('#tabs').tabs('select',tit)
            }

        }

    </script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',title:'North Title',split:true"  style="height:150px;width: 300px">

        <h1 align="center">登录权限</h1>
    </div>
    <div data-options="region:'west',title:'West',split:true" style="width:200px;">
        
        <ul id="tree" class="easyui-tree">
            <li>
                <span>
                    <span><a href="javascript:add('用户管理','http://localhost:8081/user/toselectpage')">用户管理</a></span>
                </span>
            </li>
            <li>
                <span>
                    <span><a href="javascript:add('角色管理','http://localhost:8081/role/torolepage')">角色管理</a></span>
                </span>
            </li>
            <li>
                <span>
                    <span><a href="javascript:add('权限管理','http://localhost:8081/permission/topermissionpage')">权限管理</a></span>
                </span>
            </li>
            <li>
                <span>
                    <span><a href="javascript:add('全管理','http://localhost:8081/user/toquanpage')">全管理</a></span>
                </span>
            </li>

        </ul>
    </div>
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
        <div id="tabs" class="easyui-tabs" data-options="fit:true">
            <div id="hy"  title="欢迎页面" data-options="closable:true" style="padding:20px;display:none;">
                欢迎页面
            </div>
        </div>
    </div>
</body>
</html>
