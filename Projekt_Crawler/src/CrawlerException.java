
public class CrawlerException extends Exception {

	/*public String Error;


	public CrawlerException(String msg) {
		super(msg);
	}
	
	public CrawlerException() 
	{
		Error = "Opening path failed. Application will be closed.";
	}
	
	@Override
	public String getMessage()
	{
		return Error;
	}
	
	public void handleException() throws InterruptedException
	{
		System.out.println(getMessage());
		Thread.sleep(2000);
		System.exit(0);
	}
	*/
	private String msg;

	public CrawlerException(String msg) {
		super(msg);
	}

	@Override
	public String toString(){
		return msg;
	}
}
