/**
 * Created by 11697 on 2019/5/30.
 */
var app=new Vue({
    el:'#app-article',
    data:{
        list:'',
        newsdata:'',
        rankdata:"",
        relatedata:"",
        ondata:"",
        underdata:""
    },
    methods:{
        getDataList:function (id) {
            $.ajax({
                url:"/main/findIndexDetails" ,
                type:"post",
                dataType:"json" ,
                data:{
                    "cid":id
                },
                success:function(data){
                    app.list=data.articleData;
                    app.newsdata=data.newsData;
                    app.rankdata=data.rankData;
                    app.relatedata=data.relateData;
                    app.ondata=data.onData;
                    app.underdata=data.underData;
                }
            })
        },
        /**拼接路径*/
        bindsrc:function (id) {
            app.getDataList(id);
        }

    }
})

/**初始化查询文章界面数据*/
$("document").ready(function(){
    app.getDataList(null);
})