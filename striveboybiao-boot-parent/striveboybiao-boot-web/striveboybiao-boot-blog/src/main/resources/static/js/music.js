/**
 * Created by 11697 on 2019/5/30.
 */
var app=new Vue({
    el:'#app-music',
    data:{
        newsdata:'',
        rankdata:'',
        recommenddata:""
    },
    methods:{
        getDataList:function (id) {
            $.ajax({
                url:"/main/findMusic" ,
                type:"post",
                dataType:"json" ,
                data:{
                    "cid":id
                },
                success:function(data){
                    app.recommenddata=data.recommendData;
                    app.newsdata=data.newsData;
                    app.rankdata=data.rankData;
                }
            })
        },
        /**拼接路径*/
        bindsrc:function (id) {
            return "/article?type=1&cid="+id;
        }
    }
})

/**初始化查询文章界面数据*/
$("document").ready(function(){
    app.getDataList(null);
})