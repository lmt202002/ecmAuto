package common;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
public class ReadAndWriteJSON {

    public static void main(String args) throws IOException {

    }
    public static JSONObject readUserData() throws IOException {
        File file=new File("E:\\github\\company\\src\\main\\test\\case\\common\\userData.json");
        String content= FileUtils.readFileToString(file,"UTF-8");
        JSONObject jsonObject=new JSONObject(content);
//        System.out.println("姓名是："+jsonObject.getString("name"));
//        System.out.println("年龄："+jsonObject.getDouble("age"));
//        System.out.println("学到的技能："+jsonObject.getJSONArray("major").getString(0));
//        System.out.println("邮编："+jsonObject.getJSONObject("Nativeplace2").getString("邮编"));
//        jsonObject.getJSONArray("major").get(0);
//        System.out.println("atds1的手机是："+jsonObject.getJSONObject("atds1").getString("phone"));
        return jsonObject;
    }
    public static JSONObject writeUserData() throws IOException {
        JSONObject jsonObject = null;
        try {
            File file = new File("E:\\github\\company\\src\\main\\test\\case\\common\\test.json");
            String content = FileUtils.readFileToString(file, "UTF-8");//读取所有json数据为字符串
            jsonObject = new JSONObject(content);
            String contenttmp = jsonObject.getJSONObject("atds2").toString();//取出指定人数据字符串
            JSONObject jsontemp = new JSONObject(contenttmp);//把指定人数据转为JSON对象
            jsontemp.put("reference", "9999999");//给对象的指定项赋值
            jsonObject.put("atds2", jsontemp);   //把指定对象的JSON数据再写入所有人的JSON数据中
            System.out.println(jsontemp.toString());
            FileWriter fw = new FileWriter("E:\\github\\company\\src\\main\\test\\case\\common\\test.json");//指定写入的文件路径
            PrintWriter out = new PrintWriter(fw); //创建写对象
            out.write(jsonObject.toString()); //把修改后的所有人JSON数据写到文件中
            out.println();
            fw.close();
            out.close();

        } catch (Exception e) {
            System.out.println("JSON数据格式错误或文件不存在!");
        }
        return jsonObject;
    }

}