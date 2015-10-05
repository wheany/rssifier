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
        SpringApplication.run(RSSifier.class, args);
    }
}
