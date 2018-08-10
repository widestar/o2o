$(function () {
    var shopId = getQueryString('shopId');
    var isEdit = shopId ? true : false;
    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    var registerShopUrl = '/o2o/shopadmin/registershop';
    var shopInfoUrl = '/o2o/shopadmin/getshopbyid?shopId='+shopId;
    var modifyShopUrl = '/o2o/shopadmin/modifyshop';
    if (!isEdit) {
        getShopInitInfo(shopId);
    } else {
        getShopInfo(shopId);
    }

    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-desc').val(shop.shopDesc);
                $('#shop-phone').val(shop.phone);
                var shopCategory = '<option data-id="' + shop.shopCategory.shopCategoryId + '" selected>'
                    + shop.shopCategory.shopCategoryName
                    + '</option>';
                var tempAreaHtml = '';
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#shop-category').html(shopCategory);
                $('#shop-category').attr('disabled', 'disabled');
                $('#area').html(tempAreaHtml);
                $("#area option[data-id='"+ shop.area.areaId +"'  ]").attr('selected','selected');
            }
        });
    }

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
            });
    }
    $('#submit').click(function () {
        var shop = {};
        if(isEdit){
            shop.shopId=shopId;
        }
        shop.shopName = $('#shop-name').val().toString();
        shop.shopAddr = $('#shop-addr').val().toString();
        shop.phone = $('#shop-phone').val().toString();
        shop.shopDesc = $('#shop-desc').val().toString();
        shop.shopCategory = {
            shopCategoryId: $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id').toString()
        };
        shop.area = {
            areaId: $('#area').find('option').not(function () {
                return !this.selected;
            }).data('id').toString()
        };
        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        $.ajax({
            url: (isEdit ? modifyShopUrl : registerShopUrl),
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    /*$.alert('提交成功哈哈！');*/
                    $.toast('提交成功哈哈！');
                } else {
                    /*$.alert('提交失败呜呜！' + data.errMsg);*/
                    $.toast('提交失败呜呜！' + data.errMsg);
                }
            }
        });
    });
});
