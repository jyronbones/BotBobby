import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CanadaComputers {

    public void canadaComputers() throws IOException {

        int pageCount = 1;
        while(pageCount <= 2) //6 pages
        {
            //30x url: https://www.canadacomputers.com/index.php?cPath=43&sf=:3_3,3_5,3_7,3_8,3_9&mfr=&pr=&ajax=false&page=
            String ccUrl = "https://www.canadacomputers.com/index.php?cPath=43&sf=:3_27,3_41&mfr=&pr="
                    + pageCount;

            pageCount++;

            Document doc = Jsoup.connect(ccUrl).timeout(6000).get();

            Elements body = doc.select("#product-list");
            for(Element t: body.select(".col-xl-3"))
            {
                Elements title = t.select(".productTemplate_title");
                String image = t.select(".pq-img-manu_logo").attr("src");
                Elements price = t.select(".pq-hdr-product_price");
                Elements inStock = t.select(".btn");
                String link = t.select("a").attr("href");


                if(inStock.text().equals("Add to Cart"))
                {
                    System.out.println("\n" + title.text());
                    System.out.println(image);
                    System.out.println(price.text());
                    System.out.println(link);
                    System.out.println(inStock.text().replace("Add to Cart", "In Stock"));


                }
            }
        }

    }
}