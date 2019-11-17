$(function () {
    var indexNum = 0, allLen;//当前加载json页数，json长度
    $.getJSON("../../../sTryReportVL_ajax", {th: "new"}, function (data) {
        var self = $(".comMore");
        self.addClass("loading").html("正在加载中");
        var ret = data.data;
        self.data("pno", 2).data("psize", 12);
        allLen = ret.navigateLastPage;//获取json长度
        var data1 = ret.list;
        data1.forEach(function (s) {
            var li = $("<li>");
            var a = $("<a>").appendTo(li);
            $("<img>").attr("src", "/upload/"+s.image).css({width: 700, height: 412}).appendTo(a);
            var info = $("<div>").addClass("info").appendTo(a);
            $("<p>").addClass("title").text(s.title).appendTo(info);
            var fix = $("<div>").addClass("btm fix").appendTo(info);
            var left = $("<div>").addClass("userInfo left").appendTo(fix);
            $("<span>").addClass("avt").css({"background-image":"url(/upload/"+s.user.image+")"}).appendTo(left);
            $("<span>").addClass("name").text(s.user.name).appendTo(left);
            $("<span>").addClass("time").text(s.pubDate).appendTo(left);
            var right = $("<div>").addClass("right icon").appendTo(fix);
            $("<span>").addClass("zan").text(s.thumbNum).appendTo(right).data({id:s.id});
            $("<span>").addClass("look").text(s.commentNum).appendTo(right);
            li.appendTo("#myDiv");
            li=$("<li>");
            a = $("<a>").appendTo(li);
            $("<div>").addClass("click-look").text("关于"+s.vo.title+"有"+s.vo.rcnt+"篇报告,点击查看").appendTo(a);
            li.appendTo("#myDiv");
        });

        indexNum++;
        if (indexNum >= allLen) {
            self.parent().html('<span class="no-more">没有更多啦~</span>');
            indexNum = 0
        } else {
            self.removeClass("loading").html("点击加载更多");
        }
    });
    /**
     * @首页酷玩&&&酷玩页面json
     */
    $(".comMore").click(function () {
        var pno = $(this).data("pno");
        var psize = $(this).data("psize");
        $(this).data("pno", pno + 1);
        var self = $(this);
        var param = '';//加载html变量
        self.addClass("loading").html("正在加载中");
        $.getJSON("../../../sTryReportVL_ajax", {th: "new", pno: pno, psize: psize}, function (data) {
            var ret = data.data;
            allLen = ret.navigateLastPage;//获取json长度
            var data1 = ret.list;
            data1.forEach(function (s) {
                var li = $("<li>");
                var a = $("<a>").appendTo(li);
                $("<img>").attr("src", s.image).css({width: 700, height: 412}).appendTo(a);
                var info = $("<div>").addClass("info").appendTo(a);
                $("<p>").addClass("title").text(s.title).appendTo(info);
                var fix = $("<div>").addClass("btm fix").appendTo(info);
                var left = $("<div>").addClass("userInfo left").appendTo(fix);
                $("<span>").addClass("avt").appendTo(left);
                $("<span>").addClass("name").text(s.user.name).appendTo(left);
                $("<span>").addClass("time").text(s.pubDate).appendTo(left);
                var right = $("<div>").addClass("right icon").appendTo(fix);
                $("<span>").addClass("zan").text(s.thumbNum).appendTo(right).data({id:s.id});
                $("<span>").addClass("look").text(s.commentNum).appendTo(right);
                li.appendTo("#myDiv");
                li=$("<li>");
                a = $("<a>").appendTo(li);
                $("<div>").addClass("click-look").text("关于"+s.vo.title+"有"+s.vo.rcnt+"篇报告,点击查看").appendTo(a);
                li.appendTo("#myDiv");
            });

            indexNum++;
            if (indexNum >= allLen) {
                self.parent().html('<span class="no-more">没有更多啦~</span>');
                indexNum = 0
            } else {
                self.removeClass("loading").html("点击加载更多");
            }
        });
    });
    $("#myDiv").on("click",'.zan',function () {
        var ts=$(this);
        var reportId=ts.data("id");
        $.getJSON("../../../changeReoprtThumb",{reportId:reportId},function (ret) {
            alert(ret.message);
            if(ret.message==="点赞成功")
            {
                ts.css({"background-color":"red"});
                ts.text(Number(ts.text())+1);
            }
            if(ret.message==="取消点赞成功")
            {
                ts.css({"background-color":"white"});
                ts.text(Number(ts.text())-1);
            }
        });
    });
});
