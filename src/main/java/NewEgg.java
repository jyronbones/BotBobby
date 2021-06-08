import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NewEgg {

    public void newEgg() throws IOException {
   
        int pageCount = 1;
        while(pageCount <= 1) //38 pages
        {
            //30x series url: https://www.newegg.ca/p/pl?N=100007708%20601357282
            String newEggUrl = "https://www.newegg.ca/Desktop-Graphics-Cards/SubCategory/ID-48/Page-" + pageCount;

            pageCount++;

            Document doc = Jsoup.connect(newEggUrl).timeout(6000).get();

            Elements body = doc.select(".item-cell");

            for(Element t: body.select(".item-container"))
            {
                Elements title = t.select(".item-title");
                String image = t.select(".item-container img").attr("src");
                Elements price = t.select(".price-current");
                Elements inStock = t.select(".item-button-area");
                String link = t.select("a").attr("href");


                if(inStock.text().equals("Add to cart"))
                {
                    System.out.println("\n" + title.text());
                    System.out.println(image);
                    System.out.println(price.text());
                    System.out.println(link);
                    System.out.println(inStock.text().replace("Add to cart", "In Stock"));

                }
            }
        }

    }
}