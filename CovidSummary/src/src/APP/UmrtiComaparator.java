package src.APP;

import java.util.*;

class UmrtiComparator implements Comparator<dataVUT> {

    
    /** 
     * Metoda implemetující Comparator
     * @param o1
     * @param o2
     * @return int
     */
    @Override
    public int compare(dataVUT o1, dataVUT o2) {
        if (o1.umrti == o2.umrti)
            return 0;
        else if (o1.umrti > o2.umrti)
            return 1;
        else
            return -1;
    }
}