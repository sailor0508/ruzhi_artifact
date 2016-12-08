package com.ruzhi.demo.common;

import java.util.Arrays;
import java.util.List;


public interface Constants {

	//url 加签要用到的字段
	public static final List<String> SIGN_MUST_KEYLIST = Arrays.asList(new String[]{"t","userid","sign"});

	enum ErrorCode {
		PARAM_INVALID(1,"传入参数无效或为空"),
		VERIFY_FAIL(2,"认证失败"),
		DATA_IS_NULL(3,"数据集为空"),
		RETURN_DATA_ERROR(63,"返回数据有错误"),
		USER_CLAIM_STORE_FAIL(4,"用户认领商户失败"),
		STORE_NEEDPARAM_INVALID(5,"商户必备参数非法"),
		STORE_RELATION_FAIL(6,"商户关联失败"),
		STORE_HAS_NO_CATE(7,"门店没有设置类目信息!"),
		STORE_HAS_NO_DISH(8,"门店没有设置菜品信息!"),
		ORDER_ERROR(9,"订单信息不存在!"),
		LOCAL_ORDER_ERROR(10,"商户订单信息不存在!"),
		STORE_ERROR(11,"没有商户信息"),
		ITEM_ERROR(13,"没有菜品信息"),
		ITEM_CATE_ERROR(12,"没有类目信息"),
		CONNECT_ERROR(99,"连接异常"),
		USER_HAS_NO_ORDER(15,"您不存在未消费的订单"),
		POST_DATA_ERROR(16,"订单传输错误!"),
		MISS_PARAMETER(17,"缺少参数"),
		STOREID_IS_NULL(18,"门店id为空"),
		ITEMID_IS_NULL(19,"菜品id为空"),
		ITEM_NAME_IS_NULL(20,"菜品名称为空"),
		ITEM_PRICE_IS_NULL(21,"菜品价格为空或者为0"),
		ITEM_CURRENTPRICE_GREATER_PRICE(25,"菜品当前价格大于原价"),
		ITEM_USERID_IS_NULL(22,"菜品卖家账号为空"),
		POS_VERIFY_FAIL(23,"扫码位置未通过验证"),
		PIC_UPLOAD_FAIL(24,"图片上传失败"),
		SELLER_IS_INVALID(50,"用户不合法"),
		ITEM_CATE_IS_NULL(51,"菜品类目信息不能空"),
		ITEM_CATE_NAME_IS_NULL(52,"菜品类目名称不能空"),
		ITEM_SKU_IS_DUPLICATE(54,"菜品属性重复"),
		ITEM_SETFOOD_INFO_MISSING(55,"菜品套餐详情缺少"),
		ITEM_SKU_INFO_MISSING(56,"菜品属性缺少或者菜品属性重复"),
		ITEM_SKU_GROUP_IS_OUTOFRANGE(57,"菜品属性超出数量"),
		ITEM_SKU_PRICE_IS_OUTOFRANGE(58,"菜品属性价格错误"),
		ITEM_PROPETY_PATTER_ERROR(59,"菜品属性数据格式错误"),
		ITEM_BIZTAG_PATTER_ERROR(60,"菜品biztag格式错误"),
		ITEM_TITLE_KFC(61,"菜品标题中包含敏感词"),
		ITEM_DESC_KFC(62,"菜品描述中包含敏感词"),
		USER_IS_BLACK(63,"该用户在黑名单中不能下单")
		;
		private int code;
		private String msg;

		public int getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}

		ErrorCode(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}
	}
	enum Code {
		SUCCESS(200,"OK"),
		NOT_FOUND(404,"404");
		;
		private int code;
		private String msg;

		public int getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}

		Code(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}
	}


	enum OutPartnerSource {
		//		ZUANMU(1,"钻木"),//lifemall04 杭州迪火科技有限公司(钻木)
		CANXINGJIAN(2,"餐行健"),//tbtest93  奥琦玮信息科技北京有限公司(餐行健)
		HUALALA(3,"哗啦啦"),//lifemall06  多来点信息技术有限公司(哗啦啦)
		//YITAOSHI(4,"易淘食"),//lifemall07 易淘星空网络科技(北京)有限公司(易淘食)
		SHICHUAN(5,"石川"),//lifemall05 上海石川科技有限公司
		TIANZIXING(6,"天子星"),//lanxuan06 北京普照天星科技有限公司(天子星)
		//HANGZHOUKERUN(7,"杭州科润"),//lanxuan02
		TIANCAISHANGLONG(8,"天财商龙");//lanxuan03  天津市神州商龙科技有限公司(天财商龙)

		private int code;
		private String content;

		public int getCode() {
			return code;
		}

		public String getContent() {
			return content;
		}

		OutPartnerSource(int code,String content) {
			this.code = code;
			this.content = content;
		}
	}



	enum BusyLevel {
		FREE(1,"空闲"),
		NORMAL(2,"正常"),
		BUSY(3,"繁忙");
		private int code;
		private String content;

		public int getCode() {
			return code;
		}

		public String getContent() {
			return content;
		}

		BusyLevel(int code,String content) {
			this.code = code;
			this.content = content;
		}
	}



	/**
	 * serve_info  serve_ability
	 */
	/*public static List<String> serve_abilitys = Arrays.asList(new String[] {
		"perprice"
	});*/


	enum Serve_abilitys {
		PERPRICE("perprice","人均价格"),
		TABLEINFO("tableinfo","台位信息"),
		TOTALORDERCOUNT("totalordercount","总订单数"),
		RESERVETIME("reservetime","预订时间"),
		NOTRESERVETIME("notreservetime","不能预订时间"),
		STOPINFO("stopinfo","停车位信息");

		private String key;
		private String content;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		Serve_abilitys(String key,String content) {
			this.key = key;
			this.content = content;
		}
	}

	String DEFAULT_PIC_URI = "i4/T1VuaNXs4gXXXf1Lo7-240-160.png" ;


}
