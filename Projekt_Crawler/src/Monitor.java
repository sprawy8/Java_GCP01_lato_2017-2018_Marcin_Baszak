import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Monitor extends Thread {
    private int ThreadNumber;
    private boolean isLoop = true;
   // public List<String> linkList = new ArrayList<>();
    private LinkedList<String> linksList = new LinkedList<String>();
    private LinkedList<Crawler> crawlerList = new LinkedList<Crawler>();
    private Logger[] loggers = new Logger[]{
            new ConsoleLogger(),
            // new MailLogger()
    };

    public Monitor() {
        isLoop=true;
        ThreadNumber = 100;

    }

    public Monitor(LinkedList<String> urls) {
        ThreadNumber=100;
        isLoop = true;
        this.linksList = urls;
    }

    public void setThreadNumber(int threadNumber) {
        ThreadNumber = threadNumber;
    }

    public int getThreadNumber() {
        return ThreadNumber;
    }

    @Override
    public void run() {
     //   while(isLoop) {
            for (Crawler crawler : crawlerList) {
                try{
                    crawler.run();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
      //  }
    }

    public void addCrawlers() throws MonitorException {
       if (ThreadNumber < linksList.size()) throw new MonitorException("Error, too much threads");
        for (String link : linksList) {
            Crawler crawler = new Crawler(link);
            try{
                crawler.run();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            crawlerList.add(crawler);
        }



    }

    public synchronized void cancel() throws InterruptedException {
        this.isLoop = false;
        for (Crawler c : crawlerList) {
            c.postCancel();
            this.join();
        }
    }
}
