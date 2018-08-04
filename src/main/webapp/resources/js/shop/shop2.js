$(function() {
    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    var registerShopUrl = '/o2o/shopadmin/registershop';
    getShopInitInfo();
    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
                if (data.success) {
                    var tempHtml = '';
                    var tempAreaHtml = '';
                    data.shopCategoryList.map(function (item, index) {
                        tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                            + item.shopCategoryName + '</option>';
                    });
                    data.areaList.map(function (item, index) {
                        tempAreaHtml += '<option data-id="' + item.areaId + '">'
                            + item.areaName + '</option>';
                    });
                    $('#shop-category').html(tempHtml);
                    $('#area').html(tempAreaHtml);
                }
            }
        );
        $('#submit').click(function () {
            /*var shopImg = $('#shop-img').files[0];
            console.log(shopImg);
            var formData = new FormData();
            formData.append('shopImg', shopImg);
            formData.append("lzgName", "liuzeguang");
            $.ajax({
                url: registerShopUrl,
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                cache: false,
                success: function (data) {
                    if (data.success) {
                        $.toast('提交成功！');
                        window.location.href = '/myo2o/shop/ownerlogin';
                    } else {
                        $.toast('提交失败！');
                    }
                }
            });*/
            var jsonStr = [ {
                'name' : 'jim',
                'age' : 20
            }, {
                'name' : 'king',
                'age' : 26
            }, {
                'name' : 'jge',
                'age' : 30
            } ];
            var url=registerShopUrl;
            $.ajax({
                type : "post",
                url : url,
                dataType : 'json',
                data : {
                    'mydata' : JSON.stringify(jsonStr)//注意一定要使用JSON.stringify (在IE6，IE7中不支持)
                }
        });
    });
    }
});