package com.ruzhi.demo.lifeserverweb;

import java.util.LinkedList;
import java.util.List;

/**
 * User: yuanquan.ld
 * Date: 12-2-24
 * Time: 上午10:35
 */
public class DeliveryAbilityUtil {

    public static final int TIMES_48 = 48;

    public static List<String> times = new LinkedList<String>();
    public static List<String> atimes = new LinkedList<String>();

	/*static {
		initTimes();
	}*/

    /**
     * 将tair中的valueDO转换为TimeSecPerTimeResultDO
     * @param cacheDO
     * @param time
     * @return
     */
   /* public static TimeSecPerTimeResultDO convertCacheDO2TimeResultDO(DeliveryAbilityCacheDO cacheDO, String time) {
        if (null == cacheDO)
            return null;
        TimeSecPerTimeResultDO resultDO = new TimeSecPerTimeResultDO();
        resultDO.setTotal(cacheDO.getTotal());
        resultDO.setLeft(cacheDO.getLeft());
        resultDO.setBusy(cacheDO.isBusy());
        resultDO.setTime(time);
        return resultDO;
    }

    public static List<TimeSecPerTimeResultDO> convertCacheDOs2ResultDOs(Map<String, DeliveryAbilityCacheDO> cacheDOMap){
        if(null == cacheDOMap || cacheDOMap.size() <= 0)
            return null;

        List<TimeSecPerTimeResultDO> perDateResultDOs = new ArrayList<TimeSecPerTimeResultDO>();
        TimeSecPerTimeResultDO perTimeResultDO = null;
        for(String key : cacheDOMap.keySet()){
            if(StringUtils.isBlank(key))
                continue;

            String time = getTimeFromKey(key);
            if(StringUtils.isBlank(time))
                continue;

            perTimeResultDO = convertCacheDO2TimeResultDO(cacheDOMap.get(key), time);
            if(null != perTimeResultDO){
                perDateResultDOs.add(perTimeResultDO);
            }
        }
        return perDateResultDOs;
    }

    *//**
     * 从key中获取时间段 xx201202230830   0830就是时间段
     * @param key
     * @return
     *//*
    private static String getTimeFromKey(String key){
        if(StringUtils.isBlank(key) || key.length() <= 9)
            return "";

        return key.substring(key.length() - 5, key.length());
    }

    *//**
     * 将shopIds dateList timeList 转换为keyList
     *
     * @param shopIds
     * @param dateList
     * @param timeList
     * @return
     *//*
    public static List<String> convert2KeyList(List<Long> shopIds, List<String> dateList, List<String> timeList) {
        Arguments.notFalse(CollectionUtils.isEmpty(shopIds), "shopIds can't be empty!");
        Arguments.notFalse(CollectionUtils.isEmpty(dateList), "dateList can't be empty!");
        Arguments.notFalse(CollectionUtils.isEmpty(timeList), "timeList can't be empty!");

        List<String> keys = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        for (Long shopId : shopIds) {
            if (shopId <= 0)
                continue;
            for (String date : dateList) {
                if (StringUtils.isBlank(date))
                    continue;

                for (String time : timeList) {
                    if (StringUtils.isBlank(time))
                        continue;

                    if (builder.length() > 0)
                        builder.delete(0, builder.length());
                    builder.append(shopId).append(date).append(time);
                    keys.add(builder.toString());
                }
            }
        }
        return keys;
    }

    *//**
     * 根据当前日期、该店铺支持的外卖天数，得到tair中最多存在几天的有效记录
     *
     * @param date        当前日期 20120223
     * @param supportDays 支持的外卖天数
     * @return
     *//*
    public static List<String> createDateList(String date, int supportDays) {
        Arguments.notBlank(date, "date can't be blank !");
        Arguments.notZero(supportDays, "supportDays can't be <= 0");

        List<String> dateList = new ArrayList<String>();
        dateList.add(date);
        int temp = Integer.valueOf(date);
        for (int i = 1; i < supportDays; i++) {
            temp++;
            dateList.add(String.valueOf(temp));
        }
        return dateList;
    }

    *//**
     * 将Date类型转换为String
     *
     * @param date
     * @return
     *//*
    public static String convertDate2String(Date date) {
        if (null == date)
            return null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    *//**
     * 将time List 转换为String，用<code>;</code>分隔
     *
     * @param times
     * @return
     *//*
    public static String timeList2Str(List<String> times) {
        if (CollectionUtils.isEmpty(times)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String str : times) {
            if (StringUtils.isBlank(str))
                continue;
            builder.append(str).append(";");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    *//**
     * 将dateDO转换为tair的key list
     * 删除配送能力时间段的时候使用
     *
     * @param dateDO
     * @param shopId
     * @return
     *//*
    public static List<String> convertPerDateDO2List(DeliveryAbilityPerDateDO dateDO, long shopId) {
        if (null == dateDO)
            return null;

        List<String> keys = new ArrayList<String>();
        String date = dateDO.getDate();
        for (DeliveryAbilityPerTimeDO timeDO : dateDO.getAbilityPerTimeDOList()) {
            if (null == timeDO || StringUtils.isBlank(timeDO.getTime()))
                continue;
            String key = createKey(shopId, date, timeDO.getTime());
            if (StringUtils.isNotBlank(key))
                keys.add(key);
        }
        return keys;
    }

    *//**
     * 从DeliveryAbilityPerDateDO中得到time list 时间段list
     *
     * @param dateDO
     * @return
     *//*
    public static List<String> getTimeList(DeliveryAbilityPerDateDO dateDO) {
        if (null == dateDO || CollectionUtils.isEmpty(dateDO.getAbilityPerTimeDOList()))
            return null;

        List<String> timeList = new ArrayList<String>();
        List<DeliveryAbilityPerTimeDO> timeDOs = dateDO.getAbilityPerTimeDOList();
        for (DeliveryAbilityPerTimeDO timeDO : timeDOs) {
            if (null == timeDO)
                continue;
            timeList.add(timeDO.getTime());
        }
        return timeList;
    }

    *//**
     * 将requestDO转换为tair的key list
     * 删除配送能力时间段的时候使用
     *
     * @param requestDO
     * @return
     *//*
    public static List<String> convertRquestDO2List(DeliveryAbilityRequestDO requestDO) {
        if (null == requestDO)
            return null;

        long shopId = requestDO.getShopId();
        List<String> keys = new ArrayList<String>();
        for (DeliveryAbilityPerDateDO dateDO : requestDO.getAbilityPerDateDOList()) {
            if (null == dateDO)
                continue;
            List<String> temp = DeliveryAbilityUtil.convertPerDateDO2List(dateDO, shopId);
            if (CollectionUtils.isNotEmpty(temp))
                keys.addAll(temp);
        }
        return keys;
    }

    public static String createKey(DeliveryAbilityPerDateDO dateDO, long shopId) {
        Arguments.notNull(dateDO, "dateDO can't be null !");
        Arguments.notZero(shopId, "shopId can't be <= 0 !");
        String timeSections = DeliveryAbilityUtil.timeList2Str(getTimeList(dateDO));
        if (StringUtils.isBlank(timeSections))
            return "";
        return createKey(shopId, dateDO.getDate(), timeSections);
    }

    public static String createKey(long shopId, String date, String time) {
        Arguments.notBlank(date, "date can't be null !");
        Arguments.notBlank(time, "time can't be null !");
        Arguments.notZero(shopId, "shopId can't be <= 0 !");
        StringBuilder builder = new StringBuilder();
        builder.append(shopId).append(date).append(time);
        return builder.toString();
    }

    *//**
     * 将从DB里获取的配送峰值字符串转换为DeliveryAbilityPerTimeDO list
     * @param deliveryAbility 08:00 100;08:30 200
     * @return
     *//*
    public static List<DeliveryAbilityPerTimeDO> convertStr2List(String deliveryAbility){
        if(StringUtils.isBlank(deliveryAbility))
            return null;

        String[] temp = deliveryAbility.split(";");
        List<DeliveryAbilityPerTimeDO> perTimeDOs = new ArrayList<DeliveryAbilityPerTimeDO>();
        DeliveryAbilityPerTimeDO perTimeDO = null;
        for(String str : temp){
            perTimeDO = convertStr2DeliveryAbility(str);
            if(null == perTimeDO)
                continue;

            perTimeDOs.add(perTimeDO);
        }
        return perTimeDOs;
    }

    *//**
     * 将从DB里获取的配送峰值字符串转换为DeliveryAbilityPerTimeDO
     * @param str 08:00 100
     * @return
     *//*
    private static DeliveryAbilityPerTimeDO convertStr2DeliveryAbility(String str){
        if(StringUtils.isBlank(str))
            return null;

        DeliveryAbilityPerTimeDO perTimeDO = new DeliveryAbilityPerTimeDO();
        String[] temp = str.split(" ");
        if(null == temp || temp.length != 2)
            return null;

        if(StringUtils.isBlank(temp[0]) || StringUtils.isBlank(temp[1])){
            return null;
        }

        perTimeDO.setTime(temp[0].trim());
        perTimeDO.setTotal(Integer.valueOf(temp[1].trim()));
        return perTimeDO;
    }

    *//**
     * 根据开始时间段获取配送峰值设置中的结束时间段
     * @param time
     * @return
     *//*
    public static String getAbility2(String time){
        if(StringUtils.isBlank(time))
            return "";

        return get30mLater(time);
    }

    public static List<TimeSecPerTimeResultDO> convertShopAbility2TimeSecs(ShopDeliveryAbility ability){
        if(null == ability)
            return null;

        List<TimeSecPerTimeResultDO> perTimeResultDOs = new ArrayList<TimeSecPerTimeResultDO>();
        String delivery = ability.getPeakTimeValue();
        long shopId = ability.getShopId();
        if(StringUtils.isBlank(delivery))
            return null;

        Map<String, String> timeCount = ability.getTimesCount();
        if(null == timeCount || timeCount.size() <= 0){
            return null;
        }

        for(String time : timeCount.keySet()){
            if(StringUtils.isBlank(time))
                continue;

            String count = timeCount.get(time);
            if(StringUtils.isBlank(count)){
                continue;
            }
            int iCount = Integer.valueOf(count);
            TimeSecPerTimeResultDO timeResultDO = new TimeSecPerTimeResultDO();
            timeResultDO.setTotal(iCount);
            timeResultDO.setLeft(iCount);
            timeResultDO.setTime(time);
            perTimeResultDOs.add(timeResultDO);
        }
        return perTimeResultDOs;
    }

    private static Logger log = Logger.getLogger(DeliveryAbilityUtil.class) ;
 *//**
     * 得到半个小时之后的时间
     * @param times  09:00
     * @return 09:30
     *//*
    public static String get30mLater(String times) {
		// 加半小时
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = null;
		try {
			date1 = format.parse(times);
			if (date1!=null){
			    long Time = (date1.getTime() / 1000) + 60 * 30;
		        date1.setTime(Time * 1000);
			}
		} catch (ParseException e) {
			log.error("DeliveryAbilityUtil.get30mLater", e) ;
		}
		return DateUtil.dateFormat(date1, "HH:mm");
	}*/

    /**
     * 初始化时间段 08:00-08:30/08:00
     * @return 时间段
     */
   /* public static void initTimes(){
    	String time = "00:00";
    	for (int i = 0; i < TIMES_48; i++){
    		String later = get30mLater(time);
    		times.add(time + "-" + later);
            atimes.add(time);
    		time = later;
    	}
    }*/

/*

    public static void main(String[] args) {

        String ant = getAbility2("23:30");
        ant.trim();
    }
*/

}
