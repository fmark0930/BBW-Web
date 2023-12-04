/**
 * @author FW
 * @date 2023/10/16 17:16
 * @Software: IntelliJ IDEA WEB-study
 * @version 1.0
 */
package com.bbwweb.Result;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入九位以内金额");
        Integer number = input.nextInt();
        String str = number.toString();
        for (int l = 9-str.length() ;l>0;l--){
            str = "0"+str;
        }
        String temp = "";
        for (int i = str.length()-1,j = 1;i>=0;i--,j++){
            String c = String.valueOf((str.charAt(i)));
            switch (j){
                case 1:temp = "元"+temp;break;
                case 2:temp = "拾"+temp;break;
                case 3:temp = "佰"+temp;break;
                case 4:temp = "仟"+temp;break;
                case 5:temp = "万"+temp;break;
                case 6:temp = "拾"+temp;break;
                case 7:temp = "佰"+temp;break;
                case 8:temp = "仟"+temp;break;
                case 9:temp = "亿"+temp;break;
            }
            switch (c){
                case "1":temp ="壹"+temp;break;
                case "2":temp ="贰"+temp;break;
                case "3":temp ="叁"+temp;break;
                case "4":temp ="肆"+temp;break;
                case "5":temp ="伍"+temp;break;
                case "6":temp ="陆"+temp;break;
                case "7":temp ="柒"+temp;break;
                case "8":temp ="捌"+temp;break;
                case "9":temp ="玖"+temp;break;
                default:temp ="零"+temp;break;
            }

        }

        System.out.println(temp);
    }
}
