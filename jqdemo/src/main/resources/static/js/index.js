$(function () {
    $.getJSON("loadIndex_ajax", function (ret) {
        $("#exit").hide();
        if (!ret.errorCode) {
            $("#reg").hide();
            $("#login").hide();
            $("#exit").show();
        }
    })
    $("#exit").on("click", function () {
        $.getJSON("exit_ajax", function () {
            $("#reg").show();
            $("#login").show();
            $("#exit").hide();
        })
    });

    // 首页轮播
    var indexNum = 0, allLen;//当前加载json页数，json长度
    $.getJSON("tryItemVo_ajax", function (data) {
        var ret = data.data;
        allLen = ret.navigateLastPage;//获取json长度

        for (var i = 0; i < allLen; i++) {
            $.getJSON("tryItemVo_ajax", {pno: (i + 1)}, function (data) {
                var dl;
                if (i == 0) {
                    dl = $("<dl>").addClass("on");
                } else {
                    dl = $("<dl>")
                }
                var ret = data.data;
                var list = ret.list;
                list.forEach(function (s) {
                    if (s.category === "大众试用") {
                        if (s.state == "apply") {

                            dl.append(" <dt>\n" +
                                "                <a href=\"detail.html\">\n" +
                                "                    <span class=\"top-tip shoufa\">大众试用</span>\n" +
                                "                    <img src=\"../img/hot4.jpg\" width=\"220\" height=\"130\"/>\n" +
                                "                    <div class=\"hot-con\">\n" +
                                "                        <h2 class=\"name\">" + s.title + "</h2>\n" +
                                "                        <p class=\"tabs red\">\n" +
                                "                            <span>" + s.price + "</span>\n" +
                                "                            <span>" + s.quantity + "</span>\n" +
                                "                        </p>\n" +
                                "                        <p class=\"sq\"><span>" + s.acnt + "</span>申请</p>\n" +
                                "                        <p class=\"current red\">剩余时间" + s.remainDays + "</p>\n" +
                                "                    </div>\n" +
                                "                </a>\n" +
                                "            </dt>")
                        }
                        if (s.state == "try") {
                            dl.append(
                                " <dt>\n" +
                                "                <a href=\"detail.html\">\n" +
                                "                    <span class=\"top-tip shoufa\">大众试用</span>\n" +
                                "                    <img src=\"../img/hot2.jpg\" width=\"220\" height=\"130\"/>\n" +
                                "                    <div class=\"hot-con\">\n" +
                                "                        <h2 class=\"name\">" + s.title + "</h2>\n" +
                                "                        <p class=\"tabs green\">\n" +
                                "                            <span>" + s.price + "</span>\n" +
                                "                            <span>" + s.quantity + "</span>\n" +
                                "                        </p>\n" +
                                "                        <p class=\"sq\"><span>" + s.acnt + "</span>申请</p>\n" +
                                "                        <p class=\"current green\">查看试用名单</p>\n" +
                                "                    </div>\n" +
                                "                </a>\n" +
                                "            </dt>")
                        }
                    }
                    if (s.category === "体验师专享") {
                        if (s.state == "apply") {

                            dl.append(" <dt>\n" +
                                "                <a href=\"detail.html\">\n" +
                                "                    <span class=\"top-tip shoufa\">体验师专享</span>\n" +
                                "                    <img src=\"../img/hot4.jpg\" width=\"220\" height=\"130\"/>\n" +
                                "                    <div class=\"hot-con\">\n" +
                                "                        <h2 class=\"name\">" + s.title + "</h2>\n" +
                                "                        <p class=\"tabs red\">\n" +
                                "                            <span>" + s.price + "</span>\n" +
                                "                            <span>" + s.quantity + "</span>\n" +
                                "                        </p>\n" +
                                "                        <p class=\"sq\"><span>" + s.acnt + "</span>申请</p>\n" +
                                "                        <p class=\"current red\">剩余时间" + s.remainDays + "</p>\n" +
                                "                    </div>\n" +
                                "                </a>\n" +
                                "            </dt>")
                        }
                        if (s.state == "try") {
                            dl.append(
                                " <dt>\n" +
                                "                <a href=\"detail.html\">\n" +
                                "                    <span class=\"top-tip shoufa\">体验师专享</span>\n" +
                                "                    <img src=\"../img/hot2.jpg\" width=\"220\" height=\"130\"/>\n" +
                                "                    <div class=\"hot-con\">\n" +
                                "                        <h2 class=\"name\">" + s.title + "</h2>\n" +
                                "                        <p class=\"tabs green\">\n" +
                                "                            <span>" + s.price + "</span>\n" +
                                "                            <span>" + s.quantity + "</span>\n" +
                                "                        </p>\n" +
                                "                        <p class=\"sq\"><span>" + s.acnt + "</span>申请</p>\n" +
                                "                        <p class=\"current green\">查看试用名单</p>\n" +
                                "                    </div>\n" +
                                "                </a>\n" +
                                "            </dt>")
                        }
                    }

                });
                dl.appendTo($("#try"));
            })
        }
        var list = ret.list;


    });
    // 报告
    //当前加载json页数，json长度
    $.getJSON("sTryReportVL_ajax", function (data) {
        var ret = data.data;
        var data1 = ret.list;
        data1.forEach(function (s) {
            var li = $("<li>");
            var a = $("<a>").appendTo(li);
            $("<img>").attr("src","/upload/"+ s.image).css({width: 200, height: 130}).appendTo(a);
            var info = $("<div>").addClass("info").appendTo(a);
            $("<p>").addClass("name").text(s.title).appendTo(info);
            var fix = $("<div>").addClass("tip fix").appendTo(info);
            $("<span>").addClass("name").text("●"+s.user.name).appendTo(fix);
            var right = $("<div>").addClass("right icon").appendTo(fix);
            $("<span>").addClass("zan").text(s.thumbNum).appendTo(right).data({id:s.id});
            $("<span>").addClass("look").text(s.commentNum).appendTo(right);
            li.appendTo($("#report"));
        });
    });
    $("#report").on("click",'.zan',function () {
        var ts=$(this);
        var reportId=ts.data("id");
        $.getJSON("changeReoprtThumb",{reportId:reportId},function (ret) {
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
    // 导购
    $.getJSON("sGuideVL_ajax", function (data) {
        var ret = data.data;
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
            li.appendTo($("#guide"));
        });
    });

    $("#guide").on("click",'.xin',function () {
        var ts=$(this);
        var guideId=ts.data("id");
        $.getJSON("changeGuideThumb",{guideId:guideId},function (ret) {
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
    // 发现酷玩
    var indexNum = 0, allLen;//当前加载json页数，json长度
    $.getJSON("sCoolIVL_ajax", {th: "new"}, function (data) {
        var self = $("#guideMore");
        self.addClass("loading").html("正在加载中");
        var ret = data.data;
        self.data("pno", 2).data("psize", 12);
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
            var price = s.price == null ? "尚未标价" : s.price;
            $("<span>").addClass("price left").text("￥" + price).appendTo(fix);
            var right = $("<div>").addClass("right icon").appendTo(fix);
            $("<span>").addClass("xin").text(s.thumbNum).appendTo(right).data({id:s.id});
            $("<span>").addClass("look").text(s.commentNum).appendTo(right);
            li.appendTo($("#play"));
        });

        indexNum++;
        if (indexNum >= allLen) {
            self.parent().html('<span class="no-more">没有更多啦~</span>');
            indexNum = 0
        } else {
            self.removeClass("loading").html("点击加载更多");
        }
    });
    $("#guideMore").click(function () {
        var pno = $(this).data("pno");
        var psize = $(this).data("psize");
        $(this).data("pno", pno + 1);
        var self = $(this);
        self.addClass("loading").html("正在加载中");
        $.getJSON("sCoolIVL_ajax", {th: "new", pno: pno, psize: psize}, function (data) {
            var ret = data.data;
            allLen = ret.navigateLastPage;//获取json长度
            var data1 = ret.list;
            data1.forEach(function (s) {
                console.log(s.image);
                var li = $("<li>");
                var a = $("<a>").appendTo(li);
                $("<img>").attr("src", s.image).css({width: 220, height: 130}).appendTo(a);
                var info = $("<div>").addClass("info").appendTo(a);
                var p = $("<p>").addClass("name").text(s.name).appendTo(info);
                $("<span>").text(s.title).appendTo(p);
                var fix = $("<div>").addClass("tip fix").appendTo(info);
                var price = s.price == null ? "尚未标价" : s.price;
                $("<span>").addClass("price left").text("￥" + price).appendTo(fix);
                var right = $("<div>").addClass("right icon").appendTo(fix);
                $("<span>").addClass("xin").text(s.thumbNum).appendTo(right).data({id:s.id});
                $("<span>").addClass("look").text(s.commentNum).appendTo(right);
                li.appendTo($("#play"));
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
    $("#play").on("click",'.xin',function () {
        var ts=$(this);
        var itemId=ts.data("id");
        $.getJSON("selectCoolIT",{itemId:itemId},function (ret) {
            var is=ret.message;
            if(is==="have")
            {
                $.post("deleteCoolIT",{itemId:itemId},function (rets) {
                    alert(rets.message);
                    if(!rets.errorCode)
                    {
                        ts.css({"background-color":"white"});
                        ts.text(Number(ts.text())-1);
                    }
                });
            }else if(is==="no")
            {
                $.post("addCoolIT",{itemId:itemId},function (res) {
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