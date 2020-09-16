// Omair Neazi
// This is the code for the Banana bot on discord which plays multiple games and provides
// functionality for the guild.
package omair.Banana;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import omair.Banana.Commands.*;

import javax.security.auth.login.LoginException;

public class Main {
    public static String prefix = "!";

    // main
    public static void main(String args[]) throws LoginException
    {
        JDA jda = JDABuilder.createDefault("Njk4MzIwOTA5NzIyOTc2MzU4.XpE0fg.YbVL-Om_UF0BWLL4rDSXl5CiYRw").build();	# place private bot key in string
        jda.getPresence().setActivity(Activity.playing("Currently peeling myself"));
        jda.getPresence().setStatus(OnlineStatus.ONLINE);

        // Commands list
        jda.addEventListener(new Help());
        jda.addEventListener(new Clear());
        jda.addEventListener(new RPS());
        jda.addEventListener(new OddEven());
        jda.addEventListener(new HighLow());
        jda.addEventListener(new Roast());
        jda.addEventListener(new Ping());
    }
}
