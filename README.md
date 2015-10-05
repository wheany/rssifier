# rssifier
Make elements on a page into an rss feed

Very unfinished at this point, done as a Spring/Vaadin excercise with practically zero previous experience

#Usage
Should "just work" when run with maven `mvn spring-boot:run`

Visit `http://localhost:8080/` with your browser.

The UI is pretty horrible as-is, but here are some values to try:
###Url to fetch: 
`http://www.tuulivoimayhdistys.fi/ajankohtaista/uutiset`

###Selector for items
`.articles_article`

###Selector for link
`a`

###Attribute for link
`href`

###Selector for next page link
`.zfse_pageNext a`

###Attribute for link
`href`

When you click on "Generate", an xml file should appear under `generated-rss` directory

##TODO:
* Save the values given on the page so that...
* ... a timed task can be used to refresh the page content periodically
* Serve the generated rss with the correct content-type
  * (Then when the above point works) Purge old entries and feeds that no-one has read
* actually fetch content from following pages using the url from "next page" selector
