import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class Bot implements EventListener{
	
	final public static String TOKEN = "TOKEN";
	public static String prefix = "!";
	
	public static void main(String[] args) throws LoginException {
		
		JDA api = JDABuilder.createDefault(TOKEN).addEventListeners(new Bot()).build();
		api.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
		
	
		api.addEventListener(new Commands());

	}

	public void onEvent(GenericEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}
