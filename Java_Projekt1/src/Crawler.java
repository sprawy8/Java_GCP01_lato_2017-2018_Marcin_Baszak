import java.util.ArrayList;
import java.util.List;

public class Crawler {

    public List<Student> student;

    List<Student> lista = new ArrayList<Student>();


    double extractMark( ExtremumMode mode ) {
    double max=0;
    double min=10;

    if (mode==ExtremumMode.MAX){
    for(Student El : lista){
        if (El.getMark()>max)
            max=El.getMark();
    }
    return max;
    }

    else if (mode==ExtremumMode.MIN){
        for(Student El : lista){
            if (El.getMark()<min)
                min=El.getMark();
        }
        return min;
    }

    else { System.out.print("ZLE"); return 0; }

    }
    int extractAge( ExtremumMode mode ){

    return 0;
    }



}
