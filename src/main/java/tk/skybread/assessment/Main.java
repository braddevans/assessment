package tk.skybread.assessment;

import com.wirefreethought.geodb.client.GeoDbApi;
import com.wirefreethought.geodb.client.model.*;
import com.wirefreethought.geodb.client.net.GeoDbApiClient;
import com.wirefreethought.geodb.client.request.FindRegionPlacesRequest;
import com.wirefreethought.geodb.client.request.FindRegionsRequest;
import com.wirefreethought.geodb.client.request.PlaceRequestType;
import groovy.lang.Singleton;
import tk.skybread.assessment.Database.DatabaseImpl;
import tk.skybread.assessment.Utils.NewTabUtil;
import tk.skybread.assessment.view.LoggerTab;
import tk.skybread.assessment.view.MainWindow;
import tk.skybread.assessment.view.TopMenu;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Main extends JFrame {
	private JFrame frame = new JFrame("Assessment Application");
	private static Main instance;
	private static GeoDbApiClient apiClient = new GeoDbApiClient(GeoDbInstanceType.FREE);
	private static GeoDbApi geoDbApi = new GeoDbApi(apiClient);

	public static Main getInstance() {
		return instance;
	}

	public Main() {
		instance = this;
		try {

			frame.getContentPane().add(BorderLayout.NORTH, TopMenu.mbar());
			frame.add(NewTabUtil.add("Main", MainWindow.init()));
			frame.add(NewTabUtil.add("Logger", LoggerTab.panel()), BorderLayout.CENTER);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.pack();
			frame.setMinimumSize(new Dimension(1200, 900));
			frame.setVisible(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Main main = new Main();

		//populate city's table with cities
		//not need to get request size to iterate through
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		Scanner sc = new Scanner(new File(s + "/uk-cities.txt"));
		ArrayList<String> list = new ArrayList<String>();
		while (sc.hasNext()){
			list.add(sc.next());
		}
		sc.close();

		for(int i = 0; i < list.size(); i++) {
			System.out.println("adding: " + list.get(i));
			LoggerTab.Logger(Level.INFO, "adding: " + list.get(i));
			DatabaseImpl.addToDb(i, list.get(i), "not yet implemented", 0);
		}

		LoggerTab.Logger(Level.INFO,"Startup Complete");
	}
}
