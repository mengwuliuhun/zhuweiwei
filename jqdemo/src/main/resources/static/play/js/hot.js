$(function () {
    var indexNum = 0, allLen;//当前加载json页数，json长度
    $.getJSON("../../../sCoolIVL_ajax", {th: "hot"}, function (data) {
        var self=$(".comMore");
        self.addClass("loading").html("正在加载中");
        var ret = data.data;
        self.data("pno",2).data("psize",12);
        allLen = ret.navigateLastPage;//获取json长度
        var data1 = ret.list;
        data1.forEach(function (s) {
            var li = $("<li>");
            var a = $("<a>").appendTo(li);
            $("<img>").attr("src", s.image).css({width: 220, height: 130}).appendTo(a);
            var info = $("<div>").addClass("info").appendTo(a);
            var p = $("<p>").addClass("name").text(s.name).appendTo(info);
            $("<span>").text(s.title).appendTo(p);
            var fix = $("<div>").addClass("tip fix").appendTo(info);
            var price=s.price==null?"尚未标价":s.price;
            $("<span>").addClass("price left").text("￥" + price).appendTo(fix);
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
        var param = '';//加载html变量
        self.addClass("loading").html("正在加载中");
        $.getJSON("../../../sCoolIVL_ajax", {th: "hot",pno:pno,psize:psize}, function (data) {
            var ret = data.data;
            allLen = ret.navigateLastPage;//获取json长度
            var data1 = ret.list;
            data1.forEach(function (s) {

                var li = $("<li>");
                var a = $("<a>").appendTo(li);
                $("<img>").attr("src", s.image).css({width: 220, height: 130}).appendTo(a);
                var info = $("<div>").addClass("info").appendTo(a);
                var p = $("<p>").addClass("name").text(s.name).appendTo(info);
                $("<span>").text(s.title).appendTo(p);
                var fix = $("<div>").addClass("tip fix").appendTo(info);
                var price=s.price==null?"尚未标价":s.price;
                $("<span>").addClass("price left").text("￥" + price).appendTo(fix);
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
        var itemId=ts.data("id");
        $.getJSON("../../../selectCoolIT",{itemId:itemId},function (ret) {
            var is=ret.message;
            if(is==="have")
            {
                $.post("../../../deleteCoolIT",{itemId:itemId},function (rets) {
                    alert(rets.message);
                    if(!rets.errorCode)
                    {
                        ts.css({"background-color":"white"});
                        ts.text(Number(ts.text())-1);
                    }
                });
            }else if(is==="no")
            {
                $.post("../../../addCoolIT",{itemId:itemId},function (res) {
                    alert(res.message);
                    if(!res.errorCode)
                    {
                        ts.css({"background-color":"red"});
                        ts.text(Number(ts.text())+1);

                    }
                });
            }else {
                alert(is);
            }

        });
    });
});
