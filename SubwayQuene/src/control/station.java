package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import model.Beanload;
import model.Beansubway;


public class station {
	public static Set<List<Beansubway>> LSet = new HashSet<List<Beansubway>>();//所有线集合
    public static List<Beanload> ALine = new ArrayList<Beanload>();//所有集合
    public static Map<String, List<Beansubway>> map = new HashMap<String, List<Beansubway>>();
    public static List<Beansubway> mapOfStation = new ArrayList<Beansubway>();//存放所有站点
    public static int total = 0;//总的站点数量
    public static List<String> lines = new ArrayList<>();
	public static void setStations(List<Beansubway> linemodel, String str) {
        String line=str;
        String[] line1Arr = line.split(" ");
        int flag=0;
        String linename = null;
        Beanload newline=new Beanload();
        for(String s : line1Arr){
            if(flag==0){
                linename=s;
                newline.setName(linename);
            }
            else{
            	Beansubway a=new Beansubway(s);
                for(List<Beansubway> lineP : station.LSet){
                    if(lineP.contains(a)){
                    	station.total -=1;//在其他线路出现过就减1站，不然会重复计算
                        break;
                    }
                }
                mapOfStation.add(a);
                a.setLine(linename);
                linemodel.add(a);
            }
            flag=1;
        }
        for(int i =0;i<linemodel.size();i++){
            if(i<linemodel.size()-1){
                linemodel.get(i).next = linemodel.get(i+1);
                linemodel.get(i+1).prev = linemodel.get(i);
            }
        }
        newline.setPassStations(linemodel);
        ALine.add(newline);
        map.put(linename,linemodel);
    }
}
