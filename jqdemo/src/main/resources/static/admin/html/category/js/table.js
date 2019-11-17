$(function () {
    var name=$("#name").val();
    var category=$("#category").val();
    var pnos=1;
    $.getJSON("../../../admin/coolCategory",function (ret) {
        result(ret);
    });
    $("#ul").on("click",'li a',function () {
        if(!isNaN($(this).data("pno")))
        {
            pnos=$(this).data("pno");
            var phone=$("#phone").val();
            var pno=$(this).data("pno");
            $.getJSON("../../../admin/coolCategory",{name:name,category:category,pno:pno},function (ret) {
                $("#ul").empty();
                $("#tbody").empty();
                result(ret);
            });
        }

    });
    $("#search").on("click",function () {
        name=$("#name").val();
        category=$("#category").val();
        $.getJSON("../../../admin/coolCategory",{name:name,category:category,pno:pnos},function (ret) {
            $("#ul").empty();
            $("#tbody").empty();
            result(ret);
        });
    });
    $("#tbody").on("click",'tr td button',function () {
        window.location="form.html?id="+$(this).data("id");
    });
    var result=function(ret)
    {
        var data=ret.data;
        var list=data.list;
        list.forEach(function (s) {
            var tr=$("<tr>");
            $("<td>").text(s.id).appendTo(tr);
            $("<td>").text(s.name).appendTo(tr);
            $("<td>").text(s.category).appendTo(tr);
            $("<td>").text(s.quantity).appendTo(tr);
            var tm=$("<td>");
            if(s.image===null||s.image==="")
            {
                tm.text("暂无图片");
            }
            else {
                $("<img>").attr({src:s.image,height:'80px'}).appendTo(tm);
            }
            tm.appendTo(tr);
            var td=$("<td>");

            $("<button>").addClass("btn btn-primary btn-xs").text("修改").data({id:s.id}).appendTo(td);
            td.appendTo(tr);
            tr.appendTo($("#tbody"));
        });
        var ul=$("#ul");
        var li;
        if(data.pageNum>1)
        {
            li=$("<li>");
            $("<a>").text("<<").data({pno:(data.navigateFirstPage-1)}).appendTo(li);
            li.appendTo(ul);
        }else {
            li=$("<li>").addClass("disabled");
            $("<a>").text("<<").data({pno:(data.navigateFirstPage-1)}).appendTo(li);
            li.appendTo(ul);
        }
        var npn=data.navigatepageNums;
        npn.forEach(function (s) {
            if(s===data.pageNum)
            {
                li=$("<li>").addClass("active");
                $("<a>").text(s).appendTo(li);
                li.appendTo(ul);
            }else {
                li=$("<li>");
                $("<a>").text(s).data({pno:s}).appendTo(li);
                li.appendTo(ul);
            }


        });
        if(data.pageNum<data.pages)
        {
            li=$("<li>");
            $("<a>").text(">>").data({pno:(data.navigateLastPage+1)}).appendTo(li);
            li.appendTo(ul);
        }else {
            li=$("<li>").addClass("disabled");
            $("<a>").text(">>").appendTo(li);
            li.appendTo(ul);
        }
    }
});