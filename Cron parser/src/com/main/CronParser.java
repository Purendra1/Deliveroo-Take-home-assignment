import java.util.*;
/**
 * @author - Purendra Srivastava
 */
public class CronParser {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java CronParser '<cron_expression>'");
            System.exit(1);
        }
        String cronString = args[0];
        parseCron(cronString);
    }

    private static void parseCron(String cronString) {
        String[] fields = cronString.split(" ");
        if (fields.length != 6) {
            System.err.println("Invalid cron string.");
            return;
        }
        System.out.println("minute        "+String.join(" ", expandField(fields[0], 0, 59).stream().map((i)->i.toString()).toList()));
        System.out.println("hour          "+String.join(" ", expandField(fields[1], 0, 23).stream().map((i)->i.toString()).toList()));
        System.out.println("day of month  "+String.join(" ", expandField(fields[2], 1, 31).stream().map((i)->i.toString()).toList()));
        System.out.println("month         "+String.join(" ", expandField(fields[3], 1, 12).stream().map((i)->i.toString()).toList()));
        System.out.println("day of week   "+String.join(" ", expandField(fields[4], 0,  6).stream().map((i)->i.toString()).toList()));
        System.out.println("command       "+fields[5]);
    }

    public static List<Integer> expandField(String field, int min, int max) {
        List<Integer> result = new ArrayList<>();

        if (field.equals("*")) {
            for(int x=min;x<=max;x++)
            if(inRange(min,max,x))result.add(x);
        } else if (field.contains(",")) {
            String[] parts = field.split(",");
            for(String x:parts) {
                int val = Integer.parseInt(x);
                if (inRange(min,max,val)) result.add(val);
            }
        } else if (field.contains("-")) {
            String parts[] = field.split("-");
            int begin = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            for(int x=begin;x<=end;x++) {
                if (inRange(min,max,x)) result.add(x);
            }
        } else if (field.contains("/")) {
            String[] parts = field.split("/");
            int step = Integer.parseInt(parts[1]);
            for (int x=min;x<=max;x+=step) {
                result.add(x);
            }
        } else {
            result.add(Integer.parseInt(field));
        }
        
        return result;
    }

    private static boolean inRange(int min, int max, int val) {
        return val>=min && val<=max;
    }
}