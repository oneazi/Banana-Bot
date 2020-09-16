// this file allows the user to play the oddeven game which requires
// the user to enter either odd/even and an integer to be added
package omair.Banana.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import omair.Banana.Main;

import java.util.Random;


public class OddEven extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        //checks if the user wants to play rock paper scissors
        if (args[0].equalsIgnoreCase(Main.prefix + "oddeven"))
        {
            // if only one arg given
            if(args.length < 2)
            {
                EmbedBuilder syntaxError = new EmbedBuilder();
                syntaxError.setColor(0xFF4D4D);
                syntaxError.setTitle("🚫 Missing Arguments");
                syntaxError.setDescription("The `type` and `integer` arguments are required." +
                        "\n`type` should be `odd` or `even`\n\nUsage: `!oddeven <type> <integer>`");
                event.getChannel().sendMessage(syntaxError.build()).queue();
                syntaxError.clear();
            }

            // if only two args given
            else if (args.length < 3)
            {
                EmbedBuilder syntaxError = new EmbedBuilder();
                syntaxError.setColor(0xFF4D4D);
                syntaxError.setTitle("🚫 Missing Argument");
                syntaxError.setDescription("The `integer` argument is required.\n\nUsage: `!oddeven <type> <integer>`");
                event.getChannel().sendMessage(syntaxError.build()).queue();
                syntaxError.clear();
            }

            else
            {
                String choice = args[1];
                int num = Integer.parseInt(args[2]);
                int compNum = randInRange(0,1000);
                int sum = num + compNum;
                boolean isEven = isEven(sum);
                boolean userChoice;
                if (choice.equalsIgnoreCase("even") || choice.equalsIgnoreCase("odd"))
                {
                    userChoice = (choice.equalsIgnoreCase("even"));
                    String value;
                    if (isEven)
                        value = "Even";
                    else
                        value = "Odd";

                    EmbedBuilder oddEven = new EmbedBuilder();
                    oddEven.setTitle("Odd Even");
                    if (isEven == userChoice)
                    {
                        oddEven.setColor(0x00EA47);
                        oddEven.setDescription(num + " + " + compNum + " = " + sum + "\n"
                                + sum + " is " + value + "\n\nYou won!");
                    }
                    else
                    {
                        oddEven.setColor(0xFF4D4D);
                        oddEven.setDescription(num + " + " + compNum + " = " + sum + "\n"
                                + sum + " is " + value + "\n\nYou Lost!");
                    }
                    event.getChannel().sendMessage(oddEven.build()).queue();
                    oddEven.clear();
                }
            }
        }
    }

    // determines if a number is even or odd
    private boolean isEven(int num)
    {
        return  (num % 2 == 0);
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
