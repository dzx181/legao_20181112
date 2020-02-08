//自适应
$(function () {
    //表头元素居中
    $("th").css("text-align", "center");
    var window_width = $(window).width();
    var window_height = $(window).height();
    $("#content_box").css("width", window_width - 260);
    $("#content_box").css("height", window_height + 1000);

})
//md5加密
var salt = "a1b2c3d4e5";
function md5Util(pass_word) {

    var str = salt.charAt(0) + salt.charAt(2) + pass_word + salt.charAt(4) + salt.charAt(6);
    var md5Pass = md5(str);
    return md5Pass;
}

//日期格式化
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

