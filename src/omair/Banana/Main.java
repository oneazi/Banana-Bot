// Omair Neazi
// This is the code for the Banana bot on discord which plays multiple games and provides
// functionality for the guild.
package omair.Banana;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import omair.Banana.Commands.*;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Main {
    public static String prefix = "!";

    // main
    public static void main(String[] args) throws LoginException
    {
        EnumSet<GatewayIntent> intents = EnumSet.of(
                // Enables MessageReceivedEvent for guild (also known as servers)
                GatewayIntent.GUILD_MESSAGES,
                // Enables the event for private channels (also known as direct messages)
                GatewayIntent.DIRECT_MESSAGES,
                // Enables access to message.getContentRaw()
                GatewayIntent.MESSAGE_CONTENT,
                // Enables MessageReactionAddEvent for guild
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                // Enables MessageReactionAddEvent for private channels
                GatewayIntent.DIRECT_MESSAGE_REACTIONS
        );

        JDA jda = JDABuilder.createLight("", intents).build();	// place private bot key in createDefault() as a string parameter
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
