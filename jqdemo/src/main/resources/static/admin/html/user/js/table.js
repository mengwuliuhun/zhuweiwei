$(function () {
    var name=$("#name").val();
    var phone=$("#phone").val();
    var pnos=1;
    $.getJSON("../../../admin/user",{name:name,phone:phone},function (ret) {
        result(ret);
    });
    $("#ul").on("click",'li a',function () {
        if(!isNaN($(this).data("pno")))
        {
            pnos=$(this).data("pno");
            name=$("#name").val();
            var phone=$("#phone").val();
            var pno=$(this).data("pno");
            $.getJSON("../../../admin/user",{name:name,phone:phone,pno:pno},function (ret) {
                $("#ul").empty();
                $("#tbody").empty();
                result(ret);
            });
        }

    });
    $("#tbody").on("click",'tr td button',function () {
        var operate=$(this).data("operate");
        var id=$(this).data("id");
        if(operate==="delete")
        {
            $.post("../../../admin/delete",{id:id},function (ret) {
                alert(ret.message);
             if(!ret.errorCode)
             {
                 name=$("#name").val();
                 var phone=$("#phone").val();
                 $.getJSON("../../../admin/user",{name:name,phone:phone,pno:pnos},function (ret) {
                     $("#ul").empty();
                     $("#tbody").empty();
                     result(ret);
                 });
             }
            },"json");
        }else if(operate==="recover"){
            $.post("../../../admin/recover",{id:id},function (ret) {
                alert(ret.message);
                if(!ret.errorCode)
                {
                    name=$("#name").val();
                    var phone=$("#phone").val();
                    $.getJSON("../../../admin/user",{name:name,phone:phone,pno:pnos},function (ret) {
                        $("#ul").empty();
                        $("#tbody").empty();
                        result(ret);
                    });
                }
            },"json");
        }else if(operate==="update")
        {
            window.location="form.html?id="+$(this).data("id");
        }

    });
    var result=function(ret)
    {
        var data=ret.data;
        var list=data.list;
        list.forEach(function (s) {
            var tr=$("<tr>");
            $("<td>").text(s.id).appendTo(tr);
            $("<td>").text(s.phone).appendTo(tr);
            $("<td>").text(s.name).appendTo(tr);
            $("<td>").text(s.password).appendTo(tr);
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
            if(s.state>0)
            {
                $("<button>").addClass("btn btn-primary btn-xs").text("删除").data({id:s.id,operate:"delete"}).appendTo(td);
            }
            else {
                $("<button>").addClass("btn btn-primary btn-xs").text("恢复").data({id:s.id,operate:"recover"}).appendTo(td);
            }
            $("<button>").addClass("btn btn-primary btn-xs").text("修改").data({id:s.id,operate:"update"}).appendTo(td);
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