package com.example.proj.zhaohuo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class userInfoActivity extends AppCompatActivity {
    private ConnectHelper connectHelper;
    private List<HashMap<String,String>> data;
    private List<String> more;
    private String School,Name,id,grade;
    private String[] type = {"学校","姓名","学号","年级"};
    private ListView lv1;
    private String get_user_info;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        lv1 = (ListView)findViewById(R.id.user_info_listview);
        connectHelper = new ConnectHelper();
        get_user_info = connectHelper.url+"/Service/get_user_info.jsp";
        HashMap<String,String> temp = new HashMap<>();
        HashMap<String,String> temp1 = new HashMap<>();
        HashMap<String,String> temp2 = new HashMap<>();
        HashMap<String,String> temp3 = new HashMap<>();
        School = "中山大学";
        Name = "洪哲铮";
        id = "14353085";
        grade ="14级";
        data = new ArrayList<>();
        temp.put("Type",type[0]);temp.put("Info",School);data.add(temp);
        temp1.put("Type",type[1]); temp1.put("Info",Name);data.add(temp1);
        temp2.put("Type",type[2]);temp2.put("Info",id);data.add(temp2);
        temp3.put("Type",type[3]);temp3.put("Info",grade);data.add(temp3);
        simpleAdapter = new SimpleAdapter(this,data,R.layout.user_info_item_show,new String[]{"Type","Info"},new int[]{R.id.show_user_type,R.id.show_user_info});
        lv1.setAdapter(simpleAdapter);
    }
    private class DownloadWebpageText extends AsyncTask<String,Integer,List<String>> {
        @Override
        protected List<String> doInBackground(String... urls) {
            try {
                return connectHelper.downloadUrl(urls[0]); //连接并下载数据
            } catch (IOException e) {
                e.printStackTrace();
                List<String> reList = new LinkedList<>();//返回的字符串数组，若只有1个字符串取reList.get(0)
                return reList;
            }
        }
        @Override
        protected void onPostExecute(List<String> result) {
            if(result != null){
                if(result.size() == 0){
                    Toast.makeText(getApplicationContext(),"没有返回值，请再试一次！",Toast.LENGTH_SHORT).show();
                }else{
                }
            }
        }
    }
}
