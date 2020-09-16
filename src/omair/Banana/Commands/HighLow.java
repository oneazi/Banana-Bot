// this file implements the highlow card game in which a car is drawn and then
// the user must guess if the next car will be higher or lower.
package omair.Banana.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import omair.Banana.Main;
import omair.Banana.Objects.Card;

import java.util.Random;

public class HighLow extends ListenerAdapter {

    private Card firstCard;
    private Card secondCard;
    private String user;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        //checks if the user wants to play the High Card Low Card game
        if (args[0].equalsIgnoreCase(Main.prefix + "hilo"))
        {
            this.user = event.getMember().getUser().getName();
            int rank = randInRange(0,12);
            int suit = randInRange(0,3);
            int secondRank = rank;
            int secondSuit = suit;
            this.firstCard = new Card(rank, suit);
            while(suit == secondSuit && rank == secondRank)
            {
                secondRank = randInRange(0,12);
                secondSuit = randInRange(0,3);
            }
            this.secondCard = new Card(secondRank, secondSuit);

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("High-Low | " + this.user);
            builder.setDescription("press ☝ to guess higher, press 👇 to guess lower");
            builder.addField("First Card", this.firstCard.toString(),false);
            builder.setColor(0x282828);
            event.getChannel().sendMessage(builder.build()).queue(message -> {
                message.addReaction("☝").queue();
                message.addReaction("👇").queue();
            });
            builder.clear();
        }
    }

    // implements the second half of the game after the user chooses a reaction
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event)
    {
        EmbedBuilder builder = new EmbedBuilder();

        if(event.getReactionEmote().getName().equals("☝") &&
            !event.getMember().getUser().equals(event.getJDA().getSelfUser()))
        {
            builder.setTitle("High-Low | " + this.user);
            builder.addField("First Card", this.firstCard.toString(),true);
            builder.addField("Second Card", this.secondCard.toString(),true);

            if (event.getMember().getUser().getName().equals(this.user))
            {
                // compare the cards
                if (this.firstCard.getRank() < this.secondCard.getRank())
                {
                    builder.setColor(0x00EA47);
                    builder.setDescription("You Won!");
                }
                else if (this.firstCard.getRank() > this.secondCard.getRank())
                {
                    builder.setColor(0xFF4D4D);
                    builder.setDescription("You Lost!");
                }
                else
                {
                    builder.setColor(0x282828);
                    builder.setDescription("You broke even");
                }
                event.getChannel().sendMessage(builder.build()).queue();
                builder.clear();
            }
            else
            {
                event.getReaction().removeReaction().queue();
            }
        }

        // check if finger pointing down
        else if(event.getReactionEmote().getName().equals("👇") &&
                !event.getMember().getUser().equals(event.getJDA().getSelfUser()))
        {
            builder.setTitle("High-Low | " + this.user);
            builder.addField("First Card", this.firstCard.toString(),true);
            builder.addField("Second Card", this.secondCard.toString(),true);
            if (event.getMember().getUser().getName().equals(this.user))
            {
                // compare the cards
                if (this.firstCard.getRank() < this.secondCard.getRank())
                {
                    builder.setColor(0xFF4D4D);
                    builder.setDescription("You Lost!");
                }
                else if (this.firstCard.getRank() > this.secondCard.getRank())
                {
                    builder.setColor(0x00EA47);
                    builder.setDescription("You Won!");
                }
                else
                {
                    builder.setColor(0x282828);
                    builder.setDescription("You broke even");
                }
                event.getChannel().sendMessage(builder.build()).queue();
                builder.clear();
            }
            else
            {
                event.getReaction().removeReaction().queue();
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
