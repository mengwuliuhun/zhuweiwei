$(function () {
    $.getJSON("../loadIndex_ajax",function (ret) {
        $("#exit").hide();
        if(!ret.errorCode)
        {
            $("#reg").hide();
            $("#login").hide();
            $("#exit").show();
        }
    })
    $("#exit").on("click",function () {
        $.getJSON("../exit_ajax",function () {
            $("#reg").show();
            $("#login").show();
            $("#exit").hide();
        })
    })
});