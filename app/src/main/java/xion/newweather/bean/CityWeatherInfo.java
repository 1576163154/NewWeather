package xion.newweather.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */

public class CityWeatherInfo {

    /**
     * desc : OK
     * status : 1000
     * data : {"wendu":"3","ganmao":"昼夜温差极大，且空气湿度较大，寒冷潮湿，极易发生感冒，请特别注意增减衣服保暖防寒。","forecast":[{"fengxiang":"南风","fengli":"微风级","high":"高温 6℃","type":"晴","low":"低温 -6℃","date":"2日星期一"},{"fengxiang":"北风","fengli":"微风级","high":"高温 7℃","type":"霾","low":"低温 -5℃","date":"3日星期二"},{"fengxiang":"南风","fengli":"微风级","high":"高温 6℃","type":"霾","low":"低温 -5℃","date":"4日星期三"},{"fengxiang":"南风","fengli":"微风级","high":"高温 5℃","type":"霾","low":"低温 -4℃","date":"5日星期四"},{"fengxiang":"南风","fengli":"微风级","high":"高温 5℃","type":"霾","low":"低温 -2℃","date":"6日星期五"}],"yesterday":{"fl":"微风","fx":"南风","high":"高温 4℃","type":"霾","low":"低温 -3℃","date":"1日星期日"},"aqi":"227","city":"北京"}
     */

    private String desc;//请求状态描述
    private int status;//状态码
    private DataBean data;//总的天气情况

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * wendu : 3
         * ganmao : 昼夜温差极大，且空气湿度较大，寒冷潮湿，极易发生感冒，请特别注意增减衣服保暖防寒。
         * forecast : [{"fengxiang":"南风","fengli":"微风级","high":"高温 6℃","type":"晴","low":"低温 -6℃","date":"2日星期一"},{"fengxiang":"北风","fengli":"微风级","high":"高温 7℃","type":"霾","low":"低温 -5℃","date":"3日星期二"},{"fengxiang":"南风","fengli":"微风级","high":"高温 6℃","type":"霾","low":"低温 -5℃","date":"4日星期三"},{"fengxiang":"南风","fengli":"微风级","high":"高温 5℃","type":"霾","low":"低温 -4℃","date":"5日星期四"},{"fengxiang":"南风","fengli":"微风级","high":"高温 5℃","type":"霾","low":"低温 -2℃","date":"6日星期五"}]
         * yesterday : {"fl":"微风","fx":"南风","high":"高温 4℃","type":"霾","low":"低温 -3℃","date":"1日星期日"}
         * aqi : 227
         * city : 北京
         */

        private String wendu;//此时温度
        private String ganmao;//天气描述
        private YesterdayBean yesterday;//昨天天气实体
        private String aqi;
        private String city;//城市名
        private List<ForecastBean> forecast;//预报

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * fl : 微风
             * fx : 南风
             * high : 高温 4℃
             * type : 霾
             * low : 低温 -3℃
             * date : 1日星期日
             */

            private String fl;
            private String fx;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }

        public static class ForecastBean {
            /**
             * fengxiang : 南风
             * fengli : 微风级
             * high : 高温 6℃
             * type : 晴
             * low : 低温 -6℃
             * date : 2日星期一
             */

            private String fengxiang;
            private String fengli;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
