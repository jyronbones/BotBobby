import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crypto {

    public void cryptoCurrency() throws IOException {

            String cryptoUrl = "https://coinmarketcap.com/";


            Document doc = Jsoup.connect(cryptoUrl).timeout(6000).get();

            Elements body = doc.select(".cmc-table");
            for(Element t: body.select(".tableWrapper___3utdq"))
            {
                //Elements title = t.select(".sc-16r8icm-0");
                String image = t.select(".coin-logo img").attr("src");
                Elements price = t.select(".priceValue___11gHJ");

                    System.out.println("BitCoin");
                    System.out.println(image);
                    System.out.println(price.text());

        }

    }
}