/**
 * Created by hebiao on 2018/12/28.
 */
var app=new Vue({
    el:'#app-life',
    data:{
        dataList:'',
        newsList:''
    },
    methods:{
        getDataList:function (pageNo) {
            $('#xf').show();
            $.ajax({
                url:"/main/findLife" ,
                type:"post",
                dataType:"json" ,
                data:{
                    "pageNo":pageNo,
                },
                success:function(data){
                    app.dataList=data.pageData;
                    app.newsList=data.newsData;
                    if(data.pageCount>1){
                        pageInfo(data.pageNo,data.pageCount);
                    }else{
                        $("#footer").html('');
                    }
                    app.$nextTick(function(){
                        /**数据加载特效*/
                        $('#xf').hide();
                    })
                }
            })
        },
        /**点击慢生活--栏目导航--查询相对应数据*/
        queryLanMu:function (lmlb) {
            $('#xf').show();
            $.ajax({
                url:"/main/findLife" ,
                type:"post",
                dataType:"json" ,
                data:{
                    "pageNo":"1",
                    "lmlb":lmlb
                },
                success:function(data){
                    app.dataList=data.pageData;
                    app.newsList=data.newsData;
                    if(data.pageCount>1){
                        pageInfo(data.pageNo,data.pageCount);
                    }else{
                        $("#footer").html('');
                    }
                    app.$nextTick(function(){
                        /**数据加载特效*/
                        $('#xf').hide();
                    })
                }
            })
        },
        /**拼接路径*/
        bindsrc:function (id) {
            return "/article?type=3&cid="+id;
        }

    }

})


/**初始化查询慢生活界面数据*/
$("document").ready(function(){
            app.getDataList("1");
})



/**-------------------------------------------动态创建 分页------------------------------------------------------------*/
function pageInfo(pageNo,pageCount){
    pageNo = parseInt(pageNo,"10");
    var result="";
    result+="<nav>";
    result+="<ul class='pagination'>";
    if(pageNo==1){
        result+="<li class='disabled'><a href='#'>&laquo;</a></li>";
    }else{
        result+="<li><a href='#' onclick=\"shangyiye("+(pageNo-1)+")\""+" >上一页</a></li>";
    }
    var begin=0;
    var end=0;
    if(pageCount<=5){
        begin=1;
        end=pageCount;
    }else{
        begin=pageNo-2;
        end=pageNo+3;
        if(begin<1){
            begin=1;
            end=5;
        }
        if(end>pageCount){
            begin=pageCount-4;
            end=pageCount;
        }
    }

    //<!--显示中间页数  -->
    for(var i=begin;i<=end;i++){
        if(i==pageNo){
            result+="<li class='active'><a href='#' onclick=\"zhongjianye("+i+")\""+" >"+i+"</a></li>";
        }else{
            result+="<li><a href='#' onclick=\"zhongjianye("+i+")\""+" >"+i+"</a></li>";
        }
    }

    if(end< pageCount){
        result+="<li><a href='#'>....</a></li>";
    }
    //<!--禁止  -->
    if(pageNo==pageCount){
        result+="<li class='disabled'><a href='#'>&raquo;</a></li>";
    }
    //<!--下一页  -->
    if(pageNo!=pageCount){
        result+="<li><a href='#' onclick=\"xiayiye("+(pageNo+1)+")\""+">下一页</a></li>";
    }

    result+="</ul></nav>";
    $("#footer").html(result);
}


//上一页
function shangyiye(pageNo){

    gongzong(pageNo);
}

//下一页
function xiayiye(pageNo){

    gongzong(pageNo);
}

//中间数字
function zhongjianye(pageNo){
    gongzong(pageNo);
}
/**------------------------------------------------------分页结束-------------------------------------------------------*/
/** 公共代码 */
function gongzong(pageNo){
    /**查询慢生活界面数据*/
   app.getDataList(pageNo);
}
