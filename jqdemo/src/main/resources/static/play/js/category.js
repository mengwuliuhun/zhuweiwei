$(function () {
//     <!--生活-->
// <li>
//     <dl>
//     <dt>生活</dt>
//     <dd>
//     <div class="list">
//         <a href="">
//         <img src="../img/cat_1.png"/>
//         <p class="info">空气净化器（21）</p>
//     </a>
//     </div>
//     </dd>
//     <dd>
//     <div class="list">
//         <a href="">
//         <p class="info">电视（21）</p>
//     </a>
//     </div>
//     </dd>
//     </dl>
//     </li>
    $.getJSON("../coolCategoryVo",function (ret) {
        var data=ret.data;
        data.forEach(function (s) {
            var li=$("<li>");
            var dl=$("<dl>").appendTo(li);
            $("<dt>").text(s.category).appendTo(dl);
            var dd=$("<dd>").appendTo(dl);
            var dd2=$("<dd>").appendTo(dl);
            var list=s.coolCategories;
            list.forEach(function (ss) {
                if(ss.image==null||ss.image==="")
                {
                    var div=$("<div>").addClass("list").appendTo(dd2);
                    var a=$("<a>").appendTo(div);
                    $("<p>").addClass("info").text(ss.name+"("+ss.quantity+")").appendTo(a);
                }else {
                    var div=$("<div>").addClass("list").appendTo(dd);
                    var a=$("<a>").appendTo(div);
                    $("<img>").attr({"src":ss.image,"width":"96px"}).appendTo(a);
                    $("<p>").addClass("info").text(ss.name+"("+ss.quantity+")").appendTo(a);
                }
            });
            li.appendTo($("#ul"));
        })
    });
})