import java.io.IOException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * This class contains the methods with commands to listen for user input discord
 */
public class Commands extends ListenerAdapter {

	/**
	 * This method executes scraping of sites upon received command and provided embedded end of search message
	 * @param event is the passed event of a user command
	 */
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Bot.PREFIX + "gpu")) {

			try {
			NewEgg ne = new NewEgg();
			ne.newEggScrape(event);

            CanadaComputers cc = new CanadaComputers();
            cc.canadaComputersScrape(event);

            MemoryExpress me = new MemoryExpress();
            me.memoryExpressScrape(event);

            BestBuy bb = new BestBuy();
            bb.bestBuyScrape(event);

            } catch (IOException e) {
				System.err.println("Error running scrape methods");
				e.printStackTrace();
			}

			if(args[0].equalsIgnoreCase(Bot.PREFIX + "shiba")) {
				EmbedBuilder info2 = new EmbedBuilder();
				info2.setTitle("Cryptocurrency values");
			}

			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("(☞ﾟヮﾟ)☞🕹30x Graphics Cards in Store🕹☜(ﾟヮﾟ☜)");
			info.setImage("https://cdn.mos.cms.futurecdn.net/4SQBx6PXfEMhitPwz32eFM-970-80.jpg.webp");
			info.setDescription("⬇Graphics card inventories⬇");
			info.addField("End of stock", "Check back later", false);
			info.setColor(0xff1f1f);
			info.setFooter("＼(ﾟｰﾟ＼)", event.getMember().getUser().getAvatarUrl());

			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
			info.clear();
		}
	}
}
