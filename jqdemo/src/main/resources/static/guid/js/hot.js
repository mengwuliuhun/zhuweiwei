$(function () {
    var indexNum = 0, allLen;//当前加载json页数，json长度
    $.getJSON("../../../sGuideVL_ajax", {th: "hot"}, function (data) {
        var self=$(".comMore");
        self.addClass("loading").html("正在加载中");
        var ret = data.data;
        self.data("pno",2).data("psize",12);
        allLen = ret.navigateLastPage;//获取json长度
        var data1 = ret.list;
        data1.forEach(function (s) {
            var li = $("<li>");
            var a = $("<a>").appendTo(li);
            $("<img>").attr("src", s.image).attr({href:"detail.html"}).css({width: 220, height: 130}).appendTo(a);
            var info = $("<div>").addClass("info").appendTo(a);
            var p = $("<p>").addClass("name").text(s.title).appendTo(info);
            var fix=$("<div>").addClass("tip fix").appendTo(info);
            var right = $("<div>").addClass("right icon").appendTo(fix);
            $("<span>").addClass("xin").text(s.thumbNum).appendTo(right).data({id:s.id});
            $("<span>").addClass("look").text(s.commentNum).appendTo(right);
            li.appendTo("#myDiv");
        })

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
        var pno=$(this).data("pno");
        var psize=$(this).data("psize");
        $(this).data("pno",pno+1);
        var self = $(this);
        self.addClass("loading").html("正在加载中");
        $.getJSON("../../../sGuideVL_ajax", {th: "hot",pno:pno,psize:psize}, function (data) {
            var ret = data.data;
            allLen = ret.navigateLastPage;//获取json长度
            var data1 = ret.list;
            data1.forEach(function (s) {
                var li = $("<li>");
                var a = $("<a>").appendTo(li);
                $("<img>").attr("src", s.image).attr({href:"detail.html"}).css({width: 220, height: 130}).appendTo(a);
                var info = $("<div>").addClass("info").appendTo(a);
                var p = $("<p>").addClass("name").text(s.title).appendTo(info);
                var fix=$("<div>").addClass("tip fix").appendTo(info);
                var right = $("<div>").addClass("right icon").appendTo(fix);
                $("<span>").addClass("xin").text(s.thumbNum).appendTo(right).data({id:s.id});
                $("<span>").addClass("look").text(s.commentNum).appendTo(right);
                li.appendTo("#myDiv");
            })

            indexNum++;
            if (indexNum >= allLen) {
                self.parent().html('<span class="no-more">没有更多啦~</span>');
                indexNum = 0
            } else {
                self.removeClass("loading").html("点击加载更多");
            }
        });
    });
    $("#myDiv").on("click",'.xin',function () {
        var ts=$(this);
        var guideId=ts.data("id");
        $.getJSON("../../../changeGuideThumb",{guideId:guideId},function (ret) {
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
