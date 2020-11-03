package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Beansubway;
import ui.FrmMain;

public class subwaystarter {

		public static void main(String[] args) throws IOException {
			// 读取subway.txt中的站点和线路信息
			String str=null;
	        File file = new File("subway.txt");
	        BufferedReader in = new BufferedReader(new FileReader(file));
			while((str = in.readLine()) != null) {
				station.lines.add(str);
				List<Beansubway> line = new ArrayList<Beansubway>();
	            station.setStations(line,str);
	            station.total += line.size();
	            station.LSet.add(line);
			}
			in.close();
			new FrmMain();
		}

}
