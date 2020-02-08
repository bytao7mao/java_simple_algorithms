//java 10
s->{String r="",a="aeiouabcdfghjklmnpqrstvwxyzb";char c=s.charAt(0);if(a.indexOf(c)>5)s=s.substring(1)+c+"ux";for(char d:s.toCharArray())r+=a.charAt(a.indexOf(d)+1);return r;}
