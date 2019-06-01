/**
 * Created by hebiao on 2019/3/6.
 */
/*window.onload = function(){
    $.fn.snow({
        minSize : 10,
        maxSize :50,
        newOn : 500,
        flakeColor : "red"
    });
}*/


$(".btn").click(function(){
    $(".baidu").slideToggle();//隐藏元素显示
});

$(".dimg_list ul li").click(function(){
    var src = $(this).find("img").attr("dataSrc");//获取图片路径
    $("body").css({"background":"url("+src+")","background-size":"cover"});//改变body背景图片
});
