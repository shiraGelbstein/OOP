import java.lang.reflect.Array;
import java.util.Scanner;


/**
 * A class that uses the ChatterBot class for having an endless conversation
 * between two bots.
 */
public class Chat {

        public static void main(String[] args) {
                String[] stringArray1 = new String[]{"what ", "should i say "};
                String[] stringArray3 = new String[]{"say "+
                        ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE +
                        " ok ill say " +ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE+":"+
                        ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE};
                ChatterBot chatter1= new ChatterBot("Shira", stringArray3,stringArray1);
                String[] stringArray2 = new String[]{"whaaat " +
                        ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST , "say say "};
                String[] stringArray4 = new String[]{"haha halourios, but ill say it:"+
                        ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE};
                ChatterBot chatter2= new ChatterBot("Tali",stringArray4,stringArray2);
                ChatterBot[] arrofchatter = {chatter1,chatter2};
                String statement = "say boo";
                while(true){
                     for(ChatterBot bot : arrofchatter)
                     {
                             statement = bot.replyTo(statement);
                             System.out.print(bot.getName()+": ");
                             System.out.print(statement);
                             System.out.print("\n");
                     }
                }

        }
}
