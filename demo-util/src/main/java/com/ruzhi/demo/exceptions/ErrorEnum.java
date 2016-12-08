package com.ruzhi.demo.exceptions;


public enum ErrorEnum {

    //订单
    ORDER_PAID("ORDER_FINISHED", "订单已支付",100),
    ORDER_INVALID("ORDER_INVALID", "订单已失效",101),
    ORDER_PAY_NOT("ORDER_PAY_NOT", "订单未支付",102),
    ORDER_ALLOW_NOT("ORDER_ALLOW_NOT", "非本人订单，无权访问",103),
    ORDER_SCAN_FALL("ORDER_SCAN_FALL", "非本人订单，请重新扫码",104),
    ORDER_QUERY_ERROR("ORDER_QUERY_ERROR","订单查询失败",105),
    ORDER_NOT_SUB_ORDER("ORDER_NOT_SUB_ORDER","子订单异常",106),
    ORDER_FEE_QUERY_ERROR("ORDER_FEE_QUERY_ERROR","订单金额查询失败",107),
    ORDER_NOT_MAIN_ORDER("ORDER_NOT_MAIN_ORDER","主订单异常",108),

    //退款
    REFUND_REASON_QUERY_ERROR("REFUND_REASON_QUERY_ERROR","退款原因查询失败",180),
    REFUND_ERROR("REFUND_ERROR","退款异常",181),
    REFUND_WAIMAI_STATUS_ERROR("REFUND_WAIMAI_STATUS_ERROR","未发货，客户端无法进行退款，请登陆到网页版处理",182),
    REFUND_IS_REFUND_STATUS("REFUND_IS_REFUND_STATUS","订单已申请退款",183),
    REFUND_STATUS_ERROR("REFUND_STATUS_ERROR","当前订单客户端无法申请退款，请登录到网页版处理",184),
    REFUND_STATUS_IS_SUCCESS_ERROR("REFUND_STATUS_IS_SUCCESS_ERROR","订单已经交易成功，售后处理请登录到网页版",185),
    REFUND_STATUS_IS_CLOSE_ERROR("REFUND_STATUS_IS_CLOSE_ERROR","订单已经关闭或者已退款，无法进行退款申请",186),
    REFUND_ERROR_EXCEPTION("REFUND_ERROR_EXCEPTION","退款系统繁忙，请稍后再试",187),
    REFUND_ERROR_PART_FAILURE("REFUND_ERROR_PART_FAILURE","部分菜品退款失败，请重试",188),
    REFUND_ERROR_SELLER_SEND_GOODS_LOCK("REFUND_ERROR_SELLER_SEND_GOODS_LOCK","卖家正在进行接单操作，请尝试刷新订单",189),

    //菜单
    ORDER_DD_EXISTS_NOT("ORDER_DD_EXISTS_NOT", "点菜订单不存在",201),

    //店铺
    CREATE_SHOP_EXISTS_NOT("CREATE_SHOP_EXISTS_NOT", "门店不存在",300),
    CREATE_SELLER_EXISTS_NOT("CREATE_SELLER_EXISTS_NOT", "商户不存在",301),

    //统一异常
    SYSTEM_PARAM_ERROR("SYSTEM_PARAM_ERROR","参数异常",400),
    SYSTEM_PARAM_THROW_EXCEPTION("SYSTEM_PARAM_THROW_EXCEPTION","系统返回异常",401),
    SYSTEM_STATUS_ERROR("SYSTEM_STATUS_ERROR","状态不允许进行该操作",402),

    //基本业务异常
    TIME_ERROR("TIME_ERROR","时间参数异常",500),

    /**
     * 要求客户端刷新请求
     */
    SERVER_REQUIRE_RELOAD("SERVER_REQUIRE_RELOAD","服务器忙，尝试刷新试试吧",999),
    ;
    private String key;
    private String desc;
    private int code;

    public final static String SPECIAL_ERROR_RETURN = "SPECIAL_ERROR_RETURN";

    private ErrorEnum(String key, String desc, int code){
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
