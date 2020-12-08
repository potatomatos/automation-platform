layui.define( [],function (exports) {
    var $=layui.$;
    var layer=layui.layer;
    var request= {
        get: function (url, data, success, error) {
            $.ajax({
                //请求方式
                type: "get",
                //请求地址
                url: url + "?token=" + localStorage.getItem("token") + "&timestamp=" + new Date().getTime(),
                //数据，json字符串
                data: data,
                //请求成功
                success: function (result) {
                    if (result.code === 50008 || result.code === 50014) {
                        layer.msg(result.msg, {icon: 2, shade: this.shade, scrollbar: false, time: 3000, shadeClose: true});
                        location='/login';
                        return;
                    }
                    success(result);
                },
                //请求失败，包含具体的错误信息
                error: function (xhr, textstatus, thrown) {
                    if (error){
                        error(xhr, textstatus, thrown);
                    }else {
                        layer.msg('服务器发生错误', {icon: 5});
                    }
                }
            })
        },
        syncGet: function (url, data, success, error) {
            $.ajax({
                //请求方式
                type: "get",
                //请求地址
                url: url + "?token=" + localStorage.getItem("token") + "&timestamp=" + new Date().getTime(),
                async: false,
                //数据，json字符串
                data: data,
                //请求成功
                success: function (result) {
                    if (result.code === 50008 || result.code === 50014) {
                        layer.msg(result.msg, {icon: 2, shade: this.shade, scrollbar: false, time: 3000, shadeClose: true});
                        location='/login';
                        return;
                    }
                    success(result);
                },
                //请求失败，包含具体的错误信息
                error: function (xhr, textstatus, thrown) {
                    if (error){
                        error(xhr, textstatus, thrown);
                    }else {
                        layer.msg('服务器发生错误', {icon: 5});
                    }
                }
            })
        },
        post: function (url, data, success, error) {
            $.ajax({
                //请求方式
                type: "post",
                //请求地址
                url: url + "?token=" + localStorage.getItem("token") + "&timestamp=" + new Date().getTime(),
                //数据，json字符串
                data: data,
                //请求成功
                success: function (result) {
                    if (result.code === 50008 || result.code === 50014) {
                        layer.msg(result.msg, {icon: 2, shade: this.shade, scrollbar: false, time: 3000, shadeClose: true});
                        location='/login';
                        return;
                    }
                    success(result);
                },
                //请求失败，包含具体的错误信息
                error: function (xhr, textstatus, thrown) {
                    if (error){
                        error(xhr, textstatus, thrown);
                    }else {
                        layer.msg('服务器发生错误', {icon: 5});
                    }
                }
            })
        },
        syncPost: function (url, data, success, error) {
            $.ajax({
                //请求方式
                type: "post",
                //请求地址
                url: url + "?token=" + localStorage.getItem("token") + "&timestamp=" + new Date().getTime(),
                async: false,
                //数据，json字符串
                data: data,
                //请求成功
                success: function (result) {
                    if (result.code === 50008 || result.code === 50014) {
                        layer.msg(result.msg, {icon: 2, shade: this.shade, scrollbar: false, time: 3000, shadeClose: true});
                        location='/login';
                        return;
                    }
                    success(result);
                },
                //请求失败，包含具体的错误信息
                error: function (xhr, textstatus, thrown) {
                    if (error){
                        error(xhr, textstatus, thrown);
                    }else {
                        layer.msg('服务器发生错误', {icon: 5});
                    }
                }
            })
        }
    }
    exports("request", request);
});
