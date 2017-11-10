package com.atguigu.im.model.bean;

import java.util.List;

/**
 * Created by Hmz on 2017/7/5.
 */

public class ResultBeanData {


    /**
     * code : 200
     * msg : 请求成功
     * result : {"banner_info":[{"image":"/new/img/act/z2.png","option":3,"type":0},{"image":"/new/img/act/z4.png","option":2,"type":0},{"image":"/new/img/act/z3.png","option":1,"type":0}],"channel_info":[{"channel_name":"美食","image":"/new/img/channel/c1.png","value":{"channel_id":"8"}},{"channel_name":"酒店","image":"/new/img/channel/c2.png","value":{"channel_id":"4"}},{"channel_name":"玩乐","image":"/new/img/channel/c3.png","value":{"channel_id":"3"}}]}
     */

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

        public static class BannerInfoBean {
            /**
             * image : /new/img/act/z2.png
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
             * image : /new/img/channel/c1.png
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
    }
}
