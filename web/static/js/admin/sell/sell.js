//出售成功之后的销售单
var sellSlip = null;
$(function () {

    //表单提交事件
    layui.form.on('submit(btnSubmit)', function(data) {
        var sellArgs = [];
        var imeiInput = $('#sellDetailDiv input:text[name=IMEI]');
        var pricesInput = $('#sellDetailDiv input:text[name=transactionPrice]');
        for (var i = 0 ; i < imeiInput.length ; i ++ ){
            var sell = {imei:$(imeiInput[i]).val().trim(),transactionPrice:$(pricesInput[i]).val().trim()};
            sellArgs.push(sell);
        }

        var url = $("#editForm").attr("action");
        layer.load(1);
        $.post(url, {token:getToken(),param:JSON.stringify(sellArgs)}, function(data){
            layer.closeAll('loading');
            if(data.code===200){
                imeiInput.val('');
                pricesInput.val('');
                layer.msg(data.msg,{icon: 1});
                sellSlip = data.sellSlip;
                layer.confirm('是否打印小票',function () {
                    printTicket();
                    layer.close();
                })
            }else{
                layer.msg(data.msg,{icon: 2});
            }
        }, "JSON");
        return false;
    });

    //表单验证
    layui.form.verify({
        money: function (value,item) {
            var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
            if (!reg.test(value)) {
                return "请输入正确的金钱格式";
            }
        },
        IMEI:[ /^[\S]{15,17}$/
            , '请输入正确的IMEI']
    });

    $('#printTicketBtn').click(function () {
        printTicket();
    })
});


function addDetail() {
    $('#sellDetailDiv ul').append("<hr/>\n" +
        "            <li>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">IMEI</label>\n" +
        "                    <div class=\"layui-input-block\">\n" +
        "                        <input name=\"IMEI\" placeholder=\"请输入IMEI\" type=\"text\" class=\"layui-input\" maxlength=\"20\"\n" +
        "                               lay-verify=\"required|IMEI\" required/>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">销售价格</label>\n" +
        "                    <div class=\"layui-input-block\">\n" +
        "                        <input name=\"transactionPrice\" placeholder=\"请输入销售价格\" type=\"text\" class=\"layui-input\"\n" +
        "                               maxlength=\"20\"\n" +
        "                               lay-verify=\"required|money\" required/>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </li>\n");
}

function printTicket() {
    if (sellSlip === null) {
        layer.msg("请先出售手机");
        return;
    }


   window.open(encodeURI("sell/ticket.html?serialNumber="+sellSlip.serialNumber+"&operatorName="+getCurrentUser().realName+"&saleDate="+sellSlip.saleDate+"&number="+sellSlip.saleNumber+"&totalPrice="+sellSlip.totalPrice),'打印小票','height=300,width=700,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');

}