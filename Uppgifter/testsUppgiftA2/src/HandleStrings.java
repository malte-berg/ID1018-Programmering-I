// HandleStrings.java

/****************************************************************

HandleStrings illustrates the handling of character strings.

Author: Fadil Galjic

****************************************************************/

import java.util.Arrays;
import static java.lang.System.out;

class HandleStrings
{
    public static void main (String[] args)
    {
        String s = "merciful | gracious, kindly, compassionate, forgiving, charitable";
        out.println(s + "\n");

	    int index = s.indexOf('|');
	    out.println(index);
	    String s1 = s.substring(0, index);
	    String s2 = s.substring(index + 1);
	    String s3 = s.substring(index + 1, s.length());
	    String s4 = s.substring(index + 1, s.length() - 1);
	    String[] str1 = {s1, s2, s3, s4};
	    out.println(Arrays.toString(str1));
	    int lastindex = s.lastIndexOf(',');
	    String s5 = s.substring(0, lastindex);
	    out.println(s5);
	    out.println();

	    String[] str2 = s.split("[|,]");
        out.println(Arrays.toString(str2));
        for (int i = 0; i < str2.length; i++)
            str2[i] = str2[i].trim();
        out.println(Arrays.toString(str2) + "----");
        out.println(s2);
        String[] str3 = s2.split(", ");
        out.println(Arrays.toString(str3));
        out.println();

        String s6 = "a + b = c";
        String s7 = "A + B = C";
        String s8 = "B + A = C";
        out.println(s6.equals(s7));
        out.println(s6.equalsIgnoreCase(s7));
        out.println(s7.compareTo(s8) < 0);
        out.println(s8.compareTo(s7) < 0);
        out.println(s6.compareToIgnoreCase(s7) == 0);
        if ("bac".compareTo("abc") < 0)
            out.println("bac" + ", " + "abc");
        else
            out.println("abc" + ", " + "bac");
        out.println();

        String s9 = "a | ";
        String s10 = "b. c. d. e.";
        String s11 = s9 + s10;
        out.println(s11);
        String s12 = s11.substring(0, s11.lastIndexOf('.'));
        out.println(s12);
    }
}