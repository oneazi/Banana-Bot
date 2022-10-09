// this file implements the roast command which allows one user
// to roast another based on a set of pre-written roasts which are randomly selected
package omair.Banana.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import omair.Banana.Main;

import java.util.Random;


public class Roast extends ListenerAdapter {
    private String[] roast = {"Your forehead is bigger than your future.",
            "My phone battery lasts longer than your relationships.",
            "It’s a shame you can’t Photoshop your personality.",
            "Maybe you should eat make-up so you’ll be pretty on the inside too.",
            "You should use a glue stick instead of chapstick.",
            "Mirrors can't talk. Lucky for you they can't laugh either.",
            "If laughter is the best medicine, your face must be curing the world.",
            "I’ll never forget the first time we met. But I’ll keep trying",
            "Your face makes onions cry.",
            "It’s impossible to underestimate you.",
            "Why is it acceptable for you to be an idiot but not for me to point it out?",
            "Everyone brings happiness to a room. I do when I enter, you do when you leave.",};

    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        //checks if the user wants to roast another member
        if (args[0].equalsIgnoreCase(Main.prefix + "roast"))
        {
            if (args.length < 2)
            {
                // if missing an argument
                EmbedBuilder syntaxError = new EmbedBuilder();
                syntaxError.setColor(0xFF4D4D);
                syntaxError.setTitle("🚫 Missing Argument");
                syntaxError.setDescription("The `member` argument is required.\n\nUsage: `!roast @<member>`");
                event.getChannel().sendMessageEmbeds(syntaxError.build()).queue();
                syntaxError.clear();
            }
            else
            {
                String name = args[1];
                try {
                    //Member member = event.getGuild().getMemberById(name.replace("<@!", "").replace(">", ""));
                    event.getChannel().sendMessage(name + ", " + this.roast[randInRange(0, this.roast.length - 1)]).queue();
                }
                catch (NumberFormatException e)
                {
                    EmbedBuilder syntaxError = new EmbedBuilder();
                    syntaxError.setColor(0xFF4D4D);
                    syntaxError.setTitle("🚫 Missing Argument");
                    syntaxError.setDescription("Please refer to the `member` using their `@`\n\nUsage: `!roast @<member>`");
                    event.getChannel().sendMessageEmbeds(syntaxError.build()).queue();
                    syntaxError.clear();
                }
            }
        }
    }

    // generates a random number in the range
    private int randInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
