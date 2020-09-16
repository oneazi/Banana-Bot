// this class allows the user to check their ping to the server
package omair.Banana.Commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import omair.Banana.Main;

public class Ping extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        //checks if the user wants to check their ping
        if (args[0].equalsIgnoreCase(Main.prefix + "ping"))
        {
            // get the users ping and convert it to milliseconds
            int ping = Math.round(event.getJDA().getGatewayPing());
            event.getChannel().sendMessage("Pong! " + ping + "ms.").queue();
        }
    }
}
