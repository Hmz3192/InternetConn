package com.atguigu.im.homeadapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/11/11.
 */

public class ChannelBean implements Serializable{



    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<BannerInfoBean> banner_info;
        private List<ChannelInfoBean> channel_info;
        private List<KTVBean> KTV;
        private List<FOODBean> FOOD;
        private List<CLOSEBean> CLOSE;

        public List<BannerInfoBean> getBanner_info() {
            return banner_info;
        }

        public void setBanner_info(List<BannerInfoBean> banner_info) {
            this.banner_info = banner_info;
        }

        public List<ChannelInfoBean> getChannel_info() {
            return channel_info;
        }

        public void setChannel_info(List<ChannelInfoBean> channel_info) {
            this.channel_info = channel_info;
        }

        public List<KTVBean> getKTV() {
            return KTV;
        }

        public void setKTV(List<KTVBean> KTV) {
            this.KTV = KTV;
        }

        public List<FOODBean> getFOOD() {
            return FOOD;
        }

        public void setFOOD(List<FOODBean> FOOD) {
            this.FOOD = FOOD;
        }

        public List<CLOSEBean> getCLOSE() {
            return CLOSE;
        }

        public void setCLOSE(List<CLOSEBean> CLOSE) {
            this.CLOSE = CLOSE;
        }

        public static class BannerInfoBean {
            /**
             * image : act/z2.png
             * option : 3
             * type : 0
             */

            private String image;
            private int option;
            private int type;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getOption() {
                return option;
            }

            public void setOption(int option) {
                this.option = option;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class ChannelInfoBean {
            /**
             * channel_name : 美食
             * image : channel/c1.png
             * value : {"channel_id":"8"}
             */

            private String channel_name;
            private String image;
            private ValueBean value;

            public String getChannel_name() {
                return channel_name;
            }

            public void setChannel_name(String channel_name) {
                this.channel_name = channel_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public ValueBean getValue() {
                return value;
            }

            public void setValue(ValueBean value) {
                this.value = value;
            }

            public static class ValueBean {
                /**
                 * channel_id : 8
                 */

                private String channel_id;

                public String getChannel_id() {
                    return channel_id;
                }

                public void setChannel_id(String channel_id) {
                    this.channel_id = channel_id;
                }
            }
        }

        public static class KTVBean {
            /**
             * name : SingYoung 复合式KTV
             * rating : 5
             * price : 43元起
             * length : 距离55m
             * location : 世贸中心5楼
             * url : ktv/k1.png
             * number : 100人以上消费
             * introduce : 营业时间：周一至周日
             * buy : [{"url":"科技园路.","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"科技园路.","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}]
             * comment : [{"url":"科技园路.","name":"小华","date":"2017/07/13","neirong":"环境很好，很干净，很喜欢"},{"url":"科技园路.","name":"小华","date":"2017/07/13","neirong":"环境很好，很干净，很喜欢"},{"url":"科技园路.","name":"小华","date":"2017/07/13","neirong":"环境很好，很干净，很喜欢"}]
             */

            private String name;
            private int rating;
            private String price;
            private String length;
            private String location;
            private String url;
            private String number;
            private String introduce;
            private List<BuyBean> buy;
            private List<CommentBean> comment;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRating() {
                return rating;
            }

            public void setRating(int rating) {
                this.rating = rating;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public List<BuyBean> getBuy() {
                return buy;
            }

            public void setBuy(List<BuyBean> buy) {
                this.buy = buy;
            }

            public List<CommentBean> getComment() {
                return comment;
            }

            public void setComment(List<CommentBean> comment) {
                this.comment = comment;
            }

            public static class BuyBean {
                /**
                 * url : 科技园路.
                 * price : 128
                 * name : 周一至周日欢唱3选1
                 * selled : 已售100
                 */

                private String url;
                private int price;
                private String name;
                private String selled;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSelled() {
                    return selled;
                }

                public void setSelled(String selled) {
                    this.selled = selled;
                }
            }

            public static class CommentBean {
                /**
                 * url : 科技园路.
                 * name : 小华
                 * date : 2017/07/13
                 * neirong : 环境很好，很干净，很喜欢
                 */

                private String url;
                private String name;
                private String date;
                private String neirong;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getNeirong() {
                    return neirong;
                }

                public void setNeirong(String neirong) {
                    this.neirong = neirong;
                }
            }
        }

        public static class FOODBean {
            /**
             * name : 布恬私家蛋糕
             * rating : 5
             * price : 20元/人
             * length : 距离55m
             * location : 婺城区人民东路118号
             * url : food/f1.png
             * number : 100人以上消费
             * introduce : 营业时间：周一至周日
             * kind : 美食甜点
             * buy : [{"url":"food/f1/ff1.png","price":98,"name":"甜品 3 选 1","introduce":"抹茶红豆，焦糖巧克力，蓝莓千层","rating":4,"selled":"已售100"},{"url":"food/f1/ff2.png","price":98,"name":"4寸千层蛋糕","introduce":"千层蛋糕，4英寸，圆形","rating":4,"selled":"已售100"},{"url":"food/f1/ff3.png","price":98,"name":"6寸淡奶油水果千层","introduce":"蓝莓，红豆，哈密瓜，火龙果，百香果，焦糖","rating":4,"selled":"已售12"}]
             * comment : [{"url":"科技园路.","name":"小华","date":"2017/07/13","neirong":"环境很好，很干净，很喜欢"},{"url":"科技园路.","date":"2017/07/13","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"科技园路.","date":"2017/07/13","name":"小华","neirong":"环境很好，很干净，很喜欢"}]
             */

            private String name;
            private int rating;
            private String price;
            private String length;
            private String location;
            private String url;
            private String number;
            private String introduce;
            private String kind;
            private List<BuyBeanX> buy;
            private List<CommentBeanX> comment;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRating() {
                return rating;
            }

            public void setRating(int rating) {
                this.rating = rating;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public List<BuyBeanX> getBuy() {
                return buy;
            }

            public void setBuy(List<BuyBeanX> buy) {
                this.buy = buy;
            }

            public List<CommentBeanX> getComment() {
                return comment;
            }

            public void setComment(List<CommentBeanX> comment) {
                this.comment = comment;
            }

            public static class BuyBeanX {
                /**
                 * url : food/f1/ff1.png
                 * price : 98
                 * name : 甜品 3 选 1
                 * introduce : 抹茶红豆，焦糖巧克力，蓝莓千层
                 * rating : 4
                 * selled : 已售100
                 */

                private String url;
                private int price;
                private String name;
                private String introduce;
                private int rating;
                private String selled;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public int getRating() {
                    return rating;
                }

                public void setRating(int rating) {
                    this.rating = rating;
                }

                public String getSelled() {
                    return selled;
                }

                public void setSelled(String selled) {
                    this.selled = selled;
                }
            }

            public static class CommentBeanX {
                /**
                 * url : 科技园路.
                 * name : 小华
                 * date : 2017/07/13
                 * neirong : 环境很好，很干净，很喜欢
                 */

                private String url;
                private String name;
                private String date;
                private String neirong;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getNeirong() {
                    return neirong;
                }

                public void setNeirong(String neirong) {
                    this.neirong = neirong;
                }
            }
        }

        public static class CLOSEBean {
            /**
             * product_id : 6633
             * name : 【画影】现货  小狐狸羽织
             * rating : 5
             * price : 132
             * url : close/c3.jpg
             * number : 100人以上消费
             * introduce : 红黑款是现货哦·~现货不配送发带~~红黑款是现货哦·~现货不配送发带~~红黑款是现货哦·~现货不配送发带~~ 重要的事情说三次~
             * kind : 羽绒外套
             * comment : [{"url":"科技园路.","date":"2017/07/13","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"科技园路.","name":"小华","date":"2017/07/13","neirong":"环境很好，很干净，很喜欢"},{"url":"科技园路.","date":"2017/07/13","name":"小华","neirong":"环境很好，很干净，很喜欢"}]
             */

            private int product_id;
            private String name;
            private int rating;
            private int price;
            private String url;
            private String number;
            private String introduce;
            private String kind;
            private List<CommentBeanXX> comment;

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRating() {
                return rating;
            }

            public void setRating(int rating) {
                this.rating = rating;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public List<CommentBeanXX> getComment() {
                return comment;
            }

            public void setComment(List<CommentBeanXX> comment) {
                this.comment = comment;
            }

            public static class CommentBeanXX {
                /**
                 * url : 科技园路.
                 * date : 2017/07/13
                 * name : 小华
                 * neirong : 环境很好，很干净，很喜欢
                 */

                private String url;
                private String date;
                private String name;
                private String neirong;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNeirong() {
                    return neirong;
                }

                public void setNeirong(String neirong) {
                    this.neirong = neirong;
                }
            }
        }
    }
}
