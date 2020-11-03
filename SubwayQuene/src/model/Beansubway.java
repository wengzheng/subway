package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;



public class Beansubway {
	private String line;
	private String station;
	public Beansubway prev;
    public Beansubway next;
    private Map<Beansubway,LinkedHashSet<Beansubway>> OSetMap = new HashMap<Beansubway,LinkedHashSet<Beansubway>>();
    
	public String getLine() {
		return line;
	}
	
	public void setLine(String line) {
		this.line = line;
	}
	
	public String getStation() {
		return station;
	}
	
	public void setStation(String station) {
		this.station = station;
	}
	
	public Beansubway(String station) {
        this.station = station;
    }
	
	public LinkedHashSet<Beansubway> getAllPassedStations(Beansubway station) {
        if(OSetMap.get(station) == null){
            LinkedHashSet<Beansubway> set = new LinkedHashSet<Beansubway>();
            set.add(this);
            OSetMap.put(station, set);
        }
        return OSetMap.get(station);
    }
	
	public Map<Beansubway, LinkedHashSet<Beansubway>> getOrderSetMap() {
        return OSetMap;
    }
	
	public boolean equals(Object obj) {
        if(this == obj){
            return true;
        } else if(obj instanceof Beansubway){
        	Beansubway s = (Beansubway) obj;
            if(s.getStation().equals(this.getStation())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
	public int hashCode() {//重写比较方法
        return station.hashCode();
    }
}
