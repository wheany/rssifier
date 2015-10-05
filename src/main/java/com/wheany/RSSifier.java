package com.wheany;

import com.wheany.generated.rss.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.MalformedURLException;

@SpringBootApplication
public class RSSifier {

    public static final String DOWNLOAD_TEST_URL = "https://wheany.com/test/";

    public static void main(String[] args) {
        JAXBContext jaxbContext;
        Unmarshaller jaxbUnmarshaller;
        Rss rss;
        try {
            jaxbContext = JAXBContext.newInstance(Rss.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            rss = (Rss) jaxbUnmarshaller.unmarshal(RSSifier.class.getResourceAsStream("/Liftoff News.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
            rss = new Rss();
        }

        rss.getChannel().getItemOrTitleOrLinkOrDescriptionOrLanguageOrCopyrightOrManagingEditorOrWebMasterOrPubDateOrLastBuildDateOrCategoryOrGeneratorOrDocsOrCloudOrTtlOrImageOrTextInputOrSkipHoursOrSkipDays()
                .stream()
                .filter(channelItem -> channelItem instanceof Item)
                .forEach(channelItem -> {
                    Item item = (Item) channelItem;
                    for (Object titleOrDescription : item.getTitleOrDescription()) {
                        if (titleOrDescription instanceof Title) {
                            System.out.println("Title:" + ((Title) titleOrDescription).getvalue());
                        }
                        if (titleOrDescription instanceof Description) {
                            System.out.println("Description:" + ((Description) titleOrDescription).getvalue());
                        }
                    }

                    for (Object subItem : item.getAuthorOrCategoryOrCommentsOrEnclosureOrGuidOrPubDateOrSource()) {
                        if (subItem instanceof Guid) {
                            System.out.println("Guid:" + ((Guid) subItem).getvalue());
                        }
                        if (subItem instanceof Author) {
                            System.out.println("Author:" + ((Author) subItem).getvalue());
                        }
                    }
                    if (item.getLink() != null) {
                        System.out.println("Link:" + item.getLink().getvalue());
                    }
                });
        try {
            Downloader downloader = new Downloader(DOWNLOAD_TEST_URL);

            Document document = Jsoup.parse(downloader.download().toFile(), null, DOWNLOAD_TEST_URL);

            Elements elements = document.select("a[href]");

            elements.stream().forEach(element -> System.out.println("Element:" + element.outerHtml()));

        } catch (MalformedURLException e) {
            System.err.println("Malformed URL");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO exception");
            e.printStackTrace();
        }
        SpringApplication.run(RSSifier.class, args);
    }
}
