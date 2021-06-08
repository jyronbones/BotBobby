import java.io.IOException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Bot.prefix + "gpu")) {
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("(☞ﾟヮﾟ)☞🕹30x Graphics Cards in Store🕹☜(ﾟヮﾟ☜)");
			info.setDescription("⬇Graphics card inventories⬇");
            
			try {
			NewEgg ne = new NewEgg();
				ne.newEgg();

            CanadaComputers cc = new CanadaComputers();
            cc.canadaComputers();

            MemoryExpress me = new MemoryExpress();
            me.memoryExpress();
            
            Crypto crypt = new Crypto();
            crypt.cryptoCurrency();
            
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(args[0].equalsIgnoreCase(Bot.prefix + "shiba")) {
				EmbedBuilder info2 = new EmbedBuilder();
				info2.setTitle("Cryptocurrency values");
			}

			info.addField("End of stock", "Check back later", false);
			info.setColor(0xff1f1f);
			info.setFooter("＼(ﾟｰﾟ＼)", event.getMember().getUser().getAvatarUrl());
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
			info.clear();
		}
	}

}
