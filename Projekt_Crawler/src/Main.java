import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws InterruptedException, CrawlerException, IOException {

			LinkedList<String> list = new LinkedList<>();

			list.add(new String("http://home.agh.edu.pl/~ggorecki/IS_Java/students.txt"));
			Monitor monitor = new Monitor(list);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					monitor.addCrawlers();
				}
				catch (MonitorException e) {
					e.printStackTrace();
				}

			}
		}).start();

		try{
			monitor.cancel();
		}
		catch(InterruptedException e){
			e.printStackTrace();

		}

//	monitor.cancel();
	}

}
