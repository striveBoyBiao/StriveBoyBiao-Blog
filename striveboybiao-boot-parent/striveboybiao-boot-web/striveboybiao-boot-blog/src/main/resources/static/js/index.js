/**
 * Created by hebiao on 2018/12/28.
 */
/**初始化查询慢生活界面数据*/
$("document").ready(function(){
    $('#xf').show();
    $.ajax({
        url:"/main/findIndex" ,
        type:"post",
        dataType:"json" ,
        data:{
            "pageNo":"1"
        },
        success:function(data){
            var resultHtml = "<h2> <b>今日</b>推荐 </h2>";
            var newsHtml = "";
            var rankHtml = "";
            var recommendHtml = "";
            var readingHtml = "";
            /**生成页面数据*/
            $.each(data.pageData,function(index,element){
                resultHtml+="<div class='blogs'>";
                resultHtml+="<figure><a  class='preview'  href='/article?type=1&cid="+element.cid+"'><img src='"+element.titlepicture+"' alt='"+element.title+"'></a></figure>";
                resultHtml+="<h3><a title='"+element.title+"' href='/article?type=1&cid="+element.cid+"'>"+element.title+"</a></h3><p>"+element.introduction+"</p>";
                resultHtml+="<ul><p class='autor'><span class='lm f_l'><a href='#'>"+element.typename+"</a></span><span class='dtime f_l'>"+element.gtmcreate+"</span> <span class='viewnum f_l'>浏览（"+element.reading+"）</span> <span class='pingl f_'>评论（<a>"+element.commentsnum+"</a>）</span> </p></ul></div>";
            });
            /**生成最新文章数据*/
            $.each(data.newsData,function(index,element){
                newsHtml += "<li>";
                newsHtml += "<a href='/article?type=1&cid="+element.cid+"' title='"+element.title+"' >"+element.title+"</a>";
                newsHtml += "</li>";
            });
            /**生成点击排行数据*/
            $.each(data.rankData,function(index,element){
                rankHtml += "<li>";
                rankHtml += "<a href='/article?type=1&cid="+element.cid+"' title='"+element.title+"' >"+element.title+"</a>";
                rankHtml += "</li>";
            });
            /**生成站长推荐排行数据*/
            $.each(data.recommendData,function(index,element){
                recommendHtml += "<li>";
                recommendHtml += "<a href='/article?type=1&cid="+element.cid+"' title='"+element.title+"' >"+element.title+"</a>";
                recommendHtml += "</li>";
            });
            /**生成图文推荐排行数据*/
            $.each(data.readingData,function(index,element){
                readingHtml += "<li>";
                readingHtml += "<a href='/article?type=1&cid="+element.cid+"' title='"+element.title+"' ><img border='0' width='120' height='120' src='"+element.titlepicture+"' alt='"+element.title+"'><b>"+element.title+"</b></a>";
                readingHtml += "<p><span class='tulanmu'><a title='"+element.typename+"' >"+element.typename+"</a></span><span class='tutime'>"+element.gtmcreate+"</span></p>";
                readingHtml += "</li>";
            });

            $("#findIndex").html(resultHtml);
            $("#rankData").html(rankHtml);
            $("#newsData").html(newsHtml);
            $("#nominateData").html(recommendHtml);
            $("#readingData").html(readingHtml);
            /**数据加载特效*/
            $('#xf').hide();
        }
    })
})
