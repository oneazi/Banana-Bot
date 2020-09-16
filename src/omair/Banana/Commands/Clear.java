// this class allows the user to clear between 1-100 messages from a discord chat
package omair.Banana.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import omair.Banana.Main;

import java.util.List;

public class Clear extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        // check for clear command
        if (args[0].equalsIgnoreCase(Main.prefix + "clear"))
        {
            if (args.length < 2)
            {
                EmbedBuilder syntaxError = new EmbedBuilder();
                syntaxError.setColor(0xFF4D4D);
                syntaxError.setTitle("🚫 Missing Argument");
                syntaxError.setDescription("The `amount` argument is required.\n\nUsage: `!clear <amount>`");
                event.getChannel().sendMessage(syntaxError.build()).queue();
                syntaxError.clear();
            }
            else
            {
                try
                {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();
                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(0x00EA47);
                    success.setTitle("✅ Messages deleted");
                    success.setDescription("Successfully deleted " + args[1] + " messages!");
                    event.getChannel().sendMessage(success.build()).queue();
                    success.clear();
                }
                catch(IllegalArgumentException e)
                {
                    if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval"))
                    {
                        // messages selected not between 1-100
                        EmbedBuilder wrongLength = new EmbedBuilder();
                        wrongLength.setColor(0xFF4D4D);
                        wrongLength.setTitle("🚫 Too many messages selected");
                        wrongLength.setDescription("Only 1-100 messages can be deleted at once.");
                        event.getChannel().sendMessage(wrongLength.build()).queue();
                        wrongLength.clear();
                    }
                    else
                    {
                        // messages too old
                        EmbedBuilder tooOld = new EmbedBuilder();
                        tooOld.setColor(0xFF4D4D);
                        tooOld.setTitle("🚫 Messages Selected too old");
                        tooOld.setDescription("Messages more than 2 weeks old cannot be deleted in bulk.");
                        event.getChannel().sendMessage(tooOld.build()).queue();
                        tooOld.clear();
                    }
                }
            }
        }
    }
}
