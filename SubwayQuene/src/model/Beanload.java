package model;

import java.util.ArrayList;
import java.util.List;

import control.station;


public class Beanload {
    private String name;
    private List<Beansubway> passStations;////经过的站点
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Beansubway> getPassStations() {
		return passStations;
	}
	public void setPassStations(List<Beansubway> passStations) {
		this.passStations = passStations;
	}
	public List<String> loadAllLineName(){//罗列所有线路名字
	        List<String> list=new ArrayList<>();
	        for(int i=0;i<station.ALine.size();i++){
	            list.add(station.ALine.get(i).getName());
	        }
	        return list;
	    }
}

