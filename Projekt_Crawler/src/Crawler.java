import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Crawler {
    private URL pathURL;
    boolean loop=true;
    private List<IterationListener> iterationStartedListeners = new ArrayList<>();
    private List<IterationListener> iterationCompletedListeners = new ArrayList<>();
    private List<StudentListener> studentAddedListeners = new ArrayList<>();
    private List<StudentListener> studentRemovedListeners = new ArrayList<>();


    private List<Student> studentsList;
    private Set<Student> studentsSet;

    private String urlString;

    Logger []loggers;

    public Crawler(String urlString) {
       /* this.urlString = urlString;
        this.pathURL = new URL(urlString);*/
        studentsSet = new HashSet();
        try {
            this.pathURL = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            loggers = new Logger[]
                    {
                            new ConsoleLogger(),
                            //new MailLogger()
                    };
        }
    }

    public void addIterationStartedListener(IterationListener listener){
        iterationStartedListeners.add(listener);
    }

    public void removeIterationStartedListener(IterationListener listener){ iterationStartedListeners.remove(listener); }


    public void addIterationCompletedListener(IterationListener listener){
        iterationCompletedListeners.add(listener);
    }

    public void removeIterationCpmpletedListener(IterationListener listener){iterationCompletedListeners.remove(listener); }


    public void addStudentAddedListener(StudentListener listener){
        studentAddedListeners.add(listener);
    }

    public void removeStudentAddedListener(StudentListener listener){
        studentAddedListeners.remove(listener);
    }


    public void addStudentRemovedListener(StudentListener listener){
        studentRemovedListeners.add(listener);
    }

    public void removeStudentRemovedListener(StudentListener listener){
        studentRemovedListeners.remove(listener);
    }


    public void postCancel()
    {
     loop=false;
    }


    public void run() throws Exception//throws CrawlerException, InterruptedException, IOException --> synchronized
    {
        int iteration =1;
        int i=0;

        while(loop)
        {

            if(pathURL == null) throw new CrawlerException("Zly adres");
            else
           studentsList = StudentsParser.parse(pathURL);
         //  studentsList = insertStudents(iteration);

            if(studentsList.isEmpty())

                System.out.print("Error, lista pusta");

            for(IterationListener el :iterationStartedListeners)
            {
                el.handle(iteration);
            }


            for(StudentListener el: studentAddedListeners){ //przechowywanie listenerow

                for(Student s : studentsList)
                {	if(studentsSet==null)
                {

                    studentsSet.add(s);
                    el.handle(s);
                    loggers[0].log("ADDED", s);
                    //loggers[1].log("ADDED", s);
                }

                else if(!studentsSet.contains(s))
                {
                    studentsSet.add(s);
                    el.handle(s);
                    loggers[0].log("ADDED", s);
                    //loggers[1].log("ADDED", s);
                }

                }

            }

            for(StudentListener el : studentRemovedListeners){
                List<Student> studentsToRemove = new ArrayList<Student>();

                for(Student s : studentsSet)
                {
                    if(studentsList == null) //usunieto wszystkich
                    {
                        studentsToRemove.add(s);
                        el.handle(s);
                        loggers[0].log("REMOVED",s);
                        //loggers[1].log("REMOVED", s);
                    }
                    else if(!studentsList.contains(s))
                    {
                        studentsToRemove.add(s);
                        el.handle(s);
                        loggers[0].log("REMOVED",s);
                        //loggers[1].log("REMOVED", s);
                    }
                }

                for(Student s : studentsToRemove)
                    studentsSet.remove(s);  //czyscimy lite studentsSet
            }

            //Thread.sleep(50);
            for(IterationListener el :iterationCompletedListeners)
            {
                el.handle(iteration);

            }

            iteration++;

            System.out.println("Age: " + "<" + ExtractAge(ExtremumMode.MIN) + "," + ExtractAge(ExtremumMode.MAX) + ">");
            System.out.println("Mark: " + "<" + ExtractMark(ExtremumMode.MIN) + "," + ExtractMark(ExtremumMode.MAX) + ">");

            show(extractStudents(OrderMode.MARK));
            i++;
            System.out.println("");
        }

    }

    public List<Student> insertStudents(int iteration)
    {
        List<Student> temp = null;
        File file;

        String file1 = "C:\\Users\\MarcinB\\Desktop\\Crawler1.txt";
        String file2 = "C:\\Users\\MarcinB\\Desktop\\Crawler2.txt";
        String file3 = "C:\\Users\\MarcinB\\Desktop\\Crawler3.txt";
        String file4 = "C:\\Users\\MarcinB\\Desktop\\Crawler4.txt";
        String file5 = "C:\\Users\\MarcinB\\Desktop\\Crawler5.txt";
        String file6 = "C:\\Users\\MarcinB\\Desktop\\Crawler6.txt";
        String file7 = "C:\\Users\\MarcinB\\Desktop\\Crawler7.txt";
        String file8 = "C:\\Users\\MarcinB\\Desktop\\Crawler8.txt";
        String file9 = "C:\\Users\\MarcinB\\Desktop\\Crawler9.txt";
        String file10 = "C:\\Users\\MarcinB\\Desktop\\Crawler10.txt";

        if(iteration == 1)
        {

            file = new File(file1);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error");
            }
        }

        else if(iteration == 2)
        {

            file = new File(file2);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error ");
            }
        }

        else if(iteration == 3)
        {

            file = new File(file3);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error");
            }
        }
        else if(iteration == 4)
        {

            file = new File(file4);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error");
            }
        }
        else if(iteration == 5)
        {

            file = new File(file5);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error");
            }
        }
        else if(iteration == 6)
        {

            file = new File(file6);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error");
            }
        }
        else if(iteration == 7)
        {

            file = new File(file7);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error");
            }
        }
        else if(iteration == 8)
        {

            file = new File(file8);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error");
            }
        }
        else if(iteration == 9)
        {

            file = new File(file9);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error");
            }
        }
        else //if(iteration == 10)
        {

            file = new File(file10);

            try{
                temp = StudentsParser.parse(file);
            }
            catch(IOException e)
            {
                System.out.println("Error");
            }
        }


        return temp;

    }



    void show(List<Student> students)
    {
        for(int i = 0; i<students.size(); i++)
        {
            Student s = students.get(i);
            System.out.println(s.getMark()+" "+s.getFirstName()+" "+s.getLastName()+" "+s.getAge());
        }
    }

    double ExtractMark(ExtremumMode mode)
    {
        double max =0;
        Student studentTemp= null;
        double min = 6.0;

        if(mode == ExtremumMode.MAX)
        {
            for(Student s : studentsSet){
                if(s.getMark()>max)
                    max = s.getMark();
            }


            return max;
        }
        else if(mode == ExtremumMode.MIN)
        {

            for(Student s : studentsSet){
                if(s.getMark()<min)
                    min = s.getMark();
            }


            return min;
        }
        return 0;
    }

    int ExtractAge(ExtremumMode mode)
    {
        int max =0;
        int min = 100;

        if(mode == ExtremumMode.MAX)
        {

            for(Student s : studentsSet){
                if(s.getAge()>max)
                    max = s.getAge();
            }


            return max;
        }
        if(mode == ExtremumMode.MIN)
        {

            for(Student s : studentsSet){
                if(s.getAge()<min)
                    min = s.getAge();
            }


            return min;
        }
        return 0;
    }

    List<Student> extractStudents(OrderMode mode)
    {
        List<Student> tempList= new ArrayList<Student>();

        for(Student s : studentsSet)
            tempList.add(s);


        System.out.print("ORDERED by ");


        if(mode == OrderMode.LAST_NAME)
        {
            System.out.println("Last Name");
            tempList.sort(new LastNameComparatorAscending());
            return tempList;
        }
        else if(mode == OrderMode.AGE)
        {	System.out.println("Age");
            tempList.sort(new AgeComparatorAscending());
            return tempList;
        }
        else if(mode == OrderMode.MARK)
        {	System.out.println("Mark");
            tempList.sort(new MarkComparatorAscending());
            return tempList;
        }
        return null;
    }
}
