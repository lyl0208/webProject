$(function () {

    var brandId = null;

    $.post({
        url:"/front/listBrand"
        ,async:false
        ,success:function (data) {
        layui.laytpl(brandList.innerHTML).render(data,function (html) {
            $('#brand-list').html(html);
            brandId = data[0].brandId;
        })}
    });

    layui.use('element',function () {
        var element = layui.element;

        element.init();

    });

    listPhoneItem(brandId);

});

function listPhoneItem(brandId) {
    if (brandId === null) {
        return;
    }
    $.post("/front/listPhoneItem",{brandId:parseInt(brandId)},function (data) {
        layui.laytpl(phoneList.innerHTML).render(data,function(html){
            $('#phone-list').html(html);
        })
    });

}

function defaultPic() {
    return "/static/images/no_pic.jpg";
}

function goDetail(modelId) {
    window.open("detail.html?modelId="+modelId);
}
