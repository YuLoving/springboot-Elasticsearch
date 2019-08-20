package com.example.ccq.springelasticsearch.utils;
/**
* <p>Description: 车辆相关编码转成中文</p>  

* @author ZTY  

* @date 2019年8月20日
 */
public class CarNumToCN {
    //1.2.号牌种类代码定义
    public static String HaoPai(String num){
        String res="";
        switch (num){
            case "01":
                res="大型汽车";
                break;

            case "02":
                res="小型汽车";
                break;
            case "03":
                res="使馆汽车";
                break;
            case "04":
                res="领馆汽车";
                break;

            case "05":
                res="境外汽车";
                break;
            case "06":
                res="外籍汽车";
                break;

            case "07":
                res="普通摩托车";
                break;

            case "08":
                res="轻便摩托车";
                break;

            case "09":
                res="使馆摩托车";
                break;

            case "10":
                res="领馆摩托车";
                break;
            case "11":
                res="境外摩托车";
                break;

            case "12":
                res="外籍摩托车";
                break;

            case "13":
                res="低速车";
                break;
            case "14":
                res="拖拉机";
                break;
            case "15":
                res="挂车";
                break;

            case "51":
                res="新能源大型车";
                break;

            case "52":
                res="新能源小型车";
                break;

        }
        return res;
    }

    //1.3.燃料种类代码定义
        public static String fuel(String num){
            String res="";
            switch (num){
                case "A":
                    res="汽油";
                    break;

                case "B":
                    res="柴油";
                    break;
                case "C":
                    res="电驱动（电能驱动汽车）";
                    break;
                case "D":
                    res="混合油";
                    break;

                case "E":
                    res="天然气";
                    break;
                case "F":
                    res="液化石油气";
                    break;

                case "L":
                    res="甲醇";
                    break;

                case "M":
                    res="乙醇";
                    break;

                case "N":
                    res="太阳能";
                    break;

                case "O":
                    res="混合动力";
                    break;
                case "P":
                    res="氢";
                    break;

                case "Y":
                    res="无（仅限全挂车等无动力的）";
                    break;

                case "Z":
                    res="其他";
                    break;
            }
            return res;
        }

        //1.4.颜色列表
    public static String color(String num){
        String res="";
        switch (num){
            case "A":
                res="白";
                break;

            case "B":
                res="灰";
                break;
            case "C":
                res="黄";
                break;
            case "D":
                res="粉";
                break;

            case "E":
                res="红";
                break;
            case "F":
                res="紫";
                break;

            case "G":
                res="绿";
                break;

            case "H":
                res="蓝";
                break;

            case "I":
                res="棕";
                break;

            case "J":
                res="黑";
                break;
        }
        return res;
    }

    //1.5.使用性质列表
    public static String UseCharacter(String num){
        String res="";
        switch (num){
            case "A":
                res="非营运";
                break;

            case "B":
                res="公路客运";
                break;
            case "C":
                res="公交客运";
                break;
            case "D":
                res="出租客运";
                break;

            case "E":
                res="旅游客运";
                break;
            case "F":
                res="货运";
                break;

            case "G":
                res="租赁";
                break;

            case "H":
                res="警用";
                break;

            case "I":
                res="消防";
                break;

            case "J":
                res="救护";
                break;

            case "K":
               res = "工程救险";
                break;
            case "L":
                res = "营转非 ";
                break;
            case "M":
                res = "出租转非";
                break;
            case "T":
                res= "预约出租客运";
                break;
            case "U":
                res = "预约出租转非";
                break;
            case "N":
                res = "教练";
                break;
            case "O":
                res = "幼儿校车";
                break;
            case "P":
                res = "小学生校车";
                break;
            case "Q":
                res  = "初中生校车";
                break;
            case "R":
                res  = "危化品运输";
                break;
            case "S":
                res = "中小学生校车";
                break;
        }
        return res;
    }

    public static String carState(String state) {
        String key = state;
        switch (key) {
            case "A": return "正常";
            case "B": return "转出";
            case "C": return "被盗抢";
            case "D": return "停驶";
            case "E": return "注销";
            case "G": return "违法未处理";
            case "H": return "海关监管";
            case "I": return "事故未处理";
            case "J": return "嫌疑车";
            case "K": return "查封";
            case "L": return "暂扣";
            case "M": return "强制注销";
            case "N": return "事故逃逸";
            case "O": return "锁定";
            case "P": return "达到报废标准公告牌证作废";
            case "Q": return "逾期未检验";
        }
        return null;
    }
    public static String carType(String type) {
        String key = type;
        switch (key) {
            case "B11": return "重型普通半挂车";
            case "B12": return "重型厢式半挂车";
            case "B13": return "重型罐式半挂车";
            case "B14": return "重型平板半挂车";
            case "B15": return "重型集装箱半挂车";
            case "B16": return "重型自卸半挂车";
            case "B17": return "重型特殊结构半挂车";
            case "B18": return "重型仓栅式半挂车";
            case "B19": return "重型旅居半挂车";
            case "B1A": return "重型专项作业半挂车";
            case "B1B": return "重型低平板半挂车";
            case "B1E": return "货运专用车";
            case "B21": return "中型普通半挂车";
            case "B22": return "中型厢式半挂车";
            case "B23": return "中型罐式半挂车";
            case "B24": return "中型平板半挂车";
            case "B25": return "中型集装箱半挂车";
            case "B26": return "中型自卸半挂车";
            case "B27": return "中型特殊结构半挂车";
            case "B28": return "中型仓栅式半挂车";
            case "B29": return "中型旅居半挂车";
            case "B2A": return "中型专项作业半挂车";
            case "B2B": return "中型低平板半挂车";
            case "B31": return "轻型普通半挂车";
            case "B32": return "轻型厢式半挂车";
            case "B33": return "轻型罐式半挂车";
            case "B34": return "轻型平板半挂车";
            case "B35": return "轻型自卸半挂车";
            case "B36": return "轻型仓栅式半挂车";
            case "B37": return "轻型旅居半挂车";
            case "B38": return "轻型专项作业半挂车";
            case "B39": return "轻型低平板半挂车";
            case "D11": return "无轨电车";
            case "D12": return "有轨电车";
            case "G11": return "重型普通全挂车";
            case "G12": return "重型厢式全挂车";
            case "G13": return "重型罐式全挂车";
            case "G14": return "重型平板全挂车";
            case "G15": return "重型集装箱全挂车";
            case "G16": return "重型自卸全挂车";
            case "G17": return "重型仓栅式全挂车";
            case "G18": return "重型旅居全挂车";
            case "G19": return "重型专项作业全挂车";
            case "G21": return "中型普通全挂车";
            case "G22": return "中型厢式全挂车";
            case "G23": return "中型罐式全挂车";
            case "G24": return "中型平板全挂车";
            case "G25": return "中型集装箱全挂车";
            case "G26": return "中型自卸全挂车";
            case "G27": return "中型仓栅式全挂车";
            case "G28": return "中型旅居全挂车";
            case "G29": return "中型专项作业全挂车";
            case "G31": return "轻型普通全挂车";
            case "G32": return "轻型厢式全挂车";
            case "G33": return "轻型罐式全挂车";
            case "G34": return "轻型平板全挂车";
            case "G35": return "轻型自卸全挂车";
            case "G36": return "轻型仓栅式全挂车";
            case "G37": return "轻型旅居全挂车";
            case "G38": return "轻型专项作业全挂车";
            case "H11": return "重型普通货车";
            case "H12": return "重型厢式货车";
            case "H13": return "重型封闭货车";
            case "H14": return "重型罐式货车";
            case "H15": return "重型平板货车";
            case "H16": return "重型集装厢车";
            case "H17": return "重型自卸货车";
            case "H18": return "重型特殊结构货车";
            case "H19": return "重型仓栅式货车";
            case "H21": return "中型普通货车";
            case "H22": return "中型厢式货车";
            case "H23": return "中型封闭货车";
            case "H24": return "中型罐式货车";
            case "H25": return "中型平板货车";
            case "H26": return "中型集装厢车";
            case "H27": return "中型自卸货车";
            case "H28": return "中型特殊结构货车";
            case "H29": return "中型仓栅式货车";
            case "H31": return "轻型普通货车";
            case "H32": return "轻型厢式货车";
            case "H33": return "轻型封闭货车";
            case "H34": return "轻型罐式货车";
            case "H35": return "轻型平板货车";
            case "H37": return "轻型自卸货车";
            case "H38": return "轻型特殊结构货车";
            case "H39": return "轻型仓栅式货车";
            case "H41": return "微型普通货车";
            case "H42": return "微型厢式货车";
            case "H43": return "微型封闭货车";
            case "H44": return "微型罐式货车";
            case "H45": return "微型自卸货车";
            case "H46": return "微型特殊结构货车";
            case "H47": return "微型仓栅式货车";
            case "H51": return "普通低速货车";
            case "H52": return "厢式低速货车";
            case "H53": return "罐式低速货车";
            case "H54": return "自卸低速货车";
            case "H55": return "仓栅式低速货车";
            case "J11": return "轮式装载机械";
            case "J12": return "轮式挖掘机械";
            case "J13": return "轮式平地机械";
            case "K11": return "大型普通客车";
            case "K12": return "大型双层客车";
            case "K13": return "大型卧铺客车";
            case "K14": return "大型铰接客车";
            case "K15": return "大型越野客车";
            case "K16": return "大型轿车";
            case "K17": return "大型专用客车";
            case "K18": return "大型专用校车";
            case "K21": return "中型普通客车";
            case "K22": return "中型双层客车";
            case "K23": return "中型卧铺客车";
            case "K24": return "中型铰接客车";
            case "K25": return "中型越野客车";
            case "K26": return "中型轿车";
            case "K27": return "中型专用客车";
            case "K28": return "中型专用校车";
            case "K31": return "小型普通客车";
            case "K32": return "小型越野客车";
            case "K33": return "小型轿车";
            case "K34": return "小型专用客车";
            case "K38": return "小型专用校车";
            case "K39": return "小型面包车";
            case "K49": return "微型面包车";
            case "K41": return "微型普通客车";
            case "K42": return "微型越野客车";
            case "K43": return "微型轿车";
            case "M11": return "普通正三轮摩托车";
            case "M12": return "轻便正三轮摩托车";
            case "M13": return "正三轮载客摩托车";
            case "M14": return "正三轮载货摩托车";
            case "M15": return "侧三轮摩托车";
            case "M21": return "普通二轮摩托车";
            case "M22": return "轻便二轮摩托车";
            case "N11": return "三轮汽车";
            case "Q11": return "重型半挂牵引车";
            case "Q12": return "重型全挂牵引车";
            case "Q21": return "中型半挂牵引车";
            case "Q22": return "中型全挂牵引车";
            case "Q31": return "轻型半挂牵引车";
            case "Q32": return "轻型全挂牵引车";
            case "T11": return "大型轮式拖拉机";
            case "T21": return "小型轮式拖拉机";
            case "T22": return "手扶拖拉机";
            case "T23": return "手扶变形运输机";
            case "X99": return "其它";
            case "Z11": return "大型专项作业车";
            case "Z21": return "中型专项作业车";
            case "Z31": return "小型专项作业车";
            case "Z41": return "微型专项作业车";
            case "Z51": return "重型专项作业车";
            case "Z71": return "轻型专项作业车";
            case "Z": return "其它";
        }
        return null;
    }


    public static void main(String[] args) {
        String s = HaoPai("08");
        String c = fuel("C");
        String j = color("J");
        String s1 = UseCharacter("S");

        String h = carState("H");
        String z71 = carType("Z71");
        System.out.println(s1);
        System.out.println(s);
        System.out.println(c);
        System.out.println(j);
        System.out.println(h);
        System.out.println(z71);




    }
}
