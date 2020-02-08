int centuryFromYear(int year) {
        //returns the year without the last 2 zeros, which means the century
        if(year % 100 == 0){
            return year / 100;
        }
        //else returns century plus one 
        return (year / 100) + 1;
    }
