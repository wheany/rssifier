# rssifier
Make elements on a page into an rss feed

Very unfinished at this point, done as a Spring/Vaadin excercise with practically zero previous experience

#Usage
Should "just work" when run with maven `mvn spring-boot:run`

Visit `http://localhost:8080/rssifier` with your browser.

The UI is pretty horrible as-is, but here are some values to try:
###Page URL: 
`http://www.tuulivoimayhdistys.fi/ajankohtaista/uutiset`

(click on "Download URL")

###Item selector
`.articles_article`

###Link selector
`a`

###Link attribut
`href`

###Next page link selector
`.zfse_pageNext a`

###Next page link attribute
`href`

When you click on "Generate RSS feed now", an xml file should appear under `rssifier-work` directory, is a subdirectory 
that resembles the id attribute in the browser's address field (`http://localhost:8080/rssifier/edit?id=<page id>`), 
except grouped in 2 character groups, with each character group forming a new sub directory. I.e. aabbcc --> aa/bb/cc

You can also access the feed as an actual RSS feed by visiting the address 
`http://localhost:8080/rssifier/feed/<page id>`, after clicking on the "Generate RSS feed now" button

##TODO:
* a timed task to refresh the page content periodically
* Purge old entries and feeds that no-one has read
* actually fetch content from following pages using the url from "next page" selector
