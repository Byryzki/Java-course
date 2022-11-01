public class Dates {
    
    public static class DateDiff{

        private String start;
        private String end;
        private int diff;
        private String sDay;
        private String eDay;
        
        
        
        private DateDiff(String start, String end, int diff, String sday, String eday)throws DateTimeParseException {
          
            this.start = start;
            this.end = end;
            this.diff = diff;
            this.sDay = sday;  
            this.eDay = eday;
        }
        

        public String getStart(){
            return this.start;
        } 
        public String getEnd(){
            return this.end;
        }
        int getDiff(){
            return this.diff;
        }
        
        @Override
        public String toString(){
            
            String[]start1 = this.start.split("\\-");
            
            String sdate = String.format("%02d.%02d.%s",Integer.parseInt(start1[2]),Integer.parseInt(start1[1]),start1[0]);
            
            String[]end1 = this.end.split("\\-");
            
            String edate = String.format("%02d.%02d.%s",Integer.parseInt(end1[2]),Integer.parseInt(end1[1]),end1[0]);
            
            String date = String.format("%s %s --> %s %s: %d days", this.sDay, sdate,this.eDay,edate,this.diff);
            return date;
        }
        
    }
    
    public static  DateDiff[] dateDiffs(String ...dateStrs){
        ArrayList<String> dates = new ArrayList<String>();
        for(String s : dateStrs){
            String date = s;
            if(s.contains(".")){
                String[]temp = s.split("\\.");
                int day = Integer.parseInt(temp[0]);
                int month = Integer.parseInt(temp[1]);
                date = String.format("%s-%02d-%02d",temp[2],month,day);
            }
            try {
                LocalDate.parse(date);
                dates.add(date);
            }
            catch(DateTimeParseException  e) {
                System.out.printf("The date \"%s\" is illegal!%n", s);
            }
        }
        Comparator<String> comparator = (final String d1, final String d2) -> {
            if( !d1.split("\\-")[0].equals(d2.split("\\-")[0])){
                return (Integer.parseInt(d1.split("\\-")[0]) < Integer.parseInt(d2.split("\\-")[0])) ? -1:1 ;
            }else{
                if( !d1.split("\\-")[1].equals(d2.split("\\-")[1])){
                    return (Integer.parseInt(d1.split("\\-")[1]) < Integer.parseInt(d2.split("\\-")[1])) ? -1:1 ;
                }else{
                    return (Integer.parseInt(d1.split("\\-")[2]) < Integer.parseInt(d2.split("\\-")[2])) ? -1:1 ;
                } 
            }
        };
        Collections.sort(dates, comparator);
        DateDiff[] diffs = new DateDiff[dates.size()-1];

        
        for(int i = 1; i < dates.size();i++){
            
            
            DateTimeFormatter formattr = DateTimeFormatter.ofPattern("EEEE", Locale.US);
            
            
            int period = (int)ChronoUnit.DAYS.between(LocalDate.parse(dates.get(i-1)), LocalDate.parse(dates.get(i)));
            DateDiff temp = new DateDiff(dates.get(i-1),dates.get(i),period,formattr.format(LocalDate.parse(dates.get(i-1))),formattr.format(LocalDate.parse(dates.get(i))));
            diffs[i-1] = temp;
        }
        
        return diffs;
    }
    

  
}