// this class allows the user to play rock paper scissors against an AI
package omair.Banana.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import omair.Banana.Main;

import java.util.Random;

public class RPS extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event)
    {
        // prevent message loops
        if (event.getAuthor().isBot()) return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        //checks if the user wants to play rock paper scissors
        if (args[0].equalsIgnoreCase(Main.prefix + "rps"))
        {
            if (args.length < 2)
            {
                EmbedBuilder syntaxError = new EmbedBuilder();
                syntaxError.setColor(0xFF4D4D);
                syntaxError.setTitle("🚫 Missing Argument");
                syntaxError.setDescription("The `type` argument is required.\n\nUsage: `!rps <type>`");
                event.getChannel().sendMessageEmbeds(syntaxError.build()).queue();
                syntaxError.clear();
            }
            else
            {
                String choice  = args[1].toLowerCase();
                String name = event.getMessage().getAuthor().getName();
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Rock, Paper, Scissors | " + name);
                playRPS(choice, name, event, builder);
            }
        }
    }

    // this method implements the rock paper scissors game
    private void playRPS(String choice, String name, MessageReceivedEvent event, EmbedBuilder builder)
    {
        int playerChoice = 0;
        int computerChoice = 0;

        if (choice.equals("rock"))
            playerChoice = 1;
        else if (choice.equals("paper"))
            playerChoice = 2;
        else if (choice.equals("scissors"))
            playerChoice = 3;

        computerChoice = randInRange(1,3);

        if (playerChoice == 1)
        {
            if(computerChoice == 1)
            {
                builder.setDescription(name + " threw " + choice + " 🗻\nBanana threw rock 🗻\nIt's a tie!");
                builder.setColor(0x282828);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                builder.clear();
            }
            else if(computerChoice == 2)
            {
                builder.setDescription(name + " threw " + choice + " 🗻\nBanana threw paper 📜\nBanana wins 😜");
                builder.setColor(0xFF4D4D);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                builder.clear();
            }
            else if(computerChoice == 3)
            {
                builder.setDescription(name + " threw " + choice + " 🗻\nBanana threw scissors ✂\nYou win!");
                builder.setColor(0x00EA47);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                builder.clear();
            }
        }
        else if (playerChoice == 2)
        {
            if(computerChoice == 1)
            {
                builder.setDescription(name + " threw " + choice + " 📜\nBanana threw rock 🗻\nYou win!");
                builder.setColor(0x00EA47);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                builder.clear();
            }
            else if(computerChoice == 2)
            {
                builder.setDescription(name + " threw " + choice + " 📜\nBanana threw paper 📜\nIt's a tie!");
                builder.setColor(0x282828);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                builder.clear();
            }
            else if(computerChoice == 3)
            {
                builder.setDescription(name + " threw " + choice + " 📜\nBanana threw scissors ✂\nBanana wins 😜");
                builder.setColor(0xFF4D4D);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                builder.clear();
            }
        }
        else if (playerChoice == 3)
        {
            if(computerChoice == 1)
            {
                builder.setDescription(name + " threw " + choice + " ✂\nBanana threw rock 🗻\nBanana wins 😜");
                builder.setColor(0xFF4D4D);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                builder.clear();
            }
            else if(computerChoice == 2)
            {
                builder.setDescription(name + " threw " + choice + " ✂\nBanana threw paper 📜\nYou win!");
                builder.setColor(0x00EA47);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                builder.clear();
            }
            else if(computerChoice == 3)
            {
                builder.setDescription(name + " threw " + choice + " ✂\nBanana threw scissors ✂\nIt's a tie!");
                builder.setColor(0x282828);
                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                builder.clear();
            }
        }
    }

    private int randInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
