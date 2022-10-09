// this file displays an embed when the user types the !help command to
// display all the possible commands available to the user
package omair.Banana.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import omair.Banana.Main;


public class Help extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event)
    {
        // prevent message loops
        if (event.getAuthor().isBot()) return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");
        // displays an embed which has details on all the commands this bot follows
        if (args[0].equalsIgnoreCase(Main.prefix + "help"))
        {
            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("🍌 Commands");
            help.setColor(0xFAFF63);
            help.setDescription("This is a banana bot who likes playing Rock, Paper, Scissors if you dare to challenge it.");
            help.addField("🎯 Fun", "`!hilo`, `!oddeven`, `!roast`, `!rps`", false);
            help.addField("🔨 Utilities", "`!clear`, `!ping`", false);
            help.addField("ℹ Info", "`!help`", false);
            help.setFooter("Creator:\nOmair N");
            event.getChannel().sendMessageEmbeds(help.build()).queue();
            help.clear();
        }
    }
}
