$(function () {
    var indexNum = 0, allLen;//当前加载json页数，json长度
    $.getJSON("../tryItemVo_ajax", {category: "体验师专享"}, function (data) {
        var self = $(".comMore");
        self.addClass("loading").html("正在加载中");
        var ret = data.data;
        self.data("pno", 2).data("psize", 4);
        allLen = ret.navigateLastPage;//获取json长度
        var list = ret.list;
        list.forEach(function (s) {
            if (s.state == "apply") {
                $("#dl").append(" <dt>\n" +
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
                $("#dl").append(
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
                    "                        <p class=\"sq\"><span>"+s.acnt+"</span>申请</p>\n" +
                    "                        <p class=\"current green\">查看试用名单</p>\n" +
                    "                    </div>\n" +
                    "                </a>\n" +
                    "            </dt>")
            }
            if (s.state == "end") {
                $("#dl").append("<dt>\n" +
                    "                <a href=\"detail.html\">\n" +
                    "                    <span class=\"top-tip tys\">体验师专享</span>\n" +
                    "                    <img src=\"../img/hot3.jpg\" width=\"220\" height=\"130\"/>\n" +
                    "                    <div class=\"hot-con\">\n" +
                    "                        <h2 class=\"name\">" + s.title + "</h2>\n" +
                    "                        <p class=\"tabs graw\">\n" +
                    "                            <span>" + s.price + "</span>\n" +
                    "                            <span>" + s.quantity + "</span>\n" +
                    "                        </p>\n" +
                    "                        <p class=\"sq\"><span>" + s.acnt + "</span>申请</p>\n" +
                    "                        <p class=\"current graw\">报告数量：" + s.rcnt + "</p>\n" +
                    "                    </div>\n" +
                    "                </a>\n" +
                    "            </dt>");
            }
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
        self.addClass("loading").html("正在加载中");
        $.getJSON("../tryItemVo_ajax", {category: "体验师专享", pno: pno, psize: psize}, function (data) {
            var ret = data.data;
            allLen = ret.navigateLastPage;//获取json长度
            var list = ret.list;
            list.forEach(function (s) {
                if (s.state == "apply") {
                    $("#dl").append(" <dt>\n" +
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
                    $("#dl").append(
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
                        "                        <p class=\"sq\"><span>"+s.acnt+"</span>申请</p>\n" +
                        "                        <p class=\"current green\">查看试用名单</p>\n" +
                        "                    </div>\n" +
                        "                </a>\n" +
                        "            </dt>")
                }
                if (s.state == "end") {
                    $("#dl").append("<dt>\n" +
                        "                <a href=\"detail.html\">\n" +
                        "                    <span class=\"top-tip tys\">体验师专享</span>\n" +
                        "                    <img src=\"../img/hot3.jpg\" width=\"220\" height=\"130\"/>\n" +
                        "                    <div class=\"hot-con\">\n" +
                        "                        <h2 class=\"name\">" + s.title + "</h2>\n" +
                        "                        <p class=\"tabs graw\">\n" +
                        "                            <span>" + s.price + "</span>\n" +
                        "                            <span>" + s.quantity + "</span>\n" +
                        "                        </p>\n" +
                        "                        <p class=\"sq\"><span>" + s.acnt + "</span>申请</p>\n" +
                        "                        <p class=\"current graw\">报告数量：" + s.rcnt + "</p>\n" +
                        "                    </div>\n" +
                        "                </a>\n" +
                        "            </dt>");
                }
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
});
