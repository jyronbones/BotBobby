import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
/**
 * This class contains the methods to scrape 30x series GPUs from Memory Express website and relay to discord
 */
public class MemoryExpress {
    /**
     * This method scrapes Memory Express 30x series graphics cards from website and displays via an embedded text
     * @param event is the passed event as a command
     * @throws IOException is the exception class that is thrown if IO error
     */
    public void memoryExpressScrape(GuildMessageReceivedEvent event) throws IOException {

        int pageCount = 1;
        while(pageCount <= 3)
        {
            String meUrl = "https://www.memoryexpress.com/Category/VideoCards?FilterID=a3bbaaca-8a1f-2941-1da3-fb220ebcaf1b&Page=" + pageCount;

            pageCount++;

            Document doc = Jsoup.connect(meUrl).timeout(6000).get();

            Elements body = doc.select(".c-shca-container");

            for(Element t: body.select(".c-shca-icon-item")) // loops through the element which holds the products on the page
            {
                Elements title = t.select(".c-shca-icon-item__body-name");
                String image = t.select(".c-shca-icon-item__body-image img").attr("src");
                Elements price = t.select(".c-shca-icon-item__summary-list");
                String link = t.select("a").attr("href");
                Elements inStock = t.select(".c-shca-icon-item__body-inventory");

                if(!inStock.text().equals("Out of Stock")) // if item available for purchase
                {
                        EmbedBuilder info = new EmbedBuilder();
                        info.setTitle(title.text());
                        info.setDescription("⬇Memory Express⬇");
                        info.setImage(image);
                        info.addField("Price", price.text().replace("+", ""), false);
                        info.addField("Website","https://www.memoryexpress.com" + link, false);
                        info.addField("Availability","In Stock", false);
                        info.setThumbnail("https://d2t1xqejof9utc.cloudfront.net/screenshots/pics/2a34a345be3bf4b30229de096bd0b06e/large.jpg");

                        event.getChannel().sendTyping().queue();
                        event.getChannel().sendMessage(info.build()).queue();

                }
            }
        }
    }
}
