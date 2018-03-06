package com.atguigu.im.homeadapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/11/11.
 */

public class ChannelBean implements Serializable{


    /**
     * code : 200
     * msg : 请求成功
     * result : {"banner_info":[{"image":"/new/img/act/z2.png","option":3,"type":0},{"image":"/new/img/act/z4.png","option":2,"type":0},{"image":"/new/img/act/z3.png","option":1,"type":0}],"channel_info":[{"channel_name":"美食","image":"/new/img/channel/c1.png","value":{"channel_id":"8"}},{"channel_name":"购物","image":"/new/img/channel/c2.png","value":{"channel_id":"4"}},{"channel_name":"玩乐","image":"/new/img/channel/c3.png","value":{"channel_id":"3"}}],"KTV":[{"name":"SingYoung 复合式KTV","rating":5,"price":"43元起","length":55,"location":"世贸中心5楼","url":"/new/img/ktv/k1.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k3.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"琴潮量贩KTV","rating":5,"price":"63元起","length":30,"location":"金茂大厦5层","url":"/new/img/ktv/k2.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k3.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"蓝巨星量贩KTV","rating":5,"price":"16元起","length":411,"location":"金茂大厦5层","url":"/new/img/ktv/k3.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"凯乐迪量贩KTV","rating":5,"price":"38元起","length":100,"location":"金茂大厦4层","url":"/new/img/ktv/k4.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"麦浪时尚KTV","rating":5,"price":"43元起","length":9,"location":"世贸中心5楼","url":"/new/img/ktv/k5.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k4.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"M8量贩KTV","rating":5,"price":"63元起","length":1,"location":"金茂大厦5层","url":"/new/img/ktv/k6.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k4.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"牧歌量贩KTV","rating":5,"price":"16元起","length":200,"location":"金茂大厦5层","url":"/new/img/ktv/k7.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"曼巴会所","rating":5,"price":"38元起","length":100,"location":"金茂大厦4层","url":"/new/img/ktv/k8.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"爱一起KTV","rating":5,"price":"38元起","length":100,"location":"金茂大厦4层","url":"/new/img/ktv/k9.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"嘉华KTV","rating":5,"price":"38元起","length":1124,"location":"金茂大厦4层","url":"/new/img/ktv/k10.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"普乐迪","rating":5,"price":"38元起","length":1004,"location":"金茂大厦4层","url":"/new/img/ktv/k11.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"新乐迪","rating":5,"price":"38元起","length":105,"location":"金茂大厦4层","url":"/new/img/ktv/k12.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"歌尚KTV","rating":5,"price":"38元起","length":4,"location":"金茂大厦4层","url":"/new/img/ktv/k13.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"简秀爱KTV","rating":5,"price":"38元起","length":87,"location":"金茂大厦4层","url":"/new/img/ktv/k14.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"酷K量贩KTV","rating":5,"price":"38元起","length":177,"location":"金茂大厦4层","url":"/new/img/ktv/k15.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"盛世唐朝量贩KTV","rating":5,"price":"38元起","length":177,"location":"金茂大厦4层","url":"/new/img/ktv/k16.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"百乐汇时尚主题量贩KTV","rating":5,"price":"38元起","length":197,"location":"金茂大厦4层","url":"/new/img/ktv/k18.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"金典量贩KTV","rating":5,"price":"38元起","length":155,"location":"金茂大厦4层","url":"/new/img/ktv/k19.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"V-SHOW（唯秀）主题KTV","rating":5,"price":"38元起","length":45,"location":"金茂大厦4层","url":"/new/img/ktv/k20.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"纯歌派对量贩KTV","rating":5,"price":"38元起","length":67,"location":"金茂大厦4层","url":"/new/img/ktv/k20.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"百渡纯K","rating":5,"price":"38元起","length":1113,"location":"金茂大厦4层","url":"/new/img/ktv/k21.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"阿修罗量贩KTV","rating":5,"price":"38元起","length":1456,"location":"金茂大厦4层","url":"/new/img/ktv/k22.png","number":"100人以上消费","introduce":"营业时间：周一至周日","buy":[{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k1.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]}],"FOOD":[{"name":"布恬私家蛋糕","rating":5,"price":"20元/人","length":"距离55m","location":"婺城区人民东路118号","url":"/new/img/food/f1.png","number":"100人以上消费","introduce":"营业时间：周一至周日","kind":"美食甜点","buy":[{"url":"/new/img/food/f1/ff1.png","price":98,"name":"8英寸慕斯蛋糕 1 个","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f1/ff2.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f1/ff3.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"贝克汉堡","rating":5,"price":"15元/人","length":"距离55m","location":"婺城区浙江师范大学北门","url":"/new/img/food/f3.png","number":"100人以上消费","introduce":"营业时间：周一至周日","kind":"快餐饮食","buy":[{"url":"/new/img/food/f2/f2.png","price":98,"name":"8英寸慕斯蛋糕 1 个","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f2/f2.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f2/f2.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"Apple tree 树上甜点蛋糕店","rating":5,"price":"14元/人","length":"距离55m","location":"婺城区同心路172号","url":"/new/img/food/f4.png","number":"100人以上消费","introduce":"营业时间：周一至周日","kind":"美食甜点","buy":[{"url":"/new/img/food/f1/ff1.png","price":98,"name":"8英寸慕斯蛋糕 1 个","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f1/ff2.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f1/ff3.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"鸭血粉丝","rating":5,"price":"10元/人","length":"距离792m","location":"婺城区骆家塘畅达街105号","url":"/new/img/food/f2.png","number":"100人以上消费","introduce":"营业时间：周一至周日","kind":"特色小吃","buy":[{"url":"/new/img/food/f3/f1.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f3/f1.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f3/f2.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"小布丁蛋糕","rating":5,"price":"20元/人","length":"距离55m","location":"婺城区人民东路118号","url":"/new/img/food/f1.png","number":"100人以上消费","introduce":"营业时间：周一至周日","kind":"美食甜点","buy":[{"url":"/new/img/food/f4/f2.png","price":98,"name":"8英寸慕斯蛋糕 1 个","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f4/f1.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f4/f3.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"光影汉堡店","rating":5,"price":"15元/人","length":"距离55m","location":"婺城区浙江师范大学北门","url":"/new/img/food/f3.png","number":"100人以上消费","introduce":"营业时间：周一至周日","kind":"快餐饮食","buy":[{"url":"/new/img/food/f4/f2.png","price":98,"name":"8英寸慕斯蛋糕 1 个","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f4/f1.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f4/f3.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"Apple tree 树上甜点","rating":5,"price":"14元/人","length":"距离55m","location":"婺城区同心路172号","url":"/new/img/food/f4.png","number":"100人以上消费","introduce":"营业时间：周一至周日","kind":"美食甜点","buy":[{"url":"/new/img/food/f4/f2.png","price":98,"name":"8英寸慕斯蛋糕 1 个","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f4/f1.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f4/f3.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"name":"鸭血粉丝","rating":5,"price":"10元/人","length":"距离792m","location":"婺城区骆家塘畅达街105号","url":"/new/img/food/f2.png","number":"100人以上消费","introduce":"营业时间：周一至周日","kind":"特色小吃","buy":[{"url":"/new/img/food/f4/f2.png","price":98,"name":"8英寸慕斯蛋糕 1 个","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f4/f1.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f4/f3.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"}],"comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]}],"CLOSE":[{"product_id":6633,"name":"【画影】现货  小狐狸羽织 ","rating":5,"price":132,"url":"/new/img/close/c3.jpg","number":"100人以上消费","introduce":"红黑款是现货哦·~现货不配送发带~~红黑款是现货哦·~现货不配送发带~~红黑款是现货哦·~现货不配送发带~~ 重要的事情说三次~","kind":"羽绒外套","comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"product_id":3831,"name":"【喵鹿酱】超萌 假透肉 拼接 踩脚过膝打底袜 裤袜-加绒保暖","rating":5,"price":54,"url":"/new/img/close/c2.jpg","number":"100人以上消费","introduce":"衣服材料是。。。。。","kind":"打底裤","comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"product_id":6969,"name":"【涉川原创】周边小物 手感超好超萌的兔毛小兔纸包挂/毛绒玩偶","rating":5,"price":54,"url":"/new/img/close/c4.jpg","number":"兔兔刚到手会有浮毛，多拍拍抖抖吹吹揉揉就好啦(","introduce":"衣服材料是。。。。。","kind":"毛绒手套","comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"product_id":3845,"name":"预售 【世界线的彼岸】炸鸡块 颜文字 羊毛呢贝雷帽 圆润烧饼帽 保暖","rating":1,"price":49,"url":"/new/img/close/c1.jpg","number":"100人以上消费","introduce":"衣服材料是。。。。。","kind":"毛绒帽子","comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"product_id":6633,"name":"【画影】现货  小狐狸羽织 ","rating":5,"price":132,"url":"/new/img/close/c3.jpg","number":"100人以上消费","introduce":"红黑款是现货哦·~现货不配送发带~~红黑款是现货哦·~现货不配送发带~~红黑款是现货哦·~现货不配送发带~~ 重要的事情说三次~","kind":"羽绒外套","comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"product_id":3831,"name":"【喵鹿酱】超萌 假透肉 拼接 踩脚过膝打底袜 裤袜-加绒保暖","rating":5,"price":54,"url":"/new/img/close/c2.jpg","number":"100人以上消费","introduce":"衣服材料是。。。。。","kind":"打底裤","comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"product_id":6969,"name":"【涉川原创】周边小物 手感超好超萌的兔毛小兔纸包挂/毛绒玩偶","rating":5,"price":54,"url":"/new/img/close/c4.jpg","number":"兔兔刚到手会有浮毛，多拍拍抖抖吹吹揉揉就好啦(","introduce":"衣服材料是。。。。。","kind":"毛绒手套","comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]},{"product_id":3845,"name":"预售 【世界线的彼岸】炸鸡块 颜文字 羊毛呢贝雷帽 圆润烧饼帽 保暖","rating":1,"price":49,"url":"/new/img/close/c1.jpg","number":"100人以上消费","introduce":"衣服材料是。。。。。","kind":"毛绒帽子","comment":[{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]}]}
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

        public static class KTVBean {
            /**
             * name : SingYoung 复合式KTV
             * rating : 5
             * price : 43元起
             * length : 55
             * location : 世贸中心5楼
             * url : /new/img/ktv/k1.png
             * number : 100人以上消费
             * introduce : 营业时间：周一至周日
             * buy : [{"url":"/new/img/ktv/k2.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"},{"url":"/new/img/ktv/k3.png","price":128,"name":"周一至周日欢唱3选1","selled":"已售100"}]
             * comment : [{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]
             */

            private String name;
            private int rating;
            private String price;
            private int length;
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

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
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
                 * url : /new/img/ktv/k2.png
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
                 * url : /img/1.jpg
                 * name : 小华
                 * neirong : 环境很好，很干净，很喜欢
                 */

                private String url;
                private String name;
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
             * url : /new/img/food/f1.png
             * number : 100人以上消费
             * introduce : 营业时间：周一至周日
             * kind : 美食甜点
             * buy : [{"url":"/new/img/food/f1/ff1.png","price":98,"name":"8英寸慕斯蛋糕 1 个","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f1/ff2.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"},{"url":"/new/img/food/f1/ff3.png","price":128,"name":"布恬私家蛋糕","introduce":"慕斯蛋糕1个，约8英寸，正方形","rating":4,"selled":"已售100"}]
             * comment : [{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]
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
                 * url : /new/img/food/f1/ff1.png
                 * price : 98
                 * name : 8英寸慕斯蛋糕 1 个
                 * introduce : 慕斯蛋糕1个，约8英寸，正方形
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
                 * url : /img/1.jpg
                 * name : 小华
                 * neirong : 环境很好，很干净，很喜欢
                 */

                private String url;
                private String name;
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
             * price : 132.0
             * url : /new/img/close/c3.jpg
             * number : 100人以上消费
             * introduce : 红黑款是现货哦·~现货不配送发带~~红黑款是现货哦·~现货不配送发带~~红黑款是现货哦·~现货不配送发带~~ 重要的事情说三次~
             * kind : 羽绒外套
             * comment : [{"url":"/img/1.jpg","name":"小华","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/2.jpg","name":"小明","neirong":"环境很好，很干净，很喜欢"},{"url":"/img/3.jpg","name":"小志","neirong":"环境很好，很干净，很喜欢"}]
             */

            private int product_id;
            private String name;
            private int rating;
            private double price;
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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
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
                 * url : /img/1.jpg
                 * name : 小华
                 * neirong : 环境很好，很干净，很喜欢
                 */

                private String url;
                private String name;
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
