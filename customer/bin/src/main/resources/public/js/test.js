$(document).ready(function(){
    $(".example2").luara({width:"729",height:"365",interval:2500,selected:"seleted",deriction:"left"});

    var s =$(".lists-title-ti").text().trim().length
    if(s>24){
        var test=$(".lists-title-ti").text().trim().slice(0,20)
        $(".lists-title-ti").text(test+'...')
        $(".lists-title-ti").attr("title",$(".lists-title-ti").text(test+'...'));
    }else{
        $(".lists-title-ti").attr("title",$(".lists-title-ti").text());
    }

    var list =$(".list-title-t").text().trim().length
    var listp =$(".list-title-p").text().trim().length
    if(list>=20 && listp>=69){
        var lists=$(".list-title-t").text().trim().slice(0,20)
        $(".list-title-t").text(lists+"...")

        var listpp=$(".list-title-p").text().trim().slice(0,69)
        $(".list-title-p").text(listpp+'...')

        $(".lists-title-t").attr("title",$(".lists-title-t").text(lists+'...'));
        $(".list-title-p").attr("title",$(".list-title-p").text(listpp+'...'));
    }else{
        $(".lists-title-t").attr("title",$(".lists-title-t").text());
        $(".list-title-p").attr("title",$(".list-title-p").text());
    }



});