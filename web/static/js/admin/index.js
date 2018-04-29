var refreshNav = true;
$(function () {
    initUserInfo();  //获取用户信息
    initNav();  //获取导航栏

    //点击导航切换页面时不刷新导航,其他方式切换页面要刷新导航
    layui.element.on('nav(index-nav)', function (elem) {
        refreshNav = false;
        if (document.body.clientWidth <= 750) {
            switchNav(true);
        }
    });
    //修改密码表单提交事件
    layui.form.on('submit(pswSubmit)', function (data) {
        data.field.token = getToken();
        data.field._method = $("#pswForm").attr("method");
        layer.load(1);
        $.post("api/user/psw", data.field, function (data) {
            if (data.code == 200) {
                layer.msg(data.msg, {icon: 1});
                setTimeout(function () {
                    loginOut();
                }, 1500);
            } else {
                layer.closeAll('loading');
                layer.msg(data.msg, {icon: 2});
            }
        }, "JSON");
        return false;
    });
    layui.form.verify({
        psw2: function (value, item) {
            var newPsw1 = $("#pswForm input[name=newPsw]").val();
            if (value != newPsw1) {
                return '两次输入密码不一致';
            }
        },
        pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
    });
});

//异步加载子页面
function load(path) {
    if (refreshNav) {
        activeNav(path);
    }
    refreshNav = true;
    $("#main-content").load("/" + path + ".html", function () {
        layui.element.render('breadcrumb');
        layui.form.render('select');
    });
}

//获取左侧导航栏
function initNav() {
    var indexNavStr = sessionStorage.getItem("index-nav");
    var indexNav = JSON.parse(indexNavStr);
    var user = getCurrentUser();
    console.log(user);
    if (indexNav == null || indexNav.length === 0) {
        var permissions = [];
        for (var i = 0; i < user.roles.length; i++) {
            var role = user.roles[i];
            var parentPermissions = [];//父菜单
            var subPermissions = [];//子菜单
            for (var j = 0; j < role.permissions.length; j++) {
                var permission = role.permissions[j];
                if (permission.pid === 0) {
                    parentPermissions.push(permission);
                } else {
                    subPermissions.push(permission);
                }
            }
            for (var j = 0; j < parentPermissions.length; j++) {
                var parentPermission = parentPermissions[j];
                for (var k = 0; k < subPermissions.length; k++) {
                    var subPermission = subPermissions[k];
                    if (subPermission.pid === parentPermission.permissionId) {
                        parentPermission.subPermissions.push(subPermission);
                    }
                }
                permissions.push(parentPermission);
            }
        }


        sessionStorage.setItem("index-nav", JSON.stringify(permissions));
        initNav();
    } else {
        layui.laytpl(sideNav.innerHTML).render(indexNav, function (html) {
            $("#index-nav").html(html);
            layui.element.render('nav', 'index-nav');
        });
    }
}

//获取用户信息
function initUserInfo() {
    try {
        var user = getCurrentUser();
        //$("#userHead").attr("src", user.);
        $("#userRealName").text(user.realName);
    } catch (e) {
        console.log(e.message);
    }
}

//退出登录
function loginOut() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    sessionStorage.removeItem("index-nav");
    layer.load(1);
    $.ajax({
        url: "api/login?token=" + getToken(),
        type: "DELETE",
        dataType: "JSON",
        success: function (data) {
            location.replace("login.html");
        }
    });
}

//显示表单弹窗
function updatePwd() {
    layer.open({
        type: 1,
        title: "修改密码",
        area: '400px',
        offset: '120px',
        content: $("#pswModel").html()
    });
    $("#pswForm")[0].reset();
    $("#pswCancel").click(function () {
        layer.closeAll('page');
    });
}
