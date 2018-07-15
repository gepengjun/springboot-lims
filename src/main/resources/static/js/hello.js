function a() {
   var language = $("#language").val();
   alert(language);
    $.ajax({
        type:'post',
        dataType:'json',
        url:'http：localhost:8080/changeLanguage', contentType:"application/json;charset=utf-8",
        data: {"language":language},
        success:function(data){
            if(data.code==1){
                alert("中文");
            }
        }
    })
}
