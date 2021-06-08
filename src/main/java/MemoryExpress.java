import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MemoryExpress {

    public void memoryExpress() throws IOException {

        int pageCount = 1;
        while(pageCount <= 1) //38 pages
        {
            //30x series url: https://www.memoryexpress.com/Category/VideoCards?FilterID=06feb6a8-fb17-71c3-bd4b-3f3b39562041
            String meUrl = "https://www.memoryexpress.com/Category/VideoCards?FilterID=c03cd432-a0a4-1828-9edb-830342fb1ad4&Page=" + pageCount;

            pageCount++;

            Document doc = Jsoup.connect(meUrl).timeout(6000).get();

            Elements body = doc.select(".c-shca-container");

            for(Element t: body.select(".c-shca-icon-item"))
            {
                Elements title = t.select(".c-shca-icon-item__body-name");
                String image = t.select(".c-shca-icon-item__body-image img").attr("src");
                Elements price = t.select(".c-shca-icon-item__summary-list");
                String link = t.select("a").attr("href");
                Elements inStock = t.select(".c-shca-icon-item__body-inventory");

                if(!inStock.text().equals("Out of Stock"))
                {
                    System.out.println("\n" + title.text());
                    System.out.println(image);
                    System.out.println(price.text().replace("+", ""));
                    System.out.println("https://www.memoryexpress.com" + link);
                    System.out.println("In Stock");

                }
            }
        }

    }
}