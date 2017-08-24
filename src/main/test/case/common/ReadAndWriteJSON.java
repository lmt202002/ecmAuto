package common;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import org.json.JSONObject;
public class ReadAndWriteJSON {

    public static void main(String args) throws IOException {

    }

    public static JSONObject readUserData() throws IOException {
        /**读取文件所有JSON数据赋给JSON对象*/
        File file=new File("E:\\github\\company\\src\\main\\test\\case\\common\\userData.json");
        String content= FileUtils.readFileToString(file,"UTF-8");
        JSONObject jsonObject=new JSONObject(content);
        return jsonObject;
    }
    public static JSONObject writeUserData(String wechat,String level,String parent,String reference) throws IOException {
        /**根据wechat找到等级、上级、推荐人，并根据给的参数值修改完后写入JSON文件*/
        JSONObject jsonObject=null;
        try {
            File file = new File("E:\\github\\company\\src\\main\\test\\case\\common\\userData.json");
            String content = FileUtils.readFileToString(file, "UTF-8");//读取所有json数据为字符串
            jsonObject = new JSONObject(content);//把字符串转为json对象
            String contenttmp = jsonObject.getJSONObject(wechat).toString();//取出指定人数据字符串
            JSONObject jsontemp = new JSONObject(contenttmp);//把指定人数据转为JSON对象
            jsontemp.put("level", level);//给对象的当前级别key赋值
            jsontemp.put("parent", parent);//给对象的上级key赋值
            jsontemp.put("reference", reference);//给对象的推荐人key赋值
            jsonObject.put(wechat, jsontemp);   //把指定对象的JSON数据再写入所有人的JSON数据中
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
    public static JSONObject writeUserData(String wechat,String parent,String reference) throws IOException {
        /**根据wechat找到上级、推荐人，并根据给的参数值修改完后写入JSON文件*/
        JSONObject jsonObject=null;
        try {
            File file = new File("E:\\github\\company\\src\\main\\test\\case\\common\\userData.json");
            String content = FileUtils.readFileToString(file, "UTF-8");//读取所有json数据为字符串
            jsonObject = new JSONObject(content);//把字符串转为json对象
            String contenttmp = jsonObject.getJSONObject(wechat).toString();//取出指定人数据字符串
            JSONObject jsontemp = new JSONObject(contenttmp);//把指定人数据转为JSON对象
            jsontemp.put("parent", parent);//给对象的上级key赋值
            jsontemp.put("reference", reference);//给对象的推荐人key赋值
            jsonObject.put(wechat, jsontemp);   //把指定对象的JSON数据再写入所有人的JSON数据中
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
    public static JSONObject writeUserData(String wechat,String reference) throws IOException {
        /**根据wechat找到推荐人，并根据给的参数值修改完后写入JSON文件*/
        JSONObject jsonObject = null;
        try {
            File file = new File("E:\\github\\company\\src\\main\\test\\case\\common\\userData.json");
            String content = FileUtils.readFileToString(file, "UTF-8");//读取所有json数据为字符串
            jsonObject = new JSONObject(content);//把字符串转为json对象
            String contenttmp = jsonObject.getJSONObject(wechat).toString();//取出指定人数据字符串
            JSONObject jsontemp = new JSONObject(contenttmp);//把指定人数据转为JSON对象
            jsontemp.put("reference", reference);//给对象的推荐人key赋值
            jsonObject.put(wechat, jsontemp);   //把指定对象的JSON数据再写入所有人的JSON数据中
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