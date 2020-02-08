public static int hoursAgo(String datetime) {

    Calendar date = Calendar.getInstance();
    date.setTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH).parse(datetime)); // Parse into Date object
    Calendar now = Calendar.getInstance(); // Get time now
    long differenceInMillis = now.getTimeInMillis() - date.getTimeInMillis();
    long differenceInHours = (differenceInMillis) / 1000L / 60L / 60L; // Divide by millis/sec, secs/min, mins/hr
    return (int)differenceInHours;

}
