import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
/**
 * This class contains the methods to scrape 30x series GPUs from NewEgg website and relay to discord
 */
public class NewEgg {
    /**
     * This method scrapes NewEgg 30x series graphics cards from website and displays via an embedded text
     * @param event is the passed event as a command
     * @throws IOException is the exception class that is thrown if IO error
     */
    public void newEggScrape(GuildMessageReceivedEvent event) throws IOException {
   
        int pageCount = 1;
        while(pageCount <= 6)
        {
            String newEggUrl = "https://www.newegg.ca/p/pl?N=100007708%20601357282&page=" + pageCount;

            pageCount++;

            Document doc = Jsoup.connect(newEggUrl).timeout(6000).get();

            Elements body = doc.select(".item-cell");

            for(Element t: body.select(".item-container")) // loops through the element which holds the products on the page
            {
                Elements title = t.select(".item-title");
                String image = t.select(".item-container img").attr("src");
                Elements price = t.select(".price-current");
                Elements inStock = t.select(".item-button-area");
                String link = t.select("a").attr("href");


                if(inStock.text().equals("Add to cart")) // if item available for purchase
                {
                    EmbedBuilder info = new EmbedBuilder();
                    info.setTitle(title.text());
                    info.setDescription("⬇New Egg⬇");
                    info.setImage(image);
                    info.addField("Price", price.text().replace("+", ""), false);
                    info.addField("Website", link, false);
                    info.addField("Availability","In Stock", false);
                    info.setThumbnail("https://d2t1xqejof9utc.cloudfront.net/screenshots/pics/2a34a345be3bf4b30229de096bd0b06e/large.jpg");

                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage(info.build()).queue();
                }
            }
        }
    }
}
