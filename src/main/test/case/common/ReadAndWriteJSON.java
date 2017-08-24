package common;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import org.json.JSONObject;
public class ReadAndWriteJSON {

    public static void main(String args) throws IOException {

    }
    public static void test() throws IOException {
        File file=new File("E:\\github\\company\\src\\main\\test\\case\\common\\test.json");
        String content= FileUtils.readFileToString(file,"UTF-8");
        JSONObject jsonObject=new JSONObject(content);
        System.out.println("姓名是："+jsonObject.getString("name"));
        System.out.println("年龄："+jsonObject.getDouble("age"));
        System.out.println("学到的技能："+jsonObject.getJSONArray("major").getString(0));
        System.out.println("国家："+jsonObject.getJSONObject("Nativeplace").getString("country"));
//        jsonObject.getJSONArray("major").get(0);

    }

}