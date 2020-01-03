package cn.downey.data;

import cn.downey.log.UserScanLog;
import cn.downey.utils.UrlSendUtil;
import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ScanLogProducer {

    private static Long[] channel_Id = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};//频道id集合
    private static Long[] cat_Id = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};//产品类别id集合
    private static Long[] product_Id = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};//产品id集合
    private static Long[] user_Id = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};//用户id集合

    /**
     * 地区
     */
    private static String[] countries = new String[]{"America", "china"};//地区-国家集合
    private static String[] provinces = new String[]{"America", "china"};//地区-省集合
    private static String[] cities = new String[]{"America", "china"};//地区-市集合

    /**
     * 网络方式
     */
    private static String[] networks = new String[]{"电信", "移动", "联通"};

    /**
     * 来源方式
     */
    private static String[] sources = new String[]{"直接输入", "百度跳转", "360搜索跳转", "必应跳转"};

    /**
     * 浏览器
     */
    private static String[] browsers = new String[]{"火狐", "qq浏览器", "360浏览器", "谷歌浏览器"};

    /**
     * 打开时间 离开时间
     */
    private static List<Long[]> timeLog = new ScanLogProducer().produceTime();

    public List<Long[]> produceTime() {
        List<Long[]> timeLog = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Long[] timeArray = getTime("2018-02-09 12:45:45:014");
            timeLog.add(timeArray);
        }
        return timeLog;
    }

    private Long[] getTime(String time) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        try {
            Date date = dateFormat.parse(time);
            long timeTemp = date.getTime();
            Random random = new Random();
            int randomInt = random.nextInt(10);
            long startTime = timeTemp - randomInt * 3600 * 1000;
            long endTime = startTime + randomInt * 3600 * 1000;
            return new Long[]{startTime, endTime};
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Long[]{0L, 0L};
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            //频道id 类别id 产品id 用户id 打开时间 离开时间 地区 网络方式 来源方式 浏览器
            UserScanLog userscanLog = new UserScanLog();
            userscanLog.setChannelId(1L);
            userscanLog.setCatId(cat_Id[random.nextInt(cat_Id.length)]);
            userscanLog.setProductId(product_Id[random.nextInt(product_Id.length)]);
            userscanLog.setUserId(user_Id[random.nextInt(user_Id.length)]);
            userscanLog.setCountry(countries[random.nextInt(countries.length)]);
            userscanLog.setProvince(provinces[random.nextInt(provinces.length)]);
            userscanLog.setCity(cities[random.nextInt(cities.length)]);

            userscanLog.setNetwork(networks[random.nextInt(networks.length)]);
            userscanLog.setSources(sources[random.nextInt(sources.length)]);
            userscanLog.setBrowserType(browsers[random.nextInt(browsers.length)]);

            Long[] times = timeLog.get(random.nextInt(timeLog.size()));
            userscanLog.setStartTime(times[0]);
            userscanLog.setEndTime(times[1]);

            String jsonStr = JSONObject.toJSONString(userscanLog);
            System.out.println(jsonStr);
            UrlSendUtil.sendmessage("http://127.0.0.1:6097/DsInfoCollectService/webInfoCollectService", jsonStr);
        }

    }
}
