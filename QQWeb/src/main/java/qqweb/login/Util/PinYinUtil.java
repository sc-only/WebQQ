package qqweb.login.Util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 汉字转换为拼音
 * @author Red
 */
public class PinYinUtil {
    /**
     * 测试main方法
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(toFirstChar("汉字转换为拼音").toUpperCase()); //转为首字母大写
        System.out.println(toPinyin("汉字转换为拼音"));
    }
    /**
     * 获取字符串拼音的第一个字母
     * @param chinese
     * @return
     */
    public static String toFirstChar(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    /**
     * 汉字转为拼音
     * @param chinese
     * @return
     */
    public static String toPinyin(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    public static String getPinYin(String word){
        String pinyin ="";
        byte[] bytes = (String.valueOf(word)).getBytes();
        if (bytes.equals("") || bytes.length > 2 || bytes.length <= 0) {
            // 错误引用,非合法字符
            return "";
        }
        if (bytes.length == 1) {
            // 英文字符
            return "";
        }
        if (bytes.length == 2) {
            //中文字符
            //得到字符的unicode十六进制编码,并转为大写
            String wordCode = Integer.toHexString(String.valueOf(word).codePointAt(0)).toUpperCase();;
            String pattern = "^"+wordCode+"\\s*([A-Z]+)";  //多音字只匹配第一个拼音
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);
            File file=new File("Mandarin.dat");      //此中的mandarin.dat即为查表文件
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                String temp= "";
                while((temp=br.readLine())!= "")
                {
                    Matcher m = r.matcher(temp);
                    if (m.find( )) {
                        pinyin = m.group(1);

                    }
                }
                br.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return pinyin;

    }
}
