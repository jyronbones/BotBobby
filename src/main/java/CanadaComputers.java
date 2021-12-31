import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

/**
 * This class contains the methods to scrape 30x series GPUs from Canada Computers website and relay to discord
 */
public class CanadaComputers {
    /**
     * This method scrapes Canada Computers 30x series graphics cards from website and displays via an embedded text
     * @param event is the passed event as a command
     * @throws IOException is the exception class that is thrown if IO error
     */
    public void canadaComputersScrape(GuildMessageReceivedEvent event) throws IOException {

        int pageCount = 3;
        while(pageCount <= 3) //6 pages
        {

            String ccUrl = "https://www.canadacomputers.com/index.php?ajax=false&cPath=43&sf=:3_3,3_4,3_5,3_6,3_7,3_8,3_9&mfr=&pr="
                    + pageCount;

            pageCount++;

            Document doc = Jsoup.connect(ccUrl).timeout(6000).get();

            Elements body = doc.select("#product-list");
            for(Element t: body.select(".col-xl-3")) // loops through the element which holds the products on the page
            {
                Elements title = t.select(".productTemplate_title");
                String image = t.select(".pq-img-manu_logo").attr("src");
                Elements price = t.select(".pq-hdr-product_price");
                Elements inStock = t.select(".btn");
                String link = t.select("a").attr("href");


                if(inStock.text().equals("Add to Cart")) // if item available for purchase
                {

                    EmbedBuilder info = new EmbedBuilder();
                    info.setTitle(title.text());
                    info.setDescription("⬇Canada Computers⬇");
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
